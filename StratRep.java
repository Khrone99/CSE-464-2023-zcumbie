package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class StratRep extends Path{

    @Override
    public List<String> GraphSearch(Graph<String, DefaultEdge> initialGraph, String src, String dst, Algorithm algo) {
        return super.GraphSearch(initialGraph, src, dst, algo);
    }
}
