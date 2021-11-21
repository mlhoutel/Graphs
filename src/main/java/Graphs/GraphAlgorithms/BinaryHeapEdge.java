package Graphs.GraphAlgorithms;
import java.util.ArrayList;
import java.util.List;

import Graphs.Collection.Triple;
import Graphs.Nodes.UndirectedNode;

public class BinaryHeapEdge<T extends AbstractNode> {

    /**
     * A list structure for a faster management of the heap by indexing
     * Complexité inchangée avec BinaryHeap.java
     */
    private List<Triple<T, T, Integer>> binh;

    public BinaryHeapEdge() {
        this.binh = new ArrayList<>();
    }

    public boolean isEmpty() {
        return binh.isEmpty();
    }

    public Triple<T, T, Integer> get(int index) {
        return binh.get(index);
    }

    public Integer size() {
        return binh.size();
    }

    public List<Triple<T, T, Integer>> getList() {
        return binh;
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

    public boolean hasFirstChild(int i) {
        return getFirstChildIndex(i) < this.binh.size();
    }

    public boolean hasSecondChild(int i) {
        return getSecondChildIndex(i) < this.binh.size();
    }

    public boolean hasFather(int i) {
        return i != 0;
    }

    /**
     * Insert a new edge in the binary heap
     *
     * @param from one node of the edge
     * @param to   one node of the edge
     * @param val  the edge weight
     */
    public void insert(T from, T to, int val) {
        if (isEmpty()) {
            binh.add(new Triple<>(from, to, val));
        } else {
            binh.add(new Triple<>(from, to, val));

            int dest = binh.size() - 1;
            int src = (dest - 1) / 2;

            while (dest > 0 && binh.get(src).getThird() > binh.get(dest).getThird()) {
                swap(src, dest);
                dest = src;
                src = (src - 1) / 2;
            }
        }
    }


    /**
     * Removes the root edge in the binary heap, and swap the edges to keep a valid binary heap
     *
     * @return the edge with the minimal value (root of the binary heap)
     */
    public Triple<T, T, Integer> remove() {
        if (binh.size() <= 0) {
            return null;
        } else if (binh.size() == 1) {
            return binh.remove(0);
        } else {
            //Echanger la racine avec la dernière feuille
            swap(0, binh.size() - 1);

            //replacer la nouvelle racine
            Triple<T, T, Integer> valRoot = binh.remove(binh.size() - 1);
            int posElement = 0; //position de l'élément à replacer
            int dest = getBestChildPos(posElement);

            while (dest != -1 && dest < binh.size() && binh.get(posElement).getThird() >= binh.get(dest).getThird()) {
                swap(posElement, dest);
                posElement = dest;
                dest = getBestChildPos(posElement);
            }
            return valRoot;
        }
    }


    /**
     * From an edge indexed by src, find the child having the least weight and return it
     *
     * @param src an index of the list edges
     * @return the index of the child edge with the least weight
     */
    private int getBestChildPos(int src) {
        int firstChildIndex = getFirstChildIndex(src);
        int secondChildIndex = getSecondChildIndex(src);

        if (isLeaf(src)) { // the leaf is a stopping case, then we return a default value
            return -1;
        } else {
            if (secondChildIndex>= binh.size()) {
                return firstChildIndex;
            } else {
                if (binh.get(firstChildIndex).getThird() <= binh.get(secondChildIndex).getThird()) {
                    return firstChildIndex;
                } else {
                    return secondChildIndex;
                }

            }
        }
    }

    private boolean isLeaf(int src) {
        return 2 * src + 1 >= binh.size() - 1;
    }


    /**
     * Swap two edges in the binary heap
     *
     * @param father an index of the list edges
     * @param child  an index of the list edges
     */
    private void swap(int father, int child) {
        Triple<T, T, Integer> temp = new Triple<>(binh.get(father).getFirst(), binh.get(father).getSecond(), binh.get(father).getThird());
        binh.get(father).setTriple(binh.get(child));
        binh.get(child).setTriple(temp);
    }


    /**
     * Create the string of the visualisation of a binary heap
     *
     * @return the string of the binary heap
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Triple<T, T, Integer> no : binh) {
            s.append(no).append(", ");
        }
        return s.toString();
    }


    private String space(int x) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < x; i++) {
            res.append(" ");
        }
        return res.toString();
    }

    /**
     * Print a nice visualisation of the binary heap as a hierarchy tree
     */
    public void lovelyPrinting() {
        int nodeWidth = this.binh.get(0).toString().length();
        int depth = 1 + (int) (Math.log(this.binh.size()) / Math.log(2));
        int index = 0;

        for (int h = 1; h <= depth; h++) {
            int left = ((int) (Math.pow(2, depth - h - 1))) * nodeWidth - nodeWidth / 2;
            int between = ((int) (Math.pow(2, depth - h)) - 1) * nodeWidth;
            int i = 0;
            System.out.print(space(left));
            while (i < Math.pow(2, h - 1) && index < binh.size()) {
                System.out.print(binh.get(index) + space(between));
                index++;
                i++;
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // ------------------------------------
    // 					TEST
    // ------------------------------------

    /**
     * Recursive test to check the validity of the binary heap
     *
     * @return a boolean equal to True if the binary tree is compact from left to right
     */
    private boolean test() {
        return this.isEmpty() || testRec(0);
    }

    private boolean testRec(int root) {
        int lastIndex = binh.size() - 1;
        if (isLeaf(root)) {
            return true;
        } else {
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            if (right >= lastIndex) {
                return binh.get(left).getThird() >= binh.get(root).getThird() && testRec(left);
            } else {
                return binh.get(left).getThird() >= binh.get(root).getThird() && testRec(left)
                        && binh.get(right).getThird() >= binh.get(root).getThird() && testRec(right);
            }
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

        jarjarBin.remove();
        jarjarBin.remove();
        System.out.print(jarjarBin.testRec(0));
        System.out.print(jarjarBin);
        
    }

}
