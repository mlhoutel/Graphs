package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class DFS {
     /*
     * Recursive Depth First Search algorithm for UndirectedGraph
     */
    public static List<UndirectedNode> DFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        List<UndirectedNode> list = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
            }
        }

        return list;
    }

    public static List<UndirectedNode> DFSPointer(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
        ArrayList<UndirectedNode> list = new ArrayList(Arrays.asList(node));

        for (UndirectedNode n : node.getNeighbours().keySet()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
            }
        }

        return list;
    }

    
    /*
     * Recursive Depth First Search algorithm for DirectedGraph
     */
    public static List<DirectedNode> DFS(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
            }
        }

        return list;
    }

    public static List<DirectedNode> DFSPointer(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<DirectedNode> list = new ArrayList(Arrays.asList(node));

        for (DirectedNode n : node.getSuccs().keySet()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
            }
        }

        return list;
    }
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        System.out.println(al);
        System.out.println(DFS(al));
    }
}
