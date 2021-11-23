package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyList.UndirectedValuedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class BFS {

    private BFS() {}
    
    /*
     * Recursive Breadth First Search algorithm for UndirectedGraph nodes (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the UndirectedGraph to visit
     * @return the list of all UndirectedNode in the graph
     */
    public static List<UndirectedNode> BFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        ArrayList<UndirectedNode> list = new ArrayList<>();
        Queue<UndirectedNode> queue = new LinkedList<UndirectedNode>();
        
        for (UndirectedNode n : graph.getNodes()) {                 // O(v + e)
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
                list.addAll(BFSConnectedUndirected(queue, visited));   // O(v + e)
            }
        }

        return list;
    }

    /*
     * Recursive Breadth First Search algorithm for UndirectedGraph nodes (connected)
     * <b>complexity: O(v + e)</b>
     * @param queue the UndirectedNodes waiting to be visited 
     * @param visited the Set of already visited UndirectedNode
     * @return the list of all UndirectedNode in the graph
     */
    public static List<UndirectedNode> BFSConnectedUndirected(Queue<UndirectedNode> queue, HashSet<UndirectedNode> visited) {
        ArrayList<UndirectedNode> list = new ArrayList<>();
        if (queue.isEmpty()) { return list; }

        UndirectedNode node = queue.remove();
        list.add(node);

        for (UndirectedNode n : node.getNeighbours().keySet()) {    // O(v)
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
            }
        }
        
        list.addAll(BFSConnectedUndirected(queue, visited));          // O(e)

        return list;
    }


    /*
     * Recursive Breadth First Search algorithm for DirectedGraph nodes (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the DirectedGraph to search
     * @return the list of all DirectedNode in the graph
     */
    public static List<DirectedNode> BFS(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        ArrayList<DirectedNode> list = new ArrayList<>();
        Queue<DirectedNode> queue = new LinkedList<DirectedNode>();
        
        for (DirectedNode n : graph.getNodes()) {                   // O(v + e)
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
                list.addAll(BFSConnectedDirected(queue, visited));     // O(v + e)
            }
        }

        return list;
    }

    /*
     * Recursive Breadth First Search algorithm for DirecteGraph nodes (connected)
     * @complexity O(v + e)
     * @param queue the DirectedNode waiting to be visited 
     * @param visited the Set of already visited DirectedNode
     * @return the list of all DirectedNode in the graph
     */
   public static List<DirectedNode> BFSConnectedDirected(Queue<DirectedNode> queue, HashSet<DirectedNode> visited) {
        ArrayList<DirectedNode> list = new ArrayList<>();
        if (queue.isEmpty()) { return list; }

        DirectedNode node = queue.remove();
        list.add(node);

        for (DirectedNode n : node.getSuccs().keySet()) {          //O(v)
            if (!visited.contains(n)) {
                visited.add(n);
                queue.add(n);
            }
        }
        
        list.addAll(BFSConnectedDirected(queue, visited));          //O(e)

        return list;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        // DirectedGraph al = new DirectedGraph(mat);
        UndirectedValuedGraph al = new UndirectedValuedGraph(mat);
        System.out.println(al);
        System.out.println(BFS(al));
    }
}
