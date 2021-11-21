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
import Graphs.Nodes.UndirectedNode;
import Drawing.AdjacencyList.DrawDirectedGraph;

public class Bellman {

    
    public static HashMap<DirectedNode, Pair<DirectedNode, Integer>> calculateShortestPathFromSource(DirectedGraph graph, DirectedNode source) {

        // Distance from source to node, with pred and value
        HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances = new HashMap<>();
        for (DirectedNode node : graph.getNodes()){
            distances.put(node, new Pair(new DirectedNode(-1), 999999999));
        }

        distances.put(source, new Pair(source, 0));
;
        for (DirectedNode node : graph.getNodes()) {
            System.out.println("-"+node);
            for(int i=0;i<graph.getNbNodes()-1;i++)
            {
              for(int j=0;j<graph.getNbArcs();j++)
              {
                //TODO
              }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(al);

        DrawDirectedGraph.Display(al);
        System.out.println(calculateShortestPathFromSource(al, al.getNodes().get(0)));
    }  
}
