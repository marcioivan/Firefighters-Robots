package dijkstra;

import representation_donnees.Case;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Node findNode(Case tile) {
        for (Node node: getNodes()) {
            if (node.getTile().equals(tile)) {
                return node;
            }
        }
        return null;
    }
}
