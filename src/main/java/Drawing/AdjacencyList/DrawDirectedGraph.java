package Drawing.AdjacencyList;

import java.util.HashMap;
import java.util.Map;

import Drawing.Canvas;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;

public class DrawDirectedGraph {
       public static void Display(DirectedGraph ug) {
        Canvas canvas = new Canvas("Directed Graph", true, Canvas.Layout.CIRCLE);
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();
            for (DirectedNode n : ug.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, 60, 30));
            }
            
            for (DirectedNode n : ug.getNodes()) {                
                for (Map.Entry<DirectedNode, Integer> sn : n.getSuccs().entrySet()) {
                    canvas.graph.insertEdge(root, null, sn.getValue(), nodes.get(n.getLabel()), nodes.get(sn.getKey().getLabel()));
                }
            }
        }
        finally {
            canvas.graph.getModel().endUpdate();
            canvas.graph.setCellsEditable(false);
            canvas.Draw();
        }
    }

    public static void main(String[] args) {
        
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        GraphTools.afficherMatrix(Matrix);
        DirectedGraph al = new DirectedGraph(Matrix);

        Display(al);
    }
}
