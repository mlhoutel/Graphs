package GraphAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Algorithms.BFS;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

public class BinaryHeapTest {

    @Test
    void BFS() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        UndirectedGraph al = new UndirectedGraph(mat);

        //WHEN
        //List<UndirectedNode> result = BFS.BFS(al);

        //THEN
        //Assertions.assertEquals(labels, new ArrayList(Arrays.asList(0,2,4,1,3)));
    }
}
