package org.example;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class BFS {
    public static List<String> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode) {
        Queue<String> queuedNodes = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visitedNodes = new HashSet<>();

        queuedNodes.add(startNode);
        visitedNodes.add(startNode);

        boolean foundDstNode = false;

        while (!queuedNodes.isEmpty() && !foundDstNode) {
            String currentVertex = queuedNodes.poll();

            if (currentVertex.equals(dstNode)) {
                foundDstNode = true;
                break;
            }

            for (DefaultEdge edge : initialGraph.outgoingEdgesOf(currentVertex)) {
                String neighbor = initialGraph.getEdgeTarget(edge);
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    parentMap.put(neighbor, currentVertex);
                    queuedNodes.add(neighbor);
                }
            }
        }

        if (!foundDstNode) {
            return Collections.emptyList();
        }

        // Reconstruct path from destination node to start node
        List<String> reconstructedPath = new ArrayList<>();
        String currentNode = dstNode;

        while (!currentNode.equals(startNode)) {
            reconstructedPath.add(currentNode);
            currentNode = parentMap.get(currentNode);
        }

        reconstructedPath.add(startNode);
        Collections.reverse(reconstructedPath);

        return reconstructedPath;
    }
}
