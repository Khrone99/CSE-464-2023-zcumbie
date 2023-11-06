package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Path {
    public static Graph<String, DefaultEdge> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode) {
        // Create a new graph for the search result
        Graph<String, DefaultEdge> newGraph = new SimpleGraph<>(DefaultEdge.class);
        boolean found = false;

        // Create a BFS iterator starting from the specified node
        GraphIterator<String, DefaultEdge> iterator = new BreadthFirstIterator<>(initialGraph, startNode);

        // Add the start node to the search graph
        newGraph.addVertex(startNode);

        // Traverse the graph using BFS and add nodes and edges to the search graph
        while (iterator.hasNext()) {
            String currentVertex = iterator.next();
            for (DefaultEdge edge : initialGraph.edgesOf(currentVertex)) {
                String sourceVertex = initialGraph.getEdgeSource(edge);
                String targetVertex = initialGraph.getEdgeTarget(edge);

                // Add vertices and edges to the search graph
                newGraph.addVertex(sourceVertex);
                newGraph.addVertex(targetVertex);
                newGraph.addEdge(sourceVertex, targetVertex);

                if (targetVertex == dstNode) {
                    found = true;
                    return searchGraph;
                }
            }
        }

        if (found == false) {
            return null;
        }
        return newGraph;
    }
}
