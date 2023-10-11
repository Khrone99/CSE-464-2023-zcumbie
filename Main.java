package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n...Starting Program... \n");
        parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");
        System.out.println("\n...Ending Program... \n");
    }

    public static void parseGraph(String filePath) {
        DefaultDirectedGraph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        List<String> dirOfEdges = new ArrayList<>();

        int numOfNodes = 0;
        int numOfEdges = 0;
        int i = 0;
        String[] nodes = new String[5];

        try (Scanner scanner = new Scanner(new File(filePath))) {
            Pattern edgePattern = Pattern.compile("(\\w+) -> (\\w+);");

            while (scanner.hasNextLine()) {
                String lineStr = scanner.nextLine().trim();
                Matcher matcherObj = edgePattern.matcher(lineStr);

                if (matcherObj.matches()) {
                    String source = matcherObj.group(1);
                    String target = matcherObj.group(2);

                    nodes[i] = source;
                    i++;

                    // Add the vertices and edge to the graph
                    graph.addVertex(source); // Source Node
                    graph.addVertex(target); // Target Node
                    graph.addEdge(source, target); // Creates an edge between source and target nodes

                    dirOfEdges.add(source + " -> " + target); // Creates a string representation of the edge and it's direction

                    numOfEdges++; // Tracks the number of edges
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        numOfNodes = graph.vertexSet().size(); // Calculates the number of nodes

        System.out.println("Number of Nodes: " + numOfNodes);
        System.out.println("Number of Edges: " + numOfEdges);

        System.out.print("Node Labels: ");

        nodes = new HashSet<String>(Arrays.asList(nodes)).toArray(new String[0]);

        System.out.println(Arrays.toString(nodes));

        System.out.println("Edge Directions: ");
        for (String str : dirOfEdges) { // Prints every string from dirOfEdges i.e. A -> B, etc.
            System.out.println(str);
        }
    }
}
