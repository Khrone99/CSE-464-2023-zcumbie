package org.example;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class BFS extends SearchAlgorithm{

    public void addStartNode(String startNode, String dstNode) {
        queuedNodes.add(startNode);
        visitedNodes.add(startNode);
    }

    public void runWhileLoop(Graph<String, DefaultEdge> initialGraph, String dstNode) {
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
    }

    public void reconstructThePath(String startNode, String dstNode) {
        // Reconstruct path from destination node to start node
        String currentNode = dstNode;

        while (!currentNode.equals(startNode)) {
            reconstructedPath.add(currentNode);
            currentNode = parentMap.get(currentNode);
        }

        reconstructedPath.add(startNode);
        Collections.reverse(reconstructedPath);
    }
}
