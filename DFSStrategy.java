package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class DFSStrategy implements Algorithm {
    boolean foundDstNode = false;
    private List<String> reconstructedPath = new ArrayList<>();
    private Map<String, String> parentMap = new HashMap<>();
    private Set<String> visitedNodes = new HashSet<>();

    @Override
    public List<String> search(Graph<String, DefaultEdge> initialGraph, String src, String dst) {
        addStartNode(src, dst);
        runWhileLoop(initialGraph, dst);
        reconstructThePath(src, dst);
        return reconstructedPath;
    }

    @Override
    public void addStartNode(String startNode, String dstNode) {
        stackOfNodes.push(startNode);
        visitedNodes.add(startNode);
    }

    @Override
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

    @Override
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
