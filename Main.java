package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class Main {
    static DefaultDirectedGraph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

    public static void parseGraph(String filePath) {
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

        System.out.println("");
    }

    public static void outputDOTGraph(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write("digraph G {\n");

            for (DefaultEdge edge : graph.edgeSet()) {
                String source = graph.getEdgeSource(edge);
                String target = graph.getEdgeTarget(edge);
                fileWriter.write("  " + source + " -> " + target + ";\n");
            }

            fileWriter.write("}\n");
            System.out.println("Graph exported to " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNode(String label) {
        if (!graph.containsVertex(label)) {
            graph.addVertex(label);
        } else {
            System.out.println("Node " + label + " already exists!");
        }
    }

    public static void removeNode(String label) {
        if (graph.containsVertex(label)) {
            graph.removeVertex(label);
        } else {
            throw new NodeNotFoundException("The node does not exist in the graph.");
        }
    }

    public static void addNodes(String[] labels) {
        for (String label : labels) {
            if (!graph.containsVertex(label)) {
                graph.addVertex(label);
            }  else {
                System.out.println("Node " + label + " already exists!");
            }
        }
    }

    public static void removeNodes(String[] labels) {
        for (String str : labels) {
            if (graph.containsVertex(str)) {
                graph.removeVertex(str);
            } else {
                throw new NodeNotFoundException("The node does not exist in the graph.");
            }
        }
    }

    public static void addEdge(String srcLabel, String dstLabel) {
        if (!graph.containsEdge(srcLabel, dstLabel)) {
            graph.addEdge(srcLabel, dstLabel);
        }   else {
            System.out.println("Node " + srcLabel + " and Node " + dstLabel + " already have an edge in that direction!");
        }
    }

    public static void removeEdge(String srcLabel, String dstLabel) {
        if (graph.containsEdge(srcLabel, dstLabel)) {
            graph.removeEdge(srcLabel, dstLabel);
        } else {
            throw new EdgeNotFoundException("The edge does not exist in the graph.");
        }
    }

    public static void main(String[] args) {
        System.out.println("\n...Starting Program... \n");

        parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        addNode("E");
        addNode("E");

        String[] newNodes = {"F", "G", "H", "F"};
        addNodes(newNodes);

        addEdge("F", "G");
        addEdge("F", "G");
        addEdge("G", "F");

        outputDOTGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input2.dot");

        System.out.println("\n...Ending Program... \n");
    }
}
