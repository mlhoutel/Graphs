package Drawing.GraphAlgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Drawing.Canvas;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.Algorithms.DFS;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeap;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.Nodes.AbstractNode;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class DrawDirectedCoveringTree {

    public static void Display(DirectedGraph graph, BinaryHeapEdge bin) {
        Canvas canvas = new Canvas("Directed Covering Tree", true, Canvas.Layout.HIERARCHICAL);
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();

            for (DirectedNode n : graph.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, 60, 30));
            }
            
            for (DirectedNode n : graph.getNodes()) {                
                for (Map.Entry<DirectedNode, Integer> sn : n.getSuccs().entrySet()) {
                    Boolean inTree = false;
                    for (int i = 0; i < bin.size(); i++) {
                        Triple t = bin.get(i);

                        Boolean a = ((DirectedNode)t.getFirst()).getLabel() == n.getLabel() && ((DirectedNode)t.getSecond()).getLabel() == sn.getKey().getLabel();

                        Boolean b = ((DirectedNode)t.getSecond()).getLabel() == n.getLabel() && ((DirectedNode)t.getFirst()).getLabel() == sn.getKey().getLabel();

                        inTree = inTree || a || b;
                    }

                    canvas.graph.insertEdge(root, null, sn.getValue() + " <in tree>", nodes.get(n.getLabel()), nodes.get(sn.getKey().getLabel()));
                }
            }
        }
        finally {
            canvas.graph.getModel().endUpdate();
            canvas.graph.setCellsEditable(false);
            canvas.graph.setCellsMovable(false);
            canvas.Draw();
        }
    }
    public static void main(String[] args) {
    }
}
