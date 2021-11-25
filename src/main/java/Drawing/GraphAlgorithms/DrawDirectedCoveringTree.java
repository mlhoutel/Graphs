package Drawing.GraphAlgorithms;

import java.util.HashMap;
import java.util.Map;

import Drawing.Canvas;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.Nodes.DirectedNode;

public class DrawDirectedCoveringTree {

    public static void Display(DirectedGraph graph, BinaryHeapEdge bin) {
        Canvas canvas = new Canvas("Directed Covering Tree", true, Canvas.Layout.CIRCLE);
        canvas.graph.getModel().beginUpdate();

        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();

            for (DirectedNode n : graph.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT));
            }

            for (DirectedNode n : graph.getNodes()) {
                for (Map.Entry<DirectedNode, Integer> sn : n.getSuccs().entrySet()) {
                    Boolean inTree = false;
                    for (int i = 0; i < bin.size(); i++) {
                        Triple t = bin.get(i);

                        Boolean a = ((DirectedNode)t.getFirst()).getLabel() == n.getLabel() && ((DirectedNode)t.getSecond()).getLabel() == sn.getKey().getLabel();

                        Boolean b = ((DirectedNode)t.getSecond()).getLabel() == n.getLabel() && ((DirectedNode)t.getFirst()).getLabel() == sn.getKey().getLabel();

                        inTree = inTree || a ;
                    }
                    if(inTree){
                        canvas.graph.insertEdge(root, null, sn.getValue(), nodes.get(n.getLabel()), nodes.get(sn.getKey().getLabel()), "strokeColor=#FD7272;");
                    }else{
                        canvas.graph.insertEdge(root, null, sn.getValue() , nodes.get(n.getLabel()), nodes.get(sn.getKey().getLabel()));
                    }
                }
            }
        }
        finally {
            canvas.graph.getModel().endUpdate();
            //canvas.graph.setCellsEditable(false);
            //canvas.graph.setCellsMovable(false);
            canvas.Draw();
        }
    }
    public static void main(String[] args) {
    }
}
