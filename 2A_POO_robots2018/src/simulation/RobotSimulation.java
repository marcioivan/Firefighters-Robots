package simulation;

import dijkstra.Dijkstra;
import dijkstra.Graph;
import dijkstra.Node;
import representation_donnees.Carte;
import representation_donnees.Case;
import representation_donnees.Direction;
import representation_donnees.Robot;

import java.util.List;

public class RobotSimulation {
    private Robot robot;
    private Carte carte;

    public RobotSimulation(Robot r, Carte c) {
        robot = r;
        carte = c;
    }

    // T = dS/dV
    private Double timeToCross(Case tile) { // in seconds
        return (carte.getTailleCases() * 3600) / (robot.getVitesse(tile.getNature()) * 1000);
    }

    public List<Direction> getShortestWay(Case dst) {
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

        graph = Dijkstra.calculateShortestPathFromSource(graph, graph.findNode(robot.getPosition()));

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

    public void moveTo(Case dst) {
        List<Direction> shortestPath = getShortestWay(dst);
        //Create events
    }
}
