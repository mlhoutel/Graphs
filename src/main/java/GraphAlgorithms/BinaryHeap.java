package GraphAlgorithms;

import java.util.ArrayList;

public class BinaryHeap {

    private ArrayList<Integer> nodes;
    private int pos;

    public BinaryHeap() {
        this.nodes = new ArrayList<Integer>();
        for (int i = 0; i < nodes.size(); i++) {
            this.nodes.add(Integer.MAX_VALUE);
        }
        this.pos = 0;
    }

    public class BinaryHeapException extends Exception {
        public BinaryHeapException(String errorMessage) {
            super(errorMessage);
        }
    }
    
    /*public void resize() {
        ArrayList<Integer> tab = new int[this.nodes.size() + 32];
        for (int i = 0; i < nodes.size(); i++) {
            tab[i] = Integer.MAX_VALUE;
        }
        System.arraycopy(this.nodes, 0, tab, 0, this.nodes.size());
        this.nodes = tab;
    }*/

    public boolean isEmpty() {
        return pos == 0;
    }
    
    private int getFatherIndex(int i){
        return (i-1) / 2;
    }
    
    public int getFirstChildIndex(int i) {
        return 2 * i + 1;
    }

    public int getSecondChildIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasFather(int i){
        return i != 0;
    }

    private boolean hasFirstChild(int i) {
        return getFirstChildIndex(i) <= this.nodes.size();
    }
    
    private boolean hasSecondChild(int i) {
        return getSecondChildIndex(i) <= this.nodes.size();
    }
    

    
    public void insert(int element) {
            // naive add
            this.nodes.add(element);

            // Percolate
            int index = this.nodes.size() - 1;
            this.percolateUp(index);
            this.percolateDown(index);

    }


    /*
        While the node has a father and its father value
        is lower that its value, we swap the nodes to 
        bring higher the lower value.
    */
    public void percolateUp(int index) {
        if (hasFather(index)) {
            int value = this.nodes.get(index);
            int fatherIndex = getFatherIndex(index);
            int fatherValue = this.nodes.get(fatherIndex);

             if(fatherValue > value){
                 swap(index, fatherIndex);
                 percolateUp(fatherIndex);
            }
        }
    }

    public void percolateDown(int index) {
        if (hasFirstChild(index)) {
            int value = this.nodes.get(index);

             if(getFirstChildIndex(index) != Integer.MAX_VALUE){
                 swap(index, minimumChildIndex);
                 percolateDown(fatherIndex);
            }
        }
    }
    
    
    
    public int remove() {
    	// A completer
    	return 0;
    }

    private int getBestChildPos(int index) {
        if (isLeaf(index)) {
            return Integer.MAX_VALUE;
        } else {
            if (hasSecondChild(index)) {

            } else {
                return 
            }
        	// A completer
            //pour un noeud donné, on prend le plus petit fils et on swap (percolate down)
        	return Integer.MAX_VALUE;
        }
    }

    
    /**
	 * Test if the node is a leaf in the binary heap
	 * 
	 * @returns true if it's a leaf or false else
	 * 
	 */	
    private boolean isLeaf(int index) {
    	return !hasFirstChild(index);
    }

    private void swap(int father, int child) {
        int temp = nodes.get(father);
        this.nodes.set(father, nodes.get(child)); 
        this.nodes.set(child,temp); 
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            s.append(nodes.get(i)).append(", ");
        }
        return s.toString();
    }

    /**
	 * Recursive test to check the validity of the binary heap
	 * 
	 * @returns a boolean equal to True if the binary tree is compact from left to right
	 * 
	 */
    public boolean test() {
        return this.isEmpty() || testRec(0);
    }

    private boolean testRec(int root) {
        if (isLeaf(root)) {
            return true;
        } else {
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            if (right >= pos) {
                return nodes.get(left) >= nodes.get(root) && testRec(left);
            } else {
                return nodes.get(left) >= nodes.get(root) && testRec(left) && nodes.get(right) >= nodes.get(root) && testRec(right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryHeap jarjarBin = new BinaryHeap();
        System.out.println(jarjarBin.isEmpty()+"\n");
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
        System.out.println(jarjarBin.test());
    }

}
