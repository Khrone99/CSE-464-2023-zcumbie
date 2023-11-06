package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Path {
    public static Graph<String, DefaultEdge> GraphSearch(Graph<String, DefaultEdge> originalGraph, String startNode, String dstNode) {
        // Create a new graph for the search result
        Graph<String, DefaultEdge> searchGraph = new SimpleGraph<>(DefaultEdge.class);
        boolean found = false;

        // Create a DFS iterator starting from the specified node
        GraphIterator<String, DefaultEdge> dfsIterator = new DepthFirstIterator<>(originalGraph, startNode);

        // Add the start node to the search graph
        searchGraph.addVertex(startNode);

        // Traverse the graph using DFS and add nodes and edges to the search graph
        while (dfsIterator.hasNext()) {
            String currentVertex = dfsIterator.next();
            for (DefaultEdge edge : originalGraph.edgesOf(currentVertex)) {
                String sourceVertex = originalGraph.getEdgeSource(edge);
                String targetVertex = originalGraph.getEdgeTarget(edge);

                // Add vertices and edges to the search graph
                searchGraph.addVertex(sourceVertex);
                searchGraph.addVertex(targetVertex);
                searchGraph.addEdge(sourceVertex, targetVertex);

                if (targetVertex == dstNode) {
                    found = true;
                    return searchGraph;
                }
            }
        }

        if (found == false) {
            return null;
        }
        return searchGraph;
    }
}
