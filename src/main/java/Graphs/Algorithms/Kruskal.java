package Graphs.Algorithms;


import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;

public class Kruskal {

    /*
    * PRIM Algorithm for UndirectedGraph
    */
    public static BinaryHeapEdge PRIM(UndirectedGraph graph) {
        
        BinaryHeapEdge tree = new BinaryHeapEdge();
        return tree;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
    }  
}
