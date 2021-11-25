package Drawing.AdjacencyList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Drawing.Canvas;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.UndirectedNode;

public class DrawUndirectedGraph {

    public static void Display(UndirectedGraph ug) {
        Canvas canvas = new Canvas("Undirected Graph", false, Canvas.Layout.CIRCLE);
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();
            for (UndirectedNode n : ug.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT));
            }
            
            HashSet<Integer> visited = new HashSet<Integer>();
            for (UndirectedNode n : ug.getNodes()) {                
                for (Map.Entry<UndirectedNode, Integer> sn : n.getNeighbours().entrySet()) {
                    if (!visited.contains(sn.getKey().getLabel())) {
                        canvas.graph.insertEdge(root, null, sn.getValue(), nodes.get(n.getLabel()), nodes.get(sn.getKey().getLabel()));
                    }
                }
                visited.add(n.getLabel());
            }
        }
        finally {
            canvas.graph.getModel().endUpdate();
            canvas.graph.setCellsEditable(false);
            canvas.Draw();
        }
    }

    public static void main(String[] args) {
        
        int[][] mat = GraphTools.generateGraphData(10, 20, false, true, false, 100001);
        GraphTools.afficherMatrix(mat);
        UndirectedGraph al = new UndirectedGraph(mat);

        Display(al);
    }
}
