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
import Graphs.Algorithms.CFC;

public class CFCTest {

    @Test
    void CFC() {
        //GIVEN
        int[][] mat = new int [][] {
            { 0, 0, 1, 0, 1 },
            { 0, 0, 1, 0, 1 },
            { 1, 1, 0, 1, 0 },
            { 0, 0, 1, 0, 1 },
            { 1, 1, 0, 1, 0 },
        };

        UndirectedGraph al = new UndirectedGraph(mat);

        //WHEN
        List<List<UndirectedNode>> result = CFC.CFC(al);
        List<List<Integer>> labels = new ArrayList<>();
        
        for (List<UndirectedNode> connected : result) {
            List<Integer> clabels = new ArrayList<Integer>();
            for (UndirectedNode node : connected) {
                clabels.add(node.getLabel());
            }
            labels.add(clabels);
        }
        
        List<List<Integer>> trueValues = new ArrayList<>();
        trueValues.add(new ArrayList<>(Arrays.asList(0, 2, 1, 4, 3)));
        //THEN
        Assertions.assertEquals(labels, trueValues);
    }
}
