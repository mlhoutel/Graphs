package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import Drawing.AdjacencyList.DrawDirectedGraph;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyMatrix.AdjacencyMatrixDirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class
CFC {

    private CFC() {}
    
    /*
     * Computation of strongly connected component for a DirectedGraph
     * <b>complexity: 2*O(v + e) = O(v + e)</b>
     * @param graph the DirectedGraph to explore
     * @return the list of all strongly connected components in the graph
     */
    public static List<List<DirectedNode>> CFC(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {                       // O(v + e)
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFS.DFSConnected(n, visited));                 // O(v + e)
            }
        }

        DirectedGraph igraph = graph.computeInverse();

        Collections.reverse(list);

        List<DirectedNode> decreasing = new ArrayList<>();
        for (DirectedNode n : list) {                                   // O(v)
            decreasing.add(igraph.getNodes().get(n.getLabel()));
        }

        List<List<DirectedNode>> connex = new ArrayList<>();
         for (DirectedNode n : decreasing) {                            // O(v + e)
            List<DirectedNode> current_connex = new ArrayList<>();
            if (!visited.contains(n)) {
                visited.add(n);
                current_connex.addAll(DFS.DFSConnected(n, visited));       // O(v + e)
            }

            if (!current_connex.isEmpty()) {
                connex.add(current_connex);
            }
        }

        return connex;
    }

    public static List<List<DirectedNode>> CFC(AdjacencyMatrixDirectedGraph matrix) {
        
        HashSet<Integer> visited = new HashSet<Integer>();
        List<DirectedNode> list = new ArrayList<>();

        /*for (DirectedNode n : matrix.getNodes()) {                       // O(v + e)
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFS.DFSConnected(n, visited));                 // O(v + e)
            }
        }*/
        boolean[] visites = new boolean[matrix.getNbNodes()];
        Arrays.fill(visites, false);
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < matrix.getNbNodes(); i++) { // O(v)

            for (int j = 0; j < matrix.getNbNodes(); j++) { // O(v)
            
            if (matrix.getMatrix()[i][j] != 0 && (!visites[j])) {
                visited.add(j);
            }
        }
        }
        
        AdjacencyMatrixDirectedGraph igraph = new AdjacencyMatrixDirectedGraph(matrix.computeInverse());

        Collections.reverse(list);

        List<DirectedNode> decreasing = new ArrayList<>();
        for (DirectedNode n : list) {                                   // O(v)
            decreasing.add(igraph.getNodes().get(n.getLabel()));
        }

        List<List<DirectedNode>> connex = new ArrayList<>();
         for (DirectedNode n : decreasing) {                            // O(v + e)
            List<DirectedNode> current_connex = new ArrayList<>();
            if (!visited.contains(n)) {
                visited.add(n);
                current_connex.addAll(DFS.DFSConnected(n, visited));       // O(v + e)
            }

            if (!current_connex.isEmpty()) {
                connex.add(current_connex);
            }
        }

        return connex;
    }

    /*
     * Computation of strongly connected component for a UndirectedGraph
     * <b>complexity: O(v + e)</b>
     * @param graph the UndirectedGraph to explore
     * @return the list of all strongly connected components in the graph
     */
    public static List<List<UndirectedNode>> CFC(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();

        List<List<UndirectedNode>> connex = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {                             // O(v + e)
            List<UndirectedNode> current_connex = new ArrayList<>();
            if (!visited.contains(n)) {
                visited.add(n);
                current_connex.addAll(DFS.DFSConnected(n, visited));           // O(v + e)
            }

            if (!current_connex.isEmpty()) {
                connex.add(current_connex);
            }
        }

        return connex;
    }
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        // UndirectedGraph al = new UndirectedGraph(mat);
        System.out.println(al);
        System.out.println(CFC(al));
        DrawDirectedGraph.Display(al);
    }

}
