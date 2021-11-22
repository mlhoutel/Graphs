package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyList.UndirectedValuedGraph;
import Graphs.Collection.Pair;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class DFS {
     /*
     * Recursive Depth First Search algorithm for UndirectedGraph nodes (convex or not)
     */
    public static List<UndirectedNode> DFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        List<UndirectedNode> list = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                list.addAll(DFSConvex(n, visited));
            }
        }

        return list;
    }

    /*
     * Recursive Depth First Search algorithm for UndirectedGraph nodes (convex)
     */
    public static List<UndirectedNode> DFSConvex(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
        ArrayList<UndirectedNode> list = new ArrayList(Arrays.asList(node));

        visited.add(node);

        for (UndirectedNode n : node.getNeighbours().keySet()) {
            if (!visited.contains(n)) {
                list.addAll(DFSConvex(n, visited));
            }
        }

        return list;
    }
    
    /*
     * Recursive Depth First Search algorithm for DirectedGraph nodes (convex or not)
     */
    public static List<DirectedNode> DFS(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                list.addAll(DFSConvex(n, visited));
            }
        }

        return list;
    }

    /*
     * Recursive Depth First Search algorithm for DirectedGraph nodes (convex)
     */
    public static List<DirectedNode> DFSConvex(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<DirectedNode> list = new ArrayList(Arrays.asList(node));

        visited.add(node);

        for (DirectedNode n : node.getSuccs().keySet()) {
            if (!visited.contains(n)) {
                list.addAll(DFSConvex(n, visited));
            }
        }

        return list;
    }

    /*
     * Recursive Depth First Search algorithm for DirectedGraph edges (convex or not)
     */
    public static List<Triple<DirectedNode, DirectedNode, Integer>> DFSEdges(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<Triple<DirectedNode, DirectedNode, Integer>> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                list.addAll(DFSEdgesConvex(n, visited));
            }
        }

        return list;
    }

    /*
     * Recursive Depth First Search algorithm for DirectedGraph edges (convex)
     */
    public static List<Triple<DirectedNode, DirectedNode, Integer>> DFSEdgesConvex(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<Triple<DirectedNode, DirectedNode, Integer>> list = new ArrayList<>();

        visited.add(node);

        for (Map.Entry<DirectedNode, Integer> n : node.getSuccs().entrySet()) {
            list.add(new Triple<>(node, n.getKey(), n.getValue()));

            if (!visited.contains(n.getKey())) {
                list.addAll(DFSEdgesConvex(n.getKey(), visited));
            }
        }

        return list;
    }

     /*
     * Recursive Depth First Search algorithm for UnirectedGraph edges (convex or not)
     */
    public static List<Triple<UndirectedNode, UndirectedNode, Integer>> DFSEdges(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        List<Triple<UndirectedNode, UndirectedNode, Integer>> list = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                list.addAll(DFSEdgesConvex(n, visited));
            }
        }

        return list;
    }

    /*
     * Recursive Depth First Search algorithm for UnirectedGraph edges (convex)
     */
    public static List<Triple<UndirectedNode, UndirectedNode, Integer>> DFSEdgesConvex(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
        ArrayList<Triple<UndirectedNode, UndirectedNode, Integer>> list = new ArrayList<>();

        visited.add(node);

        for (Map.Entry<UndirectedNode, Integer> n : node.getNeighbours().entrySet()) {
            
            if (node.getLabel() < n.getKey().getLabel()) {
                list.add(new Triple<>(node, n.getKey(), n.getValue()));
            }

            if (!visited.contains(n.getKey())) {
                list.addAll(DFSEdgesConvex(n.getKey(), visited));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        // DirectedGraph al = new DirectedGraph(mat);
        UndirectedValuedGraph al = new UndirectedValuedGraph(mat);
        System.out.println(al);
        System.out.println(DFS(al));
        System.out.println(DFSEdges(al));
    }
}
