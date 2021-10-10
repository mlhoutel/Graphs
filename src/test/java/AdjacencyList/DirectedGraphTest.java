package AdjacencyList;

import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class DirectedGraphTest {

    
    /*
    int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);

    successors of node-0 : node-3 node-7
    successors of node-1 : node-2 node-7 node-9
    successors of node-2 : node-3
    successors of node-3 : node-1 node-2 node-9
    successors of node-4 : node-0 node-5 node-9
    successors of node-5 : node-6
    successors of node-6 : node-5
    successors of node-7 : node-3 node-5
    successors of node-8 : node-1 node-5
    successors of node-9 : node-2 node-6    
    */

    @Test
    void isArc() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        DirectedGraph al = new DirectedGraph(Matrix);
        DirectedNode  from = al.getNodes().get(0);
        DirectedNode  to = al.getNodes().get(3);

        //WHEN
        Boolean result = al.isArc(from, to);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void removeArc() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        DirectedGraph al = new DirectedGraph(Matrix);
        DirectedNode  from = al.getNodes().get(0);
        DirectedNode  to = al.getNodes().get(3);
        Assertions.assertTrue(al.isArc(from, to));
        
        //WHEN
        al.removeArc(from, to);
        Boolean result = al.isArc(from,to);

        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void addArc() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        DirectedGraph al = new DirectedGraph(Matrix);
        DirectedNode  from = al.getNodes().get(0);
        DirectedNode  to = al.getNodes().get(5);
        Assertions.assertFalse(al.isArc(from, to));
        
        //WHEN
        al.addArc(from, to);
        Boolean result = al.isArc(from, to);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void computeInverse() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        DirectedGraph al = new DirectedGraph(Matrix);
        
        //WHEN
        DirectedGraph la = al.computeInverse();

        //THEN
        Assertions.assertEquals(al, la.computeInverse());
    }
}
