package Graphs.GraphAlgorithms;

import static Drawing.GraphAlgorithms.DrawBinaryHeap.Display;

import java.util.ArrayList;

public class BinaryHeap {

    public ArrayList<Integer> nodes;

    public BinaryHeap() {
        this.nodes = new ArrayList<Integer>();
    }

    public class BinaryHeapException extends Exception {
        public BinaryHeapException(String errorMessage) {
            super(errorMessage);
        }
    }

    public boolean isEmpty() {
        return this.nodes.size() == 0;
    }

    public int getFatherIndex(int i) {
        return (i - 1) / 2;
    }

    public int getFirstChildIndex(int i) {
        return 2 * i + 1;
    }

    public int getSecondChildIndex(int i) {
        return 2 * i + 2;
    }

    public boolean hasFather(int i) {
        return i != 0;
    }

    public boolean hasFirstChild(int i) {
        return getFirstChildIndex(i) < this.nodes.size();
    }

    public boolean hasSecondChild(int i) {
        return getSecondChildIndex(i) < this.nodes.size();
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
    private void percolateUp(int index) {
        if (hasFather(index)) {
            int value = this.nodes.get(index);
            int fatherIndex = getFatherIndex(index);
            int fatherValue = this.nodes.get(fatherIndex);

            if (fatherValue > value) {
                swap(index, fatherIndex);
                percolateUp(fatherIndex);
            }
        }
    }

    /*
        While the node has at least one child, we take
        the child with the min value and swap it with the
        current node to bring lower the higher value.
    */
    private void percolateDown(int index) {
        if (!isLeaf(index)) {
            int bestChildIndex = getBestChildPos(index);
            int value = this.nodes.get(index);

            if (bestChildIndex < value) {
                swap(index, bestChildIndex);
                percolateDown(bestChildIndex);
            }
        }
    }

    public int remove() {
        int value = this.nodes.get(0);
        if (!isLeaf(0)) {
            this.nodes.set(0, this.nodes.get(this.nodes.size() - 1));
            this.nodes.remove(nodes.size() - 1);
            this.percolateDown(0);
        }
        return value;
    }

    public int getBestChildPos(int index) {
        if (hasSecondChild(index)) {
            int firstChildIndex = getFirstChildIndex(index);
            int secondChildIndex = getSecondChildIndex(index);
            int firstChildValue = this.nodes.get(firstChildIndex);
            int secondChildValue = this.nodes.get(secondChildIndex);
            return (firstChildValue < secondChildValue) ? firstChildIndex : secondChildIndex;
        } else {
            return getFirstChildIndex(index);
        }
    }

    /**
     * Test if the node is a leaf in the binary heap
     * 
     * @returns true if it's a leaf or false else
     * 
     */
    public boolean isLeaf(int index) {
        return !hasFirstChild(index);
    }

    public void swap(int father, int child) {
        int temp = nodes.get(father);
        this.nodes.set(father, nodes.get(child));
        this.nodes.set(child, temp);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
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
            if (right >= this.nodes.size()) {
                return nodes.get(left) >= nodes.get(root) && testRec(left);
            } else {
                return nodes.get(left) >= nodes.get(root) && testRec(left) && nodes.get(right) >= nodes.get(root)
                        && testRec(right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryHeap jarjarBin = new BinaryHeap();
        System.out.println(jarjarBin.isEmpty() + "\n");

        jarjarBin.insert(2);
        jarjarBin.insert(3);
        jarjarBin.insert(1);
        jarjarBin.insert(1);
        jarjarBin.insert(6);
        jarjarBin.insert(1);
        System.out.println(jarjarBin.testRec(0) + "\n");
        Display(jarjarBin);
    }

}
