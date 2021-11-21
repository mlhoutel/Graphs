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

public class BFS {
    
    /*
     * Recursive Breadth First Search algorithm for UndirectedGraph
     */
    public static List<UndirectedNode> BFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        ArrayList<UndirectedNode> list = new ArrayList<>();
        Queue<UndirectedNode> queue = new LinkedList<UndirectedNode>();
        
        for (UndirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
                list.addAll(BFSPointerUndirected(queue, visited));
            }
        }

        return list;
    }

    public static List<UndirectedNode> BFSPointerUndirected(Queue<UndirectedNode> queue, HashSet<UndirectedNode> visited) {
        ArrayList<UndirectedNode> list = new ArrayList<>();
        if (queue.isEmpty()) { return list; }

        UndirectedNode node = queue.remove();
        list.add(node);

        for (UndirectedNode n : node.getNeighbours().keySet()) {
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
            }
        }
        
        list.addAll(BFSPointerUndirected(queue, visited));

        return list;
    }


    /*
     * Recursive Breadth First Search algorithm for DirectedGraph
     */
    public static List<DirectedNode> BFS(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        ArrayList<DirectedNode> list = new ArrayList<>();
        Queue<DirectedNode> queue = new LinkedList<DirectedNode>();
        
        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
                list.addAll(BFSPointerDirected(queue, visited));
            }
        }

        return list;
    }

   public static List<DirectedNode> BFSPointerDirected(Queue<DirectedNode> queue, HashSet<DirectedNode> visited) {
        ArrayList<DirectedNode> list = new ArrayList<>();
        if (queue.isEmpty()) { return list; }

        DirectedNode node = queue.remove();
        list.add(node);

        for (DirectedNode n : node.getSuccs().keySet()) {
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
            }
        }
        
        list.addAll(BFSPointerDirected(queue, visited));

        return list;
    }
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        System.out.println(al);
        System.out.println(BFS(al));
    }
}
