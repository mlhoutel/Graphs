package Graphs.GraphAlgorithms;

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
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class Algorithms {
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

    private static List<UndirectedNode> DFSPointer(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
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

    private static List<DirectedNode> DFSPointer(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<DirectedNode> list = new ArrayList(Arrays.asList(node));

        for (DirectedNode n : node.getSuccs().keySet()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
            }
        }

        return list;
    }

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

    private static List<UndirectedNode> BFSPointerUndirected(Queue<UndirectedNode> queue, HashSet<UndirectedNode> visited) {
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

   private static List<DirectedNode> BFSPointerDirected(Queue<DirectedNode> queue, HashSet<DirectedNode> visited) {
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


    /*
     * Calcul des composants fortement connexes
     */
    public static List<List<DirectedNode>> CFC(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                list.addAll(DFSPointer(n, visited));
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
                current_connex.addAll(DFSPointer(n, visited));
            }
            connex.add(current_connex);
        }

        return connex;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
        System.out.println(al);
        System.out.println(CFC(al));
    }
}
