package dijkstra;

import RepresentationDonnees.Case;
import RepresentationDonnees.Direction;

import java.util.*;

public class Dijkstra {
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        source.setDistance(0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Case, Double> adjacencyPair: currentNode.getAdjacentCases().entrySet()) {
                Node adjacentNode = graph.findNodeByTile(adjacencyPair.getKey());
                Double edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set< Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;
        for (Node node: unsettledNodes) {
            Double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Double edgeWeight, Node sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            List<Direction> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            Direction dirVoisin = sourceNode.getTile().getDirectionVoisin(evaluationNode.getTile());
            if(dirVoisin != null) {
                shortestPath.add(dirVoisin);
                evaluationNode.setShortestPath(shortestPath);
            }
        }
    }
}
