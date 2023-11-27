package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class DFS extends SearchAlgorithm{
    public static Graph<String, DefaultEdge> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode) {
        // Create a new graph for the search result
        Graph<String, DefaultEdge> searchGraph = new SimpleGraph<>(DefaultEdge.class);
        boolean found = false;

        GraphIterator<String, DefaultEdge> iterator = new DepthFirstIterator<>(initialGraph, startNode);

        // Add the start node to the search graph
        searchGraph.addVertex(startNode);

        // Traverse the graph using BFS/DFS and add nodes and edges to the search graph
        while (iterator.hasNext()) {
            String currentVertex = iterator.next();
            for (DefaultEdge edge : initialGraph.edgesOf(currentVertex)) {
                String sourceVertex = initialGraph.getEdgeSource(edge);
                String targetVertex = initialGraph.getEdgeTarget(edge);

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
