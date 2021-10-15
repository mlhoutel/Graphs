package Drawing.GraphAlgorithms;

import Drawing.Canvas;
import Graphs.GraphAlgorithms.BinaryHeap;

public class DrawBinaryHeap {
    
    public static void VisitDisplay(BinaryHeap bin, Canvas canvas, Object root, Object parent, int index) {

        Object current = canvas.graph.insertVertex(root, null, bin.nodes.get(index).toString(), 0, 0, 60, 30);
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

    public static void Display(BinaryHeap bin) {
        Canvas canvas = new Canvas("Binary Heap");
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
        BinaryHeap jarjarBin = new BinaryHeap();
        int k = 127;
        int min = 1;
        int max = 2000;
        for (int i = 0; i < k; i++) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));
            jarjarBin.insert(rand);
        }

        Display(jarjarBin);
    }

}
