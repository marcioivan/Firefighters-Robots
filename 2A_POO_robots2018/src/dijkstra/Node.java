package dijkstra;

import RepresentationDonnees.Case;
import RepresentationDonnees.Direction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private Case tile;

    private List<Direction> shortestPath = new LinkedList<>();

    private Double distance = Double.MAX_VALUE;

    Map<Case, Double> adjacentCases = new HashMap<>();

    public void addDestination(Case destination, Double distance) {
        adjacentCases.put(destination, distance);
    }

    public Node(Case tile) {
        this.tile = tile;
    }

    public Case getTile() {
        return tile;
    }
    public void setTile(Case tile) {
        this.tile = tile;
    }

    public List<Direction> getShortestPath() {
        return shortestPath;
    }
    public void setShortestPath(List<Direction> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Case, Double> getAdjacentCases() {
        return adjacentCases;
    }
    public void setAdjacentCases(Map<Case, Double> adjacentCases) {
        this.adjacentCases = adjacentCases;
    }
}
