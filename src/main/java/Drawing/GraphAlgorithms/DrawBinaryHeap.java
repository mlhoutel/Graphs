package Drawing.GraphAlgorithms;

import Drawing.Canvas;
import Graphs.GraphAlgorithms.BinaryHeap;

public class DrawBinaryHeap {
    
    public static void VisitDisplay(BinaryHeap bin, Canvas canvas, Object root, Object parent, int index, int depth, int width) {
        /*
        int height = 20;
        int width = 20;
        
        if (bin.hasFirstChild(index)) {
            int childIndex = bin.getFirstChildIndex(index);
        
            int baseX = depth * 100
            int baseY = width * 100
            
            Object child = canvas.graph.insertVertex(root, null, bin.nodes.get(childIndex).toString(), baseX, baseY, baseX + width, baseY + width);
            Object edge = canvas.graph.insertEdge(root, null, "", parent, child);
        
            VisitDisplay(bin, canvas, child, childIndex, depth+1, width);
        }
        
        if (bin.hasSecondChild(index)) {
            int childIndex = bin.getSecondChildIndex(index);
            
            int baseX = depth * 100
            int baseY = width * 100
            
            Object child = canvas.graph.insertVertex(root, null, bin.nodes.get(childIndex).toString(), baseX, baseY, baseX + width, baseY + width);
            Object edge = canvas.graph.insertEdge(root, null, "", parent, child);
        
            VisitDisplay(bin, canvas, child, childIndex, depth+1, width + 1);
        }*/
    }

    public static void Display(BinaryHeap bin) {
        /*
        Canvas canvas = new Canvas("Binary Heap");
        canvas.graph.getModel().beginUpdate();
        
        try {
            Object parent = graph.getDefaultParent();
            VisitDisplay(bin, canvas, parent, parent, 0, 0, 0);
        }
        finally {
            graph.getModel().endUpdate();
            graph.Draw();
        }
        */
    }
    public static void main(String[] args) {
        BinaryHeap jarjarBin = new BinaryHeap();
        System.out.println(jarjarBin.isEmpty() + "\n");
        int k = 20;
        int m = k;
        int min = 2;
        int max = 20;
        while (k > 0) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));
            System.out.print("insert " + rand);
            System.out.println("\n" + jarjarBin);

            jarjarBin.insert(rand);
            k--;
        }
        // A completer
        System.out.println("\n" + jarjarBin);
        System.out.println("removing...");
        System.out.println("removed value"+jarjarBin.remove());
        System.out.println("\n" + jarjarBin);
        System.out.println(jarjarBin.test());

        //Display(jarjarBin);
    }

}
