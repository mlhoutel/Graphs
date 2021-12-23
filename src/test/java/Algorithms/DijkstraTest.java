package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Drawing.AdjacencyList.DrawGraph;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.Algorithms.*;
import Graphs.Nodes.DirectedNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

public class DijkstraTest {

    @Test
    void Dijkstra_should_throw_no_path() throws DijkstraException {
        int[][] mat = GraphTools.generateValuedGraphData(10, true, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);

        int From = 7;
        int To = 1;

        //WHENZEN
        Exception exception = Assertions.assertThrows(DijkstraException.class, () -> {
            List<DirectedNode> path = Dijkstra.ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));
        });

        //THEN
        Assertions.assertEquals(DijkstraException.NO_PATH_MSG, exception.getMessage());
    }

    @Test
    void Dijkstra_ok() throws DijkstraException {
        //GIVEN
        int[][] mat = GraphTools.generateValuedGraphData(5, false, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        int From = 1;
        int To = 4;

        //WHEN
        List<DirectedNode> path = Dijkstra.ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));

        //THEN
        List<DirectedNode> expected = new ArrayList<>();
        expected.add(al.getNodes().get(1));
        expected.add(al.getNodes().get(2));
        expected.add(al.getNodes().get(0));
        expected.add(al.getNodes().get(4));

        Assertions.assertEquals(expected, path);
    }
}
