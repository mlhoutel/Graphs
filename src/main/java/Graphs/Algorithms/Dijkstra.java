package Graphs.Algorithms;


import java.util.*;
import java.util.Map.Entry;

import Drawing.AdjacencyList.DrawGraph;
import Drawing.GraphAlgorithms.DrawDirectedCoveringTree;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Collection.Pair;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class Dijkstra {

    private final static int MAX_VALUE = 999999999;

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
        Set<DirectedNode> to_visit = new HashSet<>();
        HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances = new HashMap<>();
        for (DirectedNode node : graph.getNodes()){
            distances.put(node, new Pair<>(null, MAX_VALUE));
            to_visit.add(node);
        }

        distances.put(source, new Pair<>(source, 0));
        to_visit.remove(source);

        DirectedNode current = source;
        //déroulement
        while (!to_visit.isEmpty()) {

            Triple<DirectedNode, DirectedNode, Integer> nearest = getLowestDistanceNode(to_visit, distances);
            current = nearest.getFirst();
            if (current == null) break; // pas possible d'accéder aux autres


            distances.put(nearest.getFirst(), new Pair<>(nearest.getSecond(), nearest.getThird()));


            for (Map.Entry<DirectedNode, Integer> succ: current.getSuccs().entrySet()) {
                Integer distance = distances.get(current).getRight() + succ.getValue();
                if (distances.get(succ.getKey()).getRight() > distance) {
                    distances.put(succ.getKey(), new Pair<>(current, distance));
                }
            }

            to_visit.remove(current);
        }

        return distances;
    }

    private static Triple<DirectedNode, DirectedNode, Integer> getLowestDistanceNode(Set<DirectedNode> to_visit, HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances) {
        Triple<DirectedNode, DirectedNode, Integer> smallest = new Triple<>(null, null,MAX_VALUE);

        for (DirectedNode node : to_visit) {
            for (Map.Entry<DirectedNode, Integer> pred: node.getPreds().entrySet()) {
                Integer distance = distances.get(pred.getKey()).getRight() + pred.getValue();
                if (smallest.getThird() > distance) {
                    smallest = new Triple<>(node, pred.getKey(), distance);
                }
            }
        }

        return smallest;
    }

    /**
     * Get the Shortest Path from source to destination with the Dijkstra algorithm for DirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @param destination the DirectedNode to go to
     * @throws DijkstraException if no path
     * @return shortest path from source to destination as a List
     */
    public static List<DirectedNode> ShortestPath(DirectedGraph graph, DirectedNode source, DirectedNode destination) throws DijkstraException {

        HashMap<DirectedNode, Pair<DirectedNode, Integer>> shortestPaths = Dijkstra(graph, source);

        System.out.println(shortestPaths);
        List<DirectedNode> shortestPath = new LinkedList<DirectedNode>();

        DirectedNode temp = destination;
        shortestPath.add(temp);

        int cpt = graph.getNodes().size();
        while (temp != source && cpt >= 0) {
            if(shortestPaths.get(temp).getLeft() != null){
                temp = shortestPaths.get(temp).getLeft();
                shortestPath.add(temp);
            }else{
                throw new DijkstraException(DijkstraException.NO_PATH_MSG);
            }
            cpt--;
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(10, true, false, true, false, 100001);
        GraphTools.afficherMatrix(mat);

        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        // DrawGraph.Display(al);

        int From = 0;
        int To = 9;

        try {
            List<DirectedNode> path = ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));

            System.out.println(path);
            BinaryHeapEdge<DirectedNode> binh = new BinaryHeapEdge<DirectedNode>();

            for (int i = 1; i < path.size(); i++) {
                binh.insert(path.get(i - 1), path.get(i),0);
            }

            DrawDirectedCoveringTree.Display(al, binh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
