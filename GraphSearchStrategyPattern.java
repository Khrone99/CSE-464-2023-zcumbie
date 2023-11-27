package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface GraphSearchStrategyPattern {
    Graph<String, DefaultEdge> search(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode);
}
