package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class CFC {
    
    /*
     * Calcul des composants fortement connexes pour un DirectedGraph
     */
    public static List<List<DirectedNode>> CFC(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFS.DFSConvex(n, visited));
            }
        }

        DirectedGraph igraph = graph.computeInverse();

        Collections.reverse(list);
        
        List<DirectedNode> decreasing = new ArrayList<>();
        for (DirectedNode n : list) {
            decreasing.add(igraph.getNodes().get(n.getLabel()));
        }

        List<List<DirectedNode>> connex = new ArrayList<>();
         for (DirectedNode n : decreasing) {
            List<DirectedNode> current_connex = new ArrayList<>();
            if (!visited.contains(n)) {
                visited.add(n);
                current_connex.addAll(DFS.DFSConvex(n, visited));
            }

            if (!current_connex.isEmpty()) {
                connex.add(current_connex);
            }
        }

        return connex;
    }

     
    /*
     * Calcul des composants fortement connexes pour un UndirectedGraph
     */
    public static List<List<UndirectedNode>> CFC(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();

        List<List<UndirectedNode>> connex = new ArrayList<>();
        for (UndirectedNode n : graph.getNodes()) {
            List<UndirectedNode> current_connex = new ArrayList<>();
            if (!visited.contains(n)) {
                visited.add(n);
                current_connex.addAll(DFS.DFSConvex(n, visited));
            }

            if (!current_connex.isEmpty()) {
                connex.add(current_connex);
            }
        }

        return connex;
    }
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        // UndirectedGraph al = new UndirectedGraph(mat);
        System.out.println(al);
        System.out.println(CFC(al));
    }

}
