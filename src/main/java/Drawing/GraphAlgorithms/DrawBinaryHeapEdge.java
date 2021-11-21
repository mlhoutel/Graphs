package Drawing.GraphAlgorithms;

import Drawing.Canvas;
import Graphs.GraphAlgorithms.BinaryHeap;
import Graphs.GraphAlgorithms.BinaryHeapEdge;
import Graphs.Nodes.UndirectedNode;

public class DrawBinaryHeapEdge {
    
    public static void VisitDisplay(BinaryHeapEdge bin, Canvas canvas, Object root, Object parent, int index) {

        Object current = canvas.graph.insertVertex(root, null, bin.get(index).toString(), 0, 0, 60, 30);
        canvas.graph.insertEdge(root, null, "", parent, current);

        if (bin.hasFirstChild(index)) {
            int childIndex = bin.getFirstChildIndex(index);
            VisitDisplay(bin, canvas, root, current, childIndex);
        }
        
        if (bin.hasSecondChild(index)) {
            int childIndex = bin.getSecondChildIndex(index);
            VisitDisplay(bin, canvas, root, current, childIndex);
        }
    }

    public static void Display(BinaryHeapEdge bin) {
        Canvas canvas = new Canvas("Binary Heap Edge", true, Canvas.Layout.HIERARCHICAL);
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object parent = canvas.graph.getDefaultParent();
            VisitDisplay(bin, canvas, parent, parent, 0);
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
