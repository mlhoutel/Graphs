package Graphs.Algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.Collection.Pair;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Drawing.AdjacencyList.DrawDirectedGraph;

public class Bellman {

    
    public static HashMap<DirectedNode, Pair<DirectedNode, Integer>> calculateShortestPathFromSource(DirectedGraph graph, DirectedNode source) {

        // Distance from source to node, with pred and value
        HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances = new HashMap<>();
        for (DirectedNode node : graph.getNodes()){
            distances.put(node, new Pair(new DirectedNode(-1), Integer.MAX_VALUE));
        }

        distances.put(source, new Pair(source, 0));
;
        for (DirectedNode node : graph.getNodes()) {
            for (Map.Entry<DirectedNode, Integer> succ : node.getSuccs().entrySet()) {
                Integer temp = distances.get(succ.getKey()).getRight() + succ.getValue();

                if (temp < distances.get(node).getRight()) {
                    distances.put(node, new Pair(succ.getKey(), temp));
                }
            }

            for (Map.Entry<DirectedNode, Integer> succ : node.getSuccs().entrySet()) {
                if (distances.get(succ.getKey()).getRight() + succ.getValue() < distances.get(node).getRight()) {
                    throw new RuntimeException("Boucle négative...");
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(al);

        System.out.println(calculateShortestPathFromSource(al, al.getNodes().get(0)));
    }  
}
