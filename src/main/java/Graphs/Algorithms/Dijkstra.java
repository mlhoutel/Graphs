package Graphs.Algorithms;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;

public class Dijkstra {
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
    }  
}
