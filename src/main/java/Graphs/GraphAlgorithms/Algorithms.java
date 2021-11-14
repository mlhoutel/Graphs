package Graphs.GraphAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Nodes.UndirectedNode;

public class Algorithms {
    /*
     * Recursive Depth First Search algorithm
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
     * Recursive Breadth First Search algorithm
     */
    public static List<UndirectedNode> BFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        ArrayList<UndirectedNode> list = new ArrayList<>();
        Queue<UndirectedNode> queue = new LinkedList<UndirectedNode>();
        
        for (UndirectedNode n : graph.getNodes()) {
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
                list.addAll(BFSPointer(queue, visited));
            }
        }

        return list;
    }

    private static List<UndirectedNode> BFSPointer(Queue<UndirectedNode> queue, HashSet<UndirectedNode> visited) {
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
        
        list.addAll(BFSPointer(queue, visited));

        return list;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        UndirectedGraph al = new UndirectedGraph(mat);
        System.out.println(al);
        System.out.println(BFS(al));
    }
}
