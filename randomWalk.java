package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class randomWalk {

    public List<String> randomWalkSearch(Graph<String, DefaultEdge> graph, String src, String dst) {
        
        Random randomizer = new Random();
        String heldNode = src;

        while (true) {

            List<String> path = new ArrayList<>();
            path.add(heldNode);
            System.out.println("Path found: " + path);

            while (!heldNode.equals(dst)) {
                List<String> childrenNodes = new ArrayList<>(graph.outgoingEdgesOf(heldNode).size());
                for (DefaultEdge edge : graph.outgoingEdgesOf(heldNode)) { // For every outgoing edge, add the target node to the list of children nodes.
                    String childNode = graph.getEdgeTarget(edge);
                    childrenNodes.add(childNode);
                }

                if (childrenNodes.isEmpty()) {
                    break; // The node has no neighbors and the path is not valid.
                } else {
                    int randomIndex = randomizer.nextInt(childrenNodes.size());
                    String nextNode = childrenNodes.get(randomIndex);
                    path.add(nextNode);
                    System.out.println("Path found: " + path);

                    if (nextNode.equals(dst)) {
                        return path; // A correct path has been found!
                    }
                    heldNode = nextNode;
                }
            }

            // Begin again from the stated src from the method call
            heldNode = src;
            System.out.println("Path ended without find destination node, restarting search...");

        }
    }
}
