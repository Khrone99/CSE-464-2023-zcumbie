package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class featureTester {

    @Test
    public void testParseGraph() {
        boolean testValid = false;
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        Set<String> set = new LinkedHashSet<>();
        set.add("(A : B)");
        set.add("(B : C)");
        set.add("(C : A)");
        set.add("(A : C)");
        set.add("(D : B)");

        Set<String> unmod = Collections.unmodifiableSet(set);

        assertEquals(unmod, Main.graph.edgeSet());

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

    // Project Part 2 Test Cases

    @Test
    public void testRemoveNodeExistingNode() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        Main.addNode("V");
        Main.addNode("P");
        Main.addEdge("V", "P");

        Main.removeNode("V");

        assertFalse(Main.graph.containsVertex("V"));
        assertTrue(Main.graph.containsVertex("P"));
        assertFalse(Main.graph.containsEdge("V", "P"));
    }

    @Test(expected = NodeNotFoundException.class)
    public void testRemoveNodeNonExistingNode() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        Main.removeNode("Z");
    }

    @Test
    public void testRemoveNodesExistingNodes() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        String[] labels = {"L", "N", "U"};
        String[] labels2 = {"L", "U"};

        Main.addNodes(labels);

        Main.removeNodes(labels2);

        assertFalse(Main.graph.containsVertex("L"));
        assertFalse(Main.graph.containsVertex("U"));
        assertTrue(Main.graph.containsVertex("N"));
    }

    @Test(expected = NodeNotFoundException.class)
    public void testRemoveNodesNonExistingNodes() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        String[] labels = {"K", "J", "R"};

        Main.removeNodes(labels);
    }

    @Test
    public void testRemoveEdgeExistingEdge() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        String[] labels = {"X", "Y", "Z"};

        Main.addNodes(labels);

        Main.addEdge("X", "Z");
        Main.addEdge("Z", "Y");
        Main.addEdge("Y", "X");

        Main.removeEdge("Z", "Y");

        assertFalse(Main.graph.containsEdge("Z", "Y"));
        assertTrue(Main.graph.containsEdge("Y", "X"));
        assertTrue(Main.graph.containsEdge("X", "Z"));
    }

    @Test(expected = EdgeNotFoundException.class)
    public void testRemoveEdgeNonExistingEdge() {
        Main.parseGraph("C:/Users/ldf08/IdeaProjects/CSE464Project/input.dot");

        Main.addNode("M");
        Main.addNode("O");

        Main.removeEdge("M", "O");
    }

}
