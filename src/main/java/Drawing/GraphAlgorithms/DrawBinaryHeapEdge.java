package Drawing.GraphAlgorithms;

import java.util.HashMap;

import Drawing.Canvas;
import Graphs.Collection.Triple;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.Nodes.AbstractNode;
import Graphs.Nodes.UndirectedNode;

public class DrawBinaryHeapEdge {
    
    public static void VisitDisplay(BinaryHeapEdge bin, Canvas canvas, Object root) {
        HashMap<AbstractNode, Object> nodes = new HashMap<>();

        for (int i = 0; i < bin.size(); i++) {
            Triple n = bin.get(i);

            if (!nodes.containsKey(n.getFirst())) {
                Object first = canvas.graph.insertVertex(root, null, n.getFirst().toString(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT);
                nodes.put((AbstractNode)n.getFirst(), first);
            } 

            if (!nodes.containsKey(n.getSecond())) {
                Object second = canvas.graph.insertVertex(root, null, n.getSecond().toString(), 0, 0, Canvas.WIDTH, Canvas.HEIGHT);
                nodes.put((AbstractNode)n.getSecond(), second);
            }

            canvas.graph.insertEdge(root, null, n.getThird(), nodes.get(n.getFirst()), nodes.get(n.getSecond()));
        }
    }

    public static void Display(BinaryHeapEdge bin) {
        Canvas canvas = new Canvas("Binary Heap Edge", true, Canvas.Layout.HIERARCHICAL);
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object parent = canvas.graph.getDefaultParent();
            VisitDisplay(bin, canvas, parent);
        }
        finally {
            canvas.graph.getModel().endUpdate();
            canvas.graph.setCellsEditable(false);
            canvas.graph.setCellsMovable(false);
            canvas.Draw();
        }
    }
    public static void main(String[] args) {
        BinaryHeapEdge jarjarBin = new BinaryHeapEdge();
        System.out.println(jarjarBin.isEmpty() + "\n");
        int k = 10;
        int min = 2;
        int max = 20;
        while (k > 0) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));
            jarjarBin.insert(new UndirectedNode(k), new UndirectedNode(k + 30), rand);
            k--;
        }
        Display(jarjarBin);
    }
}
