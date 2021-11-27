package Drawing.AdjacencyList;

import Drawing.DrawCanvas.Canvas;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.DirectedValuedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DrawGraph {
        public static void Display(DirectedGraph ug) {
        Canvas canvas = new Canvas("Directed Graph", true, Canvas.Layout.CIRCLE);
        canvas.graph.getModel().beginUpdate();

        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();
            for (DirectedNode n : ug.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT));
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
        //int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        //GraphTools.afficherMatrix(Matrix);
        //DirectedGraph al = new DirectedGraph(Matrix);

        int[][] mat = GraphTools.generateValuedGraphData(10, false, false, true, true, 855);
        DirectedGraph al = new DirectedValuedGraph(mat);

        Display(al);
    }
}
