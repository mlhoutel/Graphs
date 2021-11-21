package Graphs.Algorithms;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyList.UndirectedValuedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class PRIM {

    /*
    * PRIM Algorithm for UndirectedGraph
    */
    public static BinaryHeapEdge PRIM(UndirectedGraph graph) {
        HashSet<UndirectedNode> visited = new HashSet<>();
        BinaryHeapEdge tree = new BinaryHeapEdge();

        Comparator<Triple<UndirectedNode, UndirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<UndirectedNode, UndirectedNode, Integer>> queue = new PriorityQueue<>(comparator);


        UndirectedNode base = graph.getNodes().get(0);
        visited.add(base);

        for (Map.Entry<UndirectedNode,Integer> n : base.getNeighbours().entrySet()) {
            queue.add(new Triple<>(base, n.getKey(), n.getValue()));
        }

        while (!queue.isEmpty()) {
            Triple<UndirectedNode, UndirectedNode, Integer> head = queue.remove();
            tree.insert(head.getFirst(), head.getSecond(), head.getThird());

            for (Map.Entry<UndirectedNode,Integer> n : head.getSecond().getNeighbours().entrySet()) {
                queue.add(new Triple<>(head.getSecond(), n.getKey(), n.getValue()));
            }
        }

        return tree;
    }

    /*
    * PRIM Algorithm for DirectedGraph
    */
    public static BinaryHeapEdge PRIM(DirectedGraph graph) {
        HashSet<DirectedNode> visited = new HashSet<>();
        BinaryHeapEdge tree = new BinaryHeapEdge();

        Comparator<Triple<DirectedNode, DirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<DirectedNode, DirectedNode, Integer>> queue = new PriorityQueue<>(comparator);


        DirectedNode base = graph.getNodes().get(0);
        visited.add(base);

        for (Map.Entry<DirectedNode,Integer> n : base.getSuccs().entrySet()) {
            queue.add(new Triple<>(base, n.getKey(), n.getValue()));
        }

        while (!queue.isEmpty()) {
            Triple<DirectedNode, DirectedNode, Integer> head = queue.remove();
            // tree.insert(head.getFirst(), head.getSecond(), head.getThird());

            for (Map.Entry<DirectedNode,Integer> n : head.getSecond().getSuccs().entrySet()) {
                queue.add(new Triple<>(head.getSecond(), n.getKey(), n.getValue()));
            }
        }

        return tree;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
        UndirectedValuedGraph al = new UndirectedValuedGraph(mat);
        System.out.println(al);
        System.out.println(PRIM(al));
    }  
}
