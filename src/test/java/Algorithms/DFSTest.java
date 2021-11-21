package Algorithms;

import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyMatrix.AdjacencyMatrixUndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;
import Graphs.Algorithms.DFS;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.jupiter.api.Assertions;

public class DFSTest {

    @Test
    void DFS() {
        //GIVEN
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        UndirectedGraph al = new UndirectedGraph(mat);

        //WHEN
        List<UndirectedNode> result = DFS.DFS(al);
        List<Integer> labels = new ArrayList<Integer>();
        for (UndirectedNode node : result) {
            labels.add(node.getLabel());
        }

        //THEN
        Assertions.assertEquals(labels, new ArrayList(Arrays.asList(0,2,1,4,3)));
    }
}
