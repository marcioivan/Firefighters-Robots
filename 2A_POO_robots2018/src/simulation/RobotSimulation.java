package simulation;

import dijkstra.Dijkstra;
import dijkstra.Graph;
import dijkstra.Node;
import representation_donnees.*;
import simulables.IncendieSimulable;
import simulables.RobotSimulable;

import java.util.LinkedList;
import java.util.List;

public class RobotSimulation {
    private RobotSimulable robotSimulable;
    private Robot robot;
    private Carte carte;
    private Simulateur simulateur;
    private LinkedList<Case> eauCases = new LinkedList<>();
    private ChefPompierElementaire chef;


    public RobotSimulation(RobotSimulable r, Carte c, Simulateur s) {
        robotSimulable = r;
        robot = robotSimulable.getRobot();
        carte = c;
        simulateur = s;

        for(int i = 0; i < carte.getNbLignes(); i++) {
            for(int j = 0; j < carte.getNbColonnes(); j++) {
                if(carte.getCase(i, j).getNature() == NatureTerrain.EAU)
                    eauCases.add(carte.getCase(i, j));
            }
        }
    }

    // T = dS/dV
    private Double timeToCross(Case tile) { // in seconds
        return (carte.getTailleCases() * 3600) / (robot.getVitesse(tile.getNature()) * 1000);
    }

    private List<Direction> getShortestWay(Case dst) {
        int nbLignes = carte.getNbLignes();
        int nbColonnes = carte.getNbColonnes();
        Graph graph = new Graph();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Node newNode = new Node(carte.getCase(i, j));

                Case tileActuel = carte.getCase(i,j);

                if (carte.voisinExiste(tileActuel, Direction.NORD)) {
                    if (robot.getVitesse(carte.getCase(i-1, j).getNature()) > 0) {
                        newNode.addDestination(carte.getCase(i - 1, j), timeToCross(carte.getCase(i - 1, j)));
                    }
                }

                if (carte.voisinExiste(tileActuel, Direction.SUD)) {
                    if (robot.getVitesse(carte.getCase(i+1, j).getNature()) > 0) {
                        newNode.addDestination(carte.getCase(i + 1, j), timeToCross(carte.getCase(i + 1, j)));
                    }
                }

                if (carte.voisinExiste(tileActuel, Direction.OUEST)) {
                    if (robot.getVitesse(carte.getCase(i, j-1).getNature()) > 0) {
                        newNode.addDestination(carte.getCase(i, j - 1), timeToCross(carte.getCase(i, j - 1)));
                    }
                }

                if (carte.voisinExiste(tileActuel, Direction.EST)) {
                    if (robot.getVitesse(carte.getCase(i, j+1).getNature()) > 0) {
                        newNode.addDestination(carte.getCase(i, j + 1), timeToCross(carte.getCase(i, j + 1)));
                    }
                }

                graph.addNode(newNode);
            }
        }

        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.findNode(carte.getCase(robot.getPosition().getLigne(), robot.getPosition().getColonne())));

        Node destination = graph.findNode(dst);
        // Print directions - need to change
        if (destination != null) {
            System.out.print("Dir: ");

            for (Direction dir : destination.getShortestPath()) {
                System.out.print(dir.toString() + " ");
            }
            System.out.println();
            return destination.getShortestPath();
        }
        return null;
    }

    private Case getEauProche(Case src) {
        int srcLig = src.getLigne();
        int srcCol = src.getColonne();
        Case found = src;

        int diffLig = carte.getNbLignes(), diffCol = carte.getNbColonnes();
        for (Case eauCase : eauCases) {
            if( (diffLig + diffCol) > (Math.abs(eauCase.getLigne() - srcLig) + Math.abs(eauCase.getColonne())) ) {
                found = eauCase;
                diffLig = Math.abs(srcLig - eauCase.getLigne());
                diffCol = Math.abs(srcCol - eauCase.getColonne());
            }

        }

        if(!(robot instanceof RobotDrone)) {
            for (Direction dir : Direction.values()) {
                if (carte.voisinExiste(found, dir)) {
                    NatureTerrain natureVoisin = carte.getVoisin(found, dir).getNature();
                    if (robot instanceof RobotRoues && (natureVoisin == NatureTerrain.TERRAIN_LIBRE || natureVoisin == NatureTerrain.HABITAT))
                        found = carte.getVoisin(found, dir);
                    else if (robot instanceof RobotChenilles && (natureVoisin != NatureTerrain.EAU && natureVoisin != NatureTerrain.ROCHE))
                        found = carte.getVoisin(found, dir);
                    else if (robot instanceof RobotPattes && natureVoisin != NatureTerrain.EAU)
                        found = carte.getVoisin(found, dir);
                }
            }
        }

        return found;
    }

    private int getTempsIntervertion () {
        int tempsIntervertion = 0;

        if(robot instanceof RobotDrone) tempsIntervertion = 30;
        else if(robot instanceof RobotChenilles) tempsIntervertion = 8;
        else if(robot instanceof RobotRoues) tempsIntervertion = 5;
        else if(robot instanceof RobotPattes) tempsIntervertion = 1;

        return tempsIntervertion;
    }

    public long moveTo(Case dst, long startDate) {
        List<Direction> shortestPath = getShortestWay(dst);

        long date = startDate + 1;

        assert shortestPath != null;
        for (Direction dir : shortestPath) {
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robotSimulable, dir, date));
            date++;
        }

        return date;
    }

    private long remplir(long date) {
        long dateStart = date;
        long dateFinish = 0;

        if(robot instanceof RobotDrone) dateFinish = dateStart + 30;
        else if(robot instanceof RobotChenilles) dateFinish = dateStart + 5;
        else if(robot instanceof RobotRoues) dateFinish = dateStart + 10;
        else if(robot instanceof RobotPattes) dateFinish = dateStart;

        simulateur.ajouteEvenement(new DebutRemplirRobotEvenement(dateStart));
        simulateur.ajouteEvenement(new FinRemplirRobotEvenement(robotSimulable, dateFinish));

        return dateFinish;
    }

    public void intervinir(IncendieSimulable incendie) {
        long date = simulateur.getDateSimulation() + 1;

        chef.signalOccupied(this);

        if(robot.getVolume() == 0) {
            //goes to closest water tile to the fire to fill reservatory
            date = moveTo(getEauProche(carte.getCase(incendie.getLigne(), incendie.getColonne())), date);
            date = this.remplir(date);
        }
        else {
            date = this.moveTo(carte.getCase(incendie.getLigne(), incendie.getColonne()), date);
            simulateur.ajouteEvenement(new IntervinirRobotEvenement(this.robotSimulable, incendie, date));
            date = getTempsIntervertion();
            if(incendie.isEstinguished())
                simulateur.ajouteEvenement(new EteindreIncendieEvenement(incendie, chef, date));
        }

        simulateur.ajouteEvenement(new RobotLibreEvenement(this, chef, date));
    }

    void setChef(ChefPompierElementaire newChef) {
        chef = newChef;
    }
}
