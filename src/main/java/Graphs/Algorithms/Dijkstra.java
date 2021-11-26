package Graphs.Algorithms;


import java.util.*;
import java.util.Map.Entry;

import Drawing.AdjacencyList.DrawDirectedGraph;
import Drawing.GraphAlgorithms.DrawDirectedCoveringTree;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Collection.Pair;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class Dijkstra {

    private Dijkstra() {}

    /**
     * Dijkstra Algorithm for DirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @return all nearest nodes and their cost
     */
    public static HashMap<DirectedNode, Pair<DirectedNode, Integer>> Dijkstra(DirectedGraph graph, DirectedNode source) {

        //initialisation
        HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances = new HashMap<>();
        for (DirectedNode node : graph.getNodes()){
            distances.put(node, new Pair<>(null, 999999999));
        }

        distances.put(source, new Pair<>(source, 0));

        Set<DirectedNode> settledNodes = new HashSet<>();
        Set<DirectedNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        //déroulement
        while (!unsettledNodes.isEmpty()) {

            DirectedNode currentNode = getLowestDistanceNode(unsettledNodes, distances);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<DirectedNode, Integer> adjacencyPair: currentNode.getSuccs().entrySet()) {
                DirectedNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    Integer distance = distances.get(currentNode).getRight() + edgeWeight;
                    if (distance < distances.get(adjacentNode).getRight()) {
                        distances.put(adjacentNode, new Pair<>(distances.get(adjacentNode).getLeft(), distance));
                    }
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }

        return distances;
    }

    private static DirectedNode getLowestDistanceNode(Set<DirectedNode> unsettledNodes, HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances) {
        DirectedNode lowestDistanceNode = null;
        Integer lowestDistance = Integer.MAX_VALUE;

        for (DirectedNode node: unsettledNodes) {
            Integer nodeDistance = distances.get(node).getRight();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    /**
     * Get the Shortest Path from source to destination with the Dijkstra algorithm for DirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @param destination the DirectedNode to go to
     * @return shortest path from source to destination as a List
     */
    public static List<DirectedNode> ShortestPath(DirectedGraph graph, DirectedNode source, DirectedNode destination) {

        HashMap<DirectedNode, Pair<DirectedNode, Integer>> shortestPaths = Dijkstra(graph, destination);
        List<DirectedNode> shortestPath = new LinkedList<DirectedNode>();

        shortestPath.add(source);
        DirectedNode temp = source;

        int cpt = graph.getNodes().size();
        while (temp != destination && cpt >= 0) {
            temp = shortestPaths.get(temp).getLeft();
            shortestPath.add(temp);
            cpt--;
        }

        return shortestPath;
    }

    /**
     * Get the Shortest Path from source to destination with the Dijkstra algorithm for UndirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the UndirectedNode to explore
     * @param source the UndirectedNode to go from
     * @param destination the UndirectedNode to go to
     * @return shortest path from source to destination as a List
     */

    public static List<UndirectedNode> ShortestPath(UndirectedGraph graph, UndirectedNode source, UndirectedNode destination) {

        // HashMap<UndirectedNode, Pair<UndirectedNode, Integer>> shortestPaths = Dijkstra(graph, destination);
        List<UndirectedNode> shortestPath = new LinkedList<UndirectedNode>();

        shortestPath.add(source);
        UndirectedNode temp = source;
        /*
        int cpt = graph.getNodes().size();
        while (temp != destination && cpt >= 0) {
            temp = shortestPaths.get(temp).getLeft();
            shortestPath.add(temp);
            cpt--;
        }
        */
        return shortestPath;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(10, true, false, true, false, 100001);
        GraphTools.afficherMatrix(mat);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        DrawDirectedGraph.Display(al);

        System.out.println(Dijkstra(al, al.getNodes().get(0)));

        for(DirectedNode src : al.getNodes()) {
            for(DirectedNode dest : al.getNodes()) {
                System.out.println(ShortestPath(al ,src ,dest));
            }
        }

        int From = 7;
        int To = 0;

        List<DirectedNode> path = ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));
        BinaryHeapEdge<DirectedNode> binh = new BinaryHeapEdge<DirectedNode>();

        for (int i = 1; i < path.size(); i++) {
            binh.insert(path.get(i - 1), path.get(i),0);
        }

        DrawDirectedCoveringTree.Display(al, binh);
    }
}
