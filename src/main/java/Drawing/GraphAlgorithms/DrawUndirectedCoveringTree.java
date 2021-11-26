package Drawing.GraphAlgorithms;

import java.util.HashMap;
import java.util.Map;

import Drawing.DrawCanvas.Canvas;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.Nodes.UndirectedNode;

public class DrawUndirectedCoveringTree {

    public static void Display(UndirectedGraph graph, BinaryHeapEdge bin) {
        Canvas canvas = new Canvas("Directed Covering Tree", true, Canvas.Layout.CIRCLE);
        canvas.graph.getModel().beginUpdate();

        try {
            Object root = canvas.graph.getDefaultParent();

            HashMap<Integer, Object> nodes = new HashMap<Integer, Object>();

            for (UndirectedNode n : graph.getNodes()) {
                nodes.put(n.getLabel(), canvas.graph.insertVertex(root, null, n.getLabel(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT));
            }

            for (UndirectedNode n : graph.getNodes()) {
                for (Map.Entry<UndirectedNode, Integer> sn : n.getNeighbours().entrySet()) {
                    Boolean inTree = false;
                    for (int i = 0; i < bin.size(); i++) {
                        Triple t = bin.get(i);

                        Boolean a = ((UndirectedNode)t.getFirst()).getLabel() == n.getLabel() && ((UndirectedNode)t.getSecond()).getLabel() == sn.getKey().getLabel();

                        Boolean b = ((UndirectedNode)t.getSecond()).getLabel() == n.getLabel() && ((UndirectedNode)t.getFirst()).getLabel() == sn.getKey().getLabel();

                        inTree = inTree || a || b;
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
