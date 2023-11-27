package org.example;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Path {
    public static Graph<String, DefaultEdge> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode, GraphSearchStrategyPattern strategy) {
        return strategy.search(initialGraph, startNode, dstNode);
    }
}
