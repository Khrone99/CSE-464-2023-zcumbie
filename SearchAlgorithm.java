package org.example;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public abstract class SearchAlgorithm {

    public Queue<String> queuedNodes = new LinkedList<>();
    public Stack<String> stackOfNodes = new Stack<>();
    public Map<String, String> parentMap = new HashMap<>();
    public Set<String> visitedNodes = new HashSet<>();
    public List<String> reconstructedPath = new ArrayList<>();

    boolean foundDstNode = false;

    public final List<String> GraphSearch(Graph<String, DefaultEdge> initialGraph, String startNode, String dstNode) {
        addStartNode(startNode, dstNode);
        runWhileLoop( initialGraph, dstNode);
        reconstructThePath(startNode, dstNode);
        return reconstructedPath;
    }

    public abstract void addStartNode(String startNode, String dstNode);

    public abstract void runWhileLoop(Graph<String, DefaultEdge> initialGraph, String dstNode);

    public abstract void reconstructThePath(String startNode, String dstNode);

}
