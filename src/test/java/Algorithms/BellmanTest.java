package Algorithms;
import Drawing.AdjacencyList.DrawGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.Algorithms.Bellman;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.Algorithms.BellmanException;
import Graphs.Nodes.DirectedNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Algorithms.BFS;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

public class BellmanTest {

    @Test
    void BellmanTest_should_throw_no_path() throws Exception {
        //GIVEN
        int[][] mat = GraphTools.generateValuedGraphData(5, false, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        int From = 4;
        int To = 1;

        //WHENZEN
        Exception exception = Assertions.assertThrows(BellmanException.class, () -> {
            List<DirectedNode> path = Bellman.ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));
        });

        //THEN
        Assertions.assertEquals(BellmanException.NO_PATH_MSG, exception.getMessage());

    }

    @Test
    void BellmanTest_ok() throws Exception {
        //GIVEN
        int[][] mat = GraphTools.generateValuedGraphData(5, false, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        int From = 1;
        int To = 4;

        //WHEN
        List<DirectedNode> path = Bellman.ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));

        //THEN
        List<DirectedNode> expected = new ArrayList<>();
        expected.add(al.getNodes().get(1));
        expected.add(al.getNodes().get(2));
        expected.add(al.getNodes().get(0));
        expected.add(al.getNodes().get(4));

        Assertions.assertEquals(expected, path);
    }

    @Test
    void BellmanTest_should_throw_negative_cycle() throws Exception {
        //GIVEN
        int[][] mat = GraphTools.generateValuedGraphData(10, false, false, true, true, 855);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        int From = 6;
        int To = 5;

        //WHENZEN
        Exception exception = Assertions.assertThrows(BellmanException.class, () -> {
            Bellman.Bellman(al, al.getNodes().get(5));
        });

        //THEN
        Assertions.assertEquals(BellmanException.NEGATIVE_CYCLE_MSG, exception.getMessage());
    }
}
