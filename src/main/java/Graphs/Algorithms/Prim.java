package Graphs.Algorithms;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

import Drawing.AdjacencyList.DrawGraph;
import Drawing.GraphAlgorithms.DrawBinaryHeapEdge;
import Drawing.GraphAlgorithms.DrawDirectedCoveringTree;
import Drawing.GraphAlgorithms.DrawUndirectedCoveringTree;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyList.UndirectedValuedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class Prim {

    private Prim() {}

    /**
    * Prim Algorithm for UndirectedGraph
    * <b>complexity: O(V Log E)</b>
    * @param graph the UndirectedGraph to explore
    * @return the minimum spanning tree as a BinaryHeapEdge
    */
    public static BinaryHeapEdge Prim(UndirectedGraph graph) {

        HashSet<UndirectedNode> visited = new HashSet<>();
        BinaryHeapEdge<UndirectedNode> tree = new BinaryHeapEdge<UndirectedNode>();

        Comparator<Triple<UndirectedNode, UndirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<UndirectedNode, UndirectedNode, Integer>> queue = new PriorityQueue<>(comparator);

        UndirectedNode base = graph.getNodes().get(0);
        visited.add(base);

        for (Map.Entry<UndirectedNode,Integer> n : base.getNeighbours().entrySet()) {
            queue.add(new Triple<>(base, n.getKey(), n.getValue()));                                    // O(V Log E)
        }

        while (!queue.isEmpty()) {                                                                       // O(V Log E)
            Triple<UndirectedNode, UndirectedNode, Integer> head = queue.remove();

            if (!visited.contains(head.getSecond())) {
                visited.add(head.getSecond());

                tree.insert(head.getFirst(), head.getSecond(), head.getThird());

                for (Map.Entry<UndirectedNode,Integer> n : head.getSecond().getNeighbours().entrySet()) {
                    queue.add(new Triple<>(head.getSecond(), n.getKey(), n.getValue()));                 // O(V Log E)
                }
            }
        }

        return tree;
    }

    /**
     * Prim Algorithm for UndirectedGraph
     * <b>complexity: O(V Log E)</b>
     * @param graph the DirectedGraph to explore
     * @return the minimum spanning tree as a BinaryHeapEdge
     */
    public static BinaryHeapEdge Prim(DirectedGraph graph) {

        HashSet<DirectedNode> visited = new HashSet<>();
        BinaryHeapEdge<DirectedNode> tree = new BinaryHeapEdge();

        Comparator<Triple<DirectedNode, DirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<DirectedNode, DirectedNode, Integer>> queue = new PriorityQueue<>(comparator);

        DirectedNode base = graph.getNodes().get(0);
        visited.add(base);

        for (Map.Entry<DirectedNode,Integer> n : base.getSuccs().entrySet()) {
            queue.add(new Triple<>(base, n.getKey(), n.getValue()));                                // O(V Log E)
        }

        while (!queue.isEmpty()) {                                                                  // O(V Log E)
            Triple<DirectedNode, DirectedNode, Integer> head = queue.remove();
            
            if (!visited.contains(head.getSecond()) || !visited.contains(head.getFirst())) {
                visited.add(head.getSecond());

                tree.insert(head.getFirst(), head.getSecond(), head.getThird());

                for (Map.Entry<DirectedNode,Integer> n : head.getSecond().getSuccs().entrySet()) {
                    queue.add(new Triple<>(head.getSecond(), n.getKey(), n.getValue()));             // O(V Log E)
                }
            }
        }

        return tree;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(8, false, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        GraphTools.afficherArraysMatrix(mat);
        //DirectedValuedGraph al = new DirectedValuedGraph(mat);
        //System.out.println(al);
        BinaryHeapEdge binh = Prim(al);
        
        //DrawGraph.Display(al);
        //DrawBinaryHeapEdge.Display(binh);
        //DrawDirectedCoveringTree.Display(al, binh);

        //DrawGraph.Display(al);
        //DrawBinaryHeapEdge.Display(binh);
        DrawDirectedCoveringTree.Display(al, binh);
        System.out.println(binh);
    }
}
