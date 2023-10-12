package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class featureTester {

    @Test
    public void testParseGraph() {
        boolean testValid = false;
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        assertEquals("(A : B), (B : C), (C : A), (A : C), (D : B)", Main.graph.edgeSet());

        if (Main.graph.containsVertex("A") && Main.graph.containsVertex("B") && Main.graph.containsVertex("C") && Main.graph.containsVertex("D")) {
            testValid = true;
        }

        assertEquals(true, testValid);


        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input2.dot");

        assertEquals("(A : B), (B : C), (C : A), (A : C), (D : B), (F : G), (G : F)", Main.graph.edgeSet());

        if (Main.graph.containsVertex("A") && Main.graph.containsVertex("B") && Main.graph.containsVertex("C") && Main.graph.containsVertex("D") && Main.graph.containsVertex("F") && Main.graph.containsVertex("G")) {
            testValid = true;
        }

        assertEquals(true, testValid);
    }

    @Test
    public void testAddEdges() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        Main.addEdge("A", "D");
        assertEquals("(A : D)", Main.graph.getEdge("A", "D").toString());

        Main.addEdge("C", "B");
        assertEquals("(C : B)", Main.graph.getEdge("C", "B").toString());
    }

    @Test
    public void testAddSingleNode() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        boolean check = true;

        Main.addNode("Z");
        assertEquals(check, Main.graph.containsVertex("Z"));

        Main.addNode("W");
        assertEquals(check, Main.graph.containsVertex("W"));
    }

    @Test
    public void testAddMultipleNodes() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        boolean check = true;
        boolean testValid = false;

        String[] nodesToTest = {"X", "Y", "Z"};

        Main.addNodes(nodesToTest);

        if (Main.graph.containsVertex("X") && Main.graph.containsVertex("Y") && Main.graph.containsVertex("Z")) {
            testValid = true;
        }

        assertEquals(check, testValid);
    }

    @Test
    public void testOutputToDot() {
        Main.outputDOTGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/output.dot");
        File dotFile = new File("C:/Users/ldf08/IdeaProjects/CSE464Project/output.dot");
        assertTrue("DOT file should exist", dotFile.exists());
    }
}
