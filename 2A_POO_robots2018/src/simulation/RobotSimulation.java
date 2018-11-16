package simulation;

import dijkstra.Dijkstra;
import dijkstra.Graph;
import dijkstra.Node;
import RepresentationDonnees.*;
import simulables.IncendieSimulable;
import simulables.RobotSimulable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public List<Direction> getShortestWay(Case destination) {
        int nbLignes = carte.getNbLignes();
        int nbColonnes = carte.getNbColonnes();
        Graph graph = new Graph();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Node newNode = new Node(carte.getCase(i, j));

                List<Case> voisinsNode = carte.getAllVoisins(carte.getCase(i, j));

                for (Case voisin: voisinsNode) {
                    if (robot.getVitesse(voisin.getNature()) > 0) {
                        newNode.addDestination(voisin, timeToCross(voisin));
                    }

                    if (robot.getVitesse(voisin.getNature()) > 0) {
                        newNode.addDestination(voisin, timeToCross(voisin));
                    }

                    if (robot.getVitesse(voisin.getNature()) > 0) {
                        newNode.addDestination(voisin, timeToCross(voisin));
                    }

                    if (robot.getVitesse(voisin.getNature()) > 0) {
                        newNode.addDestination(voisin, timeToCross(voisin));
                    }
                }

                graph.addNode(newNode);
            }
        }

        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.findNodeByTile(robot.getPosition()));

        Node destinationNode = graph.findNodeByTile(destination);

        if (destinationNode != null) {
            return destinationNode.getShortestPath();
        }
        return null;
    }

    public long moveTo(Case destination, long startDate) {
        List<Direction> shortestPath = getShortestWay(destination);

        long date = startDate + 1;

        assert shortestPath != null;
        for (Direction dir: shortestPath) {
            simulateur.ajouteEvenement(new DeplaceRobotEvenement(robotSimulable, dir, date));
            date++;
        }

        return date;
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
            date = moveTo(getEauProche(robot.getPosition()), date);
            date = this.remplir(date);
            chef.signalFree(this);
        }
        else {
            date = this.moveTo(carte.getCase(incendie.getLigne(), incendie.getColonne()), date);
            simulateur.ajouteEvenement(new IntervinirRobotEvenement(this.robotSimulable, incendie, date));
            date = getTempsIntervertion();
            if(incendie.isEstinguished()) {
                simulateur.ajouteEvenement(new EteindreIncendieEvenement(incendie, chef, date));
                chef.signalFree(this);
            }
        }
    }

    void setChef(ChefPompierElementaire newChef) {
        chef = newChef;
    }
}
