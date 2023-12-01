package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class DFS {
    public static List<String> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode) {
        Stack<String> stackOfNodes = new Stack<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visitedNodes = new HashSet<>();

        stackOfNodes.push(startNode);
        visitedNodes.add(startNode);

        boolean foundDstNode = false;

        while (!stackOfNodes.isEmpty() && !foundDstNode) {
            String currentVertex = stackOfNodes.pop();

            if (currentVertex.equals(dstNode)) {
                foundDstNode = true;
                break;
            }

            for (DefaultEdge edge : initialGraph.outgoingEdgesOf(currentVertex)) {
                String neighbor = initialGraph.getEdgeTarget(edge);
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    parentMap.put(neighbor, currentVertex);
                    stackOfNodes.push(neighbor);
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
