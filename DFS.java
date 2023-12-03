package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class DFS extends SearchAlgorithm{

    public void addStartNode(String startNode, String dstNode) {
        stackOfNodes.push(startNode);
        visitedNodes.add(startNode);
    }

    public void runWhileLoop(Graph<String, DefaultEdge> initialGraph, String dstNode) {
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
