package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Drawing.AdjacencyList.DrawGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.Nodes.DirectedNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Algorithms.BFS;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;
import Graphs.Algorithms.Dijkstra;

public class DijkstraTest {

    @Test
    void Dijkstra_ok() {
        int[][] mat = GraphTools.generateValuedGraphData(10, true, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(Dijkstra.Dijkstra(al, al.getNodes().get(0)));

        int From = 7;
        int To = 0;

        List<DirectedNode> path = Dijkstra.ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));

    }
}
