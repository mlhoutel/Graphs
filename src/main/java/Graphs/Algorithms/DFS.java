package Graphs.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import Drawing.AdjacencyList.DrawDirectedGraph;
import Drawing.AdjacencyList.DrawUndirectedGraph;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyMatrix.AdjacencyMatrixDirectedGraph;
import Graphs.AdjacencyMatrix.AdjacencyMatrixUndirectedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class DFS {
    
    private DFS() {}

    /**
     * Recursive Depth First Search algorithm for UndirectedGraph nodes (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the UndirectedGraph to search
     * @return the list of all UndirectedNode in the graph
     */
    public static List<UndirectedNode> DFS(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        List<UndirectedNode> list = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {                         // O(v + e)
            if (!visited.contains(n)) {
                list.addAll(DFSConnected(n, visited));                      // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for UndirectedGraph nodes (connected)
     * <b>complexity: O(v + e)</b>
     * @param node the UndirectedNode to search
     * @param visited the Set of already visited UndirectedNode
     * @return the list of all UndirectedNode in the graph
     */
    public static List<UndirectedNode> DFSConnected(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
        ArrayList<UndirectedNode> list = new ArrayList(Arrays.asList(node));

        visited.add(node);

        for (UndirectedNode n : node.getNeighbours().keySet()) {                        // O(e)
            if (!visited.contains(n)) {
                list.addAll(DFSConnected(n, visited));                                  // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for DirectedGraph nodes (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the DirectedGraph to search
     * @return the list of all DirectedNode in the graph
     */
    public static List<DirectedNode> DFS(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<DirectedNode> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {                       // O(v + e)
            if (!visited.contains(n)) {
                list.addAll(DFSConnected(n, visited));                  // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for DirectedGraph nodes (connected)
     * <b>complexity: O(v + e)</b>
     * @param node the DirectedNode to search
     * @param visited the Set of already visited DirectedNode
     * @return the list of all DirectedNode in the graph
     */
    public static List<DirectedNode> DFSConnected(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<DirectedNode> list = new ArrayList(Arrays.asList(node));

        visited.add(node);

        for (DirectedNode n : node.getSuccs().keySet()) {                       // O(e)
            if (!visited.contains(n)) {
                list.addAll(DFSConnected(n, visited));                          // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for DirectedGraph edges (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the DirectedGraph to search
     * @return the list of all Edges in the graph
     */
    public static List<Triple<DirectedNode, DirectedNode, Integer>> DFSEdges(DirectedGraph graph) {
        
        HashSet<DirectedNode> visited = new HashSet<DirectedNode>();
        List<Triple<DirectedNode, DirectedNode, Integer>> list = new ArrayList<>();

        for (DirectedNode n : graph.getNodes()) {                      // O(v + e)
            if (!visited.contains(n)) {
                list.addAll(DFSEdgesConnected(n, visited));            // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for DirectedGraph edges (connected)
     * <b>complexity: O(v + e)</b>
     * @param node the DirectedNode to search
     * @param visited the Set of already visited DirectedNode
     * @return the list of all Edges in the graph
     */
    public static List<Triple<DirectedNode, DirectedNode, Integer>> DFSEdgesConnected(DirectedNode node, HashSet<DirectedNode> visited) {
        
        ArrayList<Triple<DirectedNode, DirectedNode, Integer>> list = new ArrayList<>();

        visited.add(node);

        for (Map.Entry<DirectedNode, Integer> n : node.getSuccs().entrySet()) {             // O(e)
            list.add(new Triple<>(node, n.getKey(), n.getValue()));

            if (!visited.contains(n.getKey())) {
                list.addAll(DFSEdgesConnected(n.getKey(), visited));                        // O(v + e)
            }
        }

        return list;
    }


    /**
     * Recursive Depth First Search algorithm for UndirectedGraph edges (connected/disconnected)
     * <b>complexity: O(v + e)</b>
     * @param graph the UndirectedGraph to search
     * @return the list of all Edges in the graph
     */
    public static List<Triple<UndirectedNode, UndirectedNode, Integer>> DFSEdges(UndirectedGraph graph) {
        
        HashSet<UndirectedNode> visited = new HashSet<UndirectedNode>();
        List<Triple<UndirectedNode, UndirectedNode, Integer>> list = new ArrayList<>();

        for (UndirectedNode n : graph.getNodes()) {                     // O(v + e)
            if (!visited.contains(n)) {
                list.addAll(DFSEdgesConnected(n, visited));             // O(v + e)
            }
        }

        return list;
    }

    /**
     * Recursive Depth First Search algorithm for UndirectedGraph edges (connected)
     * <b>complexity: O(v + e)</b>
     * @param node the UndirectedNode to search
     * @param visited the Set of already visited UndirectedNode
     * @return the list of all Edges in the graph
     */
    public static List<Triple<UndirectedNode, UndirectedNode, Integer>> DFSEdgesConnected(UndirectedNode node, HashSet<UndirectedNode> visited) {
        
        ArrayList<Triple<UndirectedNode, UndirectedNode, Integer>> list = new ArrayList<>();

        visited.add(node);

        for (Map.Entry<UndirectedNode, Integer> n : node.getNeighbours().entrySet()) {           // O(e)
            
            if (node.getLabel() < n.getKey().getLabel()) {
                list.add(new Triple<>(node, n.getKey(), n.getValue()));
            }

            if (!visited.contains(n.getKey())) {
                list.addAll(DFSEdgesConnected(n.getKey(), visited));                            // O(v + e)
            }
        }

        return list;
    }


    /**
     * Depth First Search algorithm for Adjacency Matrix Directed Graph 
     * <b>complexity: O(v^2)</b>
     * @param matrix the AdjacencyMatrixDirectedGraph to search
     * @param start the strating index
     * @param visites visited nodes
     * @param res nodes that still have to be visited
     * @return the list of all nodes (indexes) in the graph
     */
    public static List<Integer> DFSMatrix(AdjacencyMatrixDirectedGraph matrix, int start, boolean[] visites, List<Integer> res) {
        //visite
        res.add(start);
        visites[start] = true;

        // boucle
        for (int i = 0; i < matrix.getNbNodes(); i++) {                 // O(v^2)
            if (matrix.getMatrix()[start][i] != 0 && (!visites[i])) {
                DFSMatrix(matrix, i, visites, res);                     // O(v)
            }
        }
        return res;
    }

    /**
     * Depth First Search algorithm for Adjacency Matrix Undirected Graph
     * <b>complexity: O(v^2)</b>
     * @param matrix the AdjacencyMatrixDirectedGraph to search
     * @param start the strating index
     * @param visites visited nodes
     * @param res nodes that still have to be visited
     * @return the list of all nodes (indexes) in the graph
     */
    public static List<Integer> DFSMatrix(AdjacencyMatrixUndirectedGraph matrix, int start, boolean[] visites, List<Integer> res) {
        //visite
        res.add(start);
        visites[start] = true;

        // boucle
        for (int i = 0; i < matrix.getNbNodes(); i++) {                 // O(v^2)
            if (matrix.getMatrix()[start][i] != 0 && (!visites[i])) {   // TODO: empecher duplicatas (2 sens)
                DFSMatrix(matrix, i, visites, res);                     // O(v)
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        // DirectedGraph al = new DirectedGraph(mat);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(al);
        System.out.println(DFS(al));
        System.out.println(DFSEdges(al));

        DrawDirectedGraph.Display(al);

        AdjacencyMatrixDirectedGraph amd = new AdjacencyMatrixDirectedGraph(al);

        boolean[] visites = new boolean[amd.getNbNodes()];
        Arrays.fill(visites, false);
        List<Integer> res = new ArrayList<>();
        System.out.println("DFSMatrix:" + DFSMatrix(amd,0,visites,res));
    }
}
