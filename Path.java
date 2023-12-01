package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Path {

    Algorithm algorithm;
    List<String> output = new ArrayList<>();

    public List<String> GraphSearch(Graph<String, DefaultEdge> initialGraph, String src, String dst, Algorithm algo) {
        this.algorithm = algo;

        output = algorithm.search(initialGraph, src, dst);

        return output;
    }

}
