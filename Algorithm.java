package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public interface Algorithm {
    Queue<String> queuedNodes = new LinkedList<>();
    Stack<String> stackOfNodes = new Stack<>();

    List<String> search(Graph<String, DefaultEdge> initialGraph, String src, String dst);

    void addStartNode(String startNode, String dstNode);

    void runWhileLoop(Graph<String, DefaultEdge> initialGraph, String dstNode);

    void reconstructThePath(String startNode, String dstNode);
}
