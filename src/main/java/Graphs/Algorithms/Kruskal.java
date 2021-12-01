package Graphs.Algorithms;


import java.util.*;

import Drawing.GraphAlgorithms.DrawDirectedCoveringTree;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class Kruskal {

    private Kruskal() {}

    /**
    * Kruskal Algorithm for UndirectedGraph
     * <b>complexity: O(E log E)</b>
     * @param graph the UndirectedGraph to explore
     * @return the minimum spanning tree as a BinaryHeapEdge
     */
    public static BinaryHeapEdge Kruskal(UndirectedGraph graph) {

        BinaryHeapEdge<UndirectedNode> tree = new BinaryHeapEdge();
        HashSet<UndirectedNode> visited = new HashSet<>();

        Comparator<Triple<UndirectedNode, UndirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<UndirectedNode, UndirectedNode, Integer>> queue = new PriorityQueue<>(comparator);

        List<Triple<UndirectedNode, UndirectedNode, Integer>> edges = DFS.DFSEdges(graph);

        for (Triple<UndirectedNode, UndirectedNode, Integer> edge : edges) {
            queue.add(edge);                                                        // O (E log E)
        }

        while(!queue.isEmpty()) {                                                   // O (V)
            Triple<UndirectedNode, UndirectedNode, Integer> edge = queue.remove();

            if (!visited.contains(edge.getFirst()) || !visited.contains(edge.getSecond())) {
                visited.add(edge.getFirst());
                visited.add(edge.getSecond());
                tree.insert(edge.getFirst(), edge.getSecond(), edge.getThird());
            }
        }

        return tree;
    }

    /**
    * Kruskal Algorithm for DirectedGraph
     * <b>complexity: O(E log E)</b>
     * @param graph the DirectedGraph to explore
     * @return the minimum spanning tree as a BinaryHeapEdge
     */
    public static BinaryHeapEdge Kruskal(DirectedGraph graph) {

        BinaryHeapEdge<DirectedNode> tree = new BinaryHeapEdge<>();
        HashMap<DirectedNode, HashSet<DirectedNode>> subsets = new HashMap<>();

        // Initilize sets of visited for each node
        for (DirectedNode node : graph.getNodes()) {
            subsets.put(node, new HashSet<>(Arrays.asList(node))); // only the current node is reachable
        }

        Comparator<Triple<DirectedNode, DirectedNode, Integer>> comparator = (p1, p2) -> { return p1.getThird() - p2.getThird(); };
        PriorityQueue<Triple<DirectedNode, DirectedNode, Integer>> queue = new PriorityQueue<>(comparator);

        List<Triple<DirectedNode, DirectedNode, Integer>> edges = DFS.DFSEdges(graph);

        for (Triple<DirectedNode, DirectedNode, Integer> edge : edges) {
            queue.add(edge);                                                                // O (E log E)
        }

        while(!queue.isEmpty()) {                                                           // O (V)
            Triple<DirectedNode, DirectedNode, Integer> edge = queue.remove();

            // TODO: fix
            if (!subsets.get(edge.getFirst()).contains(edge.getSecond()) && !subsets.get(edge.getSecond()).contains(edge.getFirst())) {
                ArrayList<DirectedNode> subsetB = new ArrayList<>(subsets.get(edge.getSecond()));
                ArrayList<DirectedNode> subsetA = new ArrayList<>(subsets.get(edge.getFirst()));
                subsets.get(edge.getFirst()).addAll(subsetB);
                subsets.get(edge.getSecond()).addAll(subsetA);

                tree.insert(edge.getFirst(), edge.getSecond(), edge.getThird());
            }
        }

        return tree;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(8, false, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        //DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(al);
        BinaryHeapEdge binh = Kruskal(al);
        System.out.println(binh);
        DrawDirectedCoveringTree.Display(al, binh);
    }
}
