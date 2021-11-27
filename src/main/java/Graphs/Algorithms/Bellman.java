package Graphs.Algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Drawing.AdjacencyList.DrawGraph;
import Drawing.GraphAlgorithms.DrawDirectedCoveringTree;
import Drawing.GraphAlgorithms.DrawUndirectedCoveringTree;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.AdjacencyList.UndirectedValuedGraph;
import Graphs.Collection.Pair;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public final class Bellman {

    private final static int MAX_VALUE = 99999999;

    private Bellman() {}

    /**
     * Bellman Algorithm for DirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @throws Exception if negative cycles
     * @return all nearest nodes and their cost
     */
    public static HashMap<DirectedNode, Pair<DirectedNode, Integer>> Bellman(DirectedGraph graph, DirectedNode source) throws BellmanException {

        // Distance depuis source à node, avec prédécesseur et value
        HashMap<DirectedNode, Pair<DirectedNode, Integer>> distances = new HashMap<>();
        for (DirectedNode node : graph.getNodes()){
            distances.put(node, new Pair<>(null, MAX_VALUE));
        }

        distances.put(source, new Pair<>(source, 0));
        
        //boucle principale
        for (int i = 1; i < graph.getNbNodes() - 1; i++) {
            for (DirectedNode node : graph.getNodes()) {
                for (Map.Entry<DirectedNode,Integer> edge : node.getPreds().entrySet()) {
                    DirectedNode u = node;
                    DirectedNode v = edge.getKey();
                    int w = edge.getValue();
                    if (distances.get(u).getRight() != MAX_VALUE && distances.get(u).getRight() < w + distances.get(v).getRight()) {
                        distances.put(v, new Pair<>(node, distances.get(u).getRight()+w));
                    }
                }
            }
        }

        System.out.println("chek negative circle");
        //vérification absence de cycle négatif
        for (DirectedNode node : graph.getNodes()) {
            for (Entry<DirectedNode,Integer> edge : node.getPreds().entrySet()) {
                DirectedNode u = node;
                DirectedNode v = edge.getKey();
                int w = edge.getValue();
                if (distances.get(u).getRight() != 99999999 && distances.get(u).getRight() < w + distances.get(v).getRight()) {
                    throw new BellmanException(BellmanException.NEGATIVE_CYCLE_MSG);
                }
            }
        }

        return distances;
    }

    /**
     * Bellman Algorithm for UndirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the UndirectedGraph to explore
     * @param source the UndirectedNode to go from
     * @throws Exception if negative cycles
     * @return all nearest nodes and their cost
     */
    public static HashMap<UndirectedNode, Pair<UndirectedNode, Integer>> Bellman(UndirectedGraph graph, UndirectedNode source) throws BellmanException {

        // Distance depuis source à node, avec prédécesseur et value
        HashMap<UndirectedNode, Pair<UndirectedNode, Integer>> distances = new HashMap<>();
        for (UndirectedNode node : graph.getNodes()){
            distances.put(node, new Pair<>(null, 999999999));
        }

        distances.put(source, new Pair<>(source, 0));

        //boucle principale
        for (int i = 1; i < graph.getNbNodes() - 1; i++) {
            for(UndirectedNode node : graph.getNodes()) {
                for (Map.Entry<UndirectedNode,Integer> edge: node.getNeighbours().entrySet()) {
                    if (distances.get(node).getRight() + edge.getValue() < distances.get(edge.getKey()).getRight()) {
                        distances.put(edge.getKey(), new Pair<>(node, distances.get(node).getRight() + edge.getValue()));
                    }
                }
            }
        }

        // vérification absence de cycle négatif
        for (UndirectedNode node : graph.getNodes()) {
            for (Entry<UndirectedNode,Integer> edge : node.getNeighbours().entrySet()) {
                if (distances.get(node).getRight() + edge.getValue() < distances.get(edge.getKey()).getRight()) {
                    throw new BellmanException(BellmanException.NEGATIVE_CYCLE_MSG);
                }
            }
        }

        return distances;
    }

    /**
     * Get the Shortest Path from source to destination with the Bellman algorithm for DirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @param destination the DirectedNode to go to
     * @throws Exception if negative cycles
     * @return shortest path from source to destination as a List of DirectedNode
     */
    public static List<DirectedNode> ShortestPath(DirectedGraph graph, DirectedNode source, DirectedNode destination) throws BellmanException {

        HashMap<DirectedNode, Pair<DirectedNode, Integer>> shortestPaths = Bellman(graph, destination);
        List<DirectedNode> shortestPath = new LinkedList<DirectedNode>();

        shortestPath.add(source);
        DirectedNode temp = source;

        int cpt = graph.getNodes().size();
        while (temp != destination && cpt >= 0) {
            if(shortestPaths.get(temp).getLeft() != null){
                temp = shortestPaths.get(temp).getLeft();
            }else{
                throw new BellmanException(BellmanException.NO_PATH_MSG);
            }
            shortestPath.add(temp);
            cpt--;
        }

        return shortestPath;
    }

    /**
     * Get the Shortest Path from source to destination with the Bellman algorithm for UndirectedGraph
     * <b>complexity: O(E log V)</b>
     * @param graph the DirectedGraph to explore
     * @param source the DirectedNode to go from
     * @param destination the DirectedNode to go to
     * @throws Exception if negative cycles
     * @return shortest path from source to destination as a List of DirectedNode
     */
    public static List<UndirectedNode> ShortestPath(UndirectedGraph graph, UndirectedNode source, UndirectedNode destination) throws Exception {

        HashMap<UndirectedNode, Pair<UndirectedNode, Integer>> shortestPaths = Bellman(graph, destination);
        List<UndirectedNode> shortestPath = new LinkedList<UndirectedNode>();

        shortestPath.add(source);
        UndirectedNode temp = source;

        int cpt = graph.getNodes().size();
        while (temp != destination && cpt >= 0) {
            temp = shortestPaths.get(temp).getLeft();
            shortestPath.add(temp);
            cpt--;
        }

        return shortestPath;
    }

    public static void main(String[] args) {
        int[][] mat = GraphTools.generateValuedGraphData(8, false, false, true, false, 100001);
        //UndirectedValuedGraph al = new UndirectedValuedGraph(mat);
        DirectedValuedGraph al = new DirectedValuedGraph(mat);
        System.out.println(al);

        try {
            System.out.println(Bellman(al, al.getNodes().get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int From = 7;
        int To = 0;

        try {
            // List<UndirectedNode> path = ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));
            List<DirectedNode> path = ShortestPath(al, al.getNodes().get(From), al.getNodes().get(To));

            System.out.println(path);
            BinaryHeapEdge binh = new BinaryHeapEdge();

            for (int i = 1; i < path.size(); i++) {
                binh.insert(path.get(i - 1), path.get(i),0);
            }

            // DrawUndirectedCoveringTree.Display(al, binh);
            DrawDirectedCoveringTree.Display(al, binh);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
