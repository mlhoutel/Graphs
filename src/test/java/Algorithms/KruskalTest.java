package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Algorithms.BFS;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

public class KruskalTest {

    @Test
    void Kruskal() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        UndirectedGraph al = new UndirectedGraph(mat);

        //WHEN
        List<UndirectedNode> result = BFS.BFS(al);
        List<Integer> labels = new ArrayList<Integer>();
        for (UndirectedNode node : result) {
            labels.add(node.getLabel());
        }

        //THEN
        Assertions.assertEquals(labels, new ArrayList(Arrays.asList(0,2,4,1,3)));
    }
}
