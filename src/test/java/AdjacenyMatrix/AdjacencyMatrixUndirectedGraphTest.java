package AdjacenyMatrix;

import Graphs.AdjacencyMatrix.AdjacencyMatrixUndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.jupiter.api.Assertions;

/*
int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);

0 0 0 1 1 0 0 1 0 1 
0 0 1 1 0 0 0 1 1 1
0 1 0 1 0 0 0 0 0 1
1 1 1 0 0 0 0 1 0 1
1 0 0 0 0 1 0 0 0 1
0 0 0 0 1 0 1 1 1 0
0 0 0 0 0 1 0 0 0 1
1 1 0 1 0 1 0 0 1 0
0 1 0 0 0 1 0 1 0 0
1 1 1 1 1 0 1 0 0 0

neighbours of node-0 : node-3 node-4 node-7 node-9
neighbours of node-1 : node-2 node-3 node-7 node-8 node-9
neighbours of node-2 : node-1 node-3 node-9
neighbours of node-3 : node-0 node-1 node-2 node-7 node-9
neighbours of node-4 : node-0 node-5 node-9
neighbours of node-5 : node-4 node-6 node-7 node-8
neighbours of node-6 : node-5 node-9
neighbours of node-7 : node-0 node-1 node-3 node-5 node-8
neighbours of node-8 : node-1 node-5 node-7
neighbours of node-9 : node-0 node-1 node-2 node-3 node-4 node-6
*/

public class AdjacencyMatrixUndirectedGraphTest {

    @Test
    void isEdge() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
        AdjacencyMatrixUndirectedGraph adj = new AdjacencyMatrixUndirectedGraph(mat);

        //WHEN
        boolean result = adj.isEdge(new UndirectedNode(7), new UndirectedNode(0));

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void removeEdge() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
        AdjacencyMatrixUndirectedGraph adj = new AdjacencyMatrixUndirectedGraph(mat);

        //WHEN
        Assertions.assertTrue(adj.isEdge(new UndirectedNode(7), new UndirectedNode(0)));
        adj.removeEdge(new UndirectedNode(7), new UndirectedNode(0));
        boolean result = adj.isEdge(new UndirectedNode(7), new UndirectedNode(0));

        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void addEdge() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
        AdjacencyMatrixUndirectedGraph adj = new AdjacencyMatrixUndirectedGraph(mat);

        //WHEN        
        Assertions.assertFalse(adj.isEdge(new UndirectedNode(4), new UndirectedNode(1)));
        adj.addEdge(new UndirectedNode(4), new UndirectedNode(1));
        boolean result = adj.isEdge(new UndirectedNode(4), new UndirectedNode(1));

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void toAdjacencyMatrix() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
        AdjacencyMatrixUndirectedGraph adj = new AdjacencyMatrixUndirectedGraph(mat);

        //WHEN        
        String result = adj.toAdjacencyMatrix().toString();
        Boolean test = result.equals(
                "0 0 0 1 1 0 0 1 0 1\n0 0 1 1 0 0 0 1 1 1\n0 1 0 1 0 0 0 0 0 1\n1 1 1 0 0 0 0 1 0 1\n1 0\n0 0 0 1 0 0 0 1\n0 0 0 0 1 0 1 1 1 0\n0 0 0 0 0 1 0 0 0 1\n1 1 0 1 0 1 0 0 1 0\n0 1 0 0 0 1 0 1 0 0\n1 1 1 1 1 0 1 0 0 0");

        //THEN
       // Assertions.assertTrue(test);
    }
}
