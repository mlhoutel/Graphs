package Graphs.GraphAlgorithms;

import Graphs.Collection.Triple;
import Graphs.Nodes.UndirectedNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeapEdge {

	/**
	 * A list structure for a faster management of the heap by indexing
	 */
	private List<Triple<UndirectedNode,UndirectedNode,Integer>> binh;

    public BinaryHeapEdge() {
        this.binh = new ArrayList<>();
    }

    public boolean isEmpty() {
        return binh.isEmpty();
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
        return getFirstChildIndex(i) < this.binh.size();
    }

    public boolean hasSecondChild(int i) {
        return getSecondChildIndex(i) < this.binh.size();
    }

    /**
	 * Insert a new edge in the binary heap
	 * 
	 * @param from one node of the edge
	 * @param to one node of the edge
	 * @param val the edge weight
	 */
    public void insert(UndirectedNode from, UndirectedNode to, int val) {
		if(isEmpty()){
			binh.add(new Triple<UndirectedNode,UndirectedNode,Integer>(from, to, val));
		}else{
			binh.add(new Triple<UndirectedNode,UndirectedNode,Integer>(from, to, val));
			int dest = binh.size()-1;
			int src = (dest-1)/2;
			while(dest > 0 && binh.get(src).getThird() > binh.get(dest).getThird()){
				swap(src, dest);
				dest = src;
                src = (src-1)/2;
			}
		}
    }

    
    /**
	 * Removes the root edge in the binary heap, and swap the edges to keep a valid binary heap
	 * 
	 * @return the edge with the minimal value (root of the binary heap)
	 * 
	 */
    public Triple<UndirectedNode,UndirectedNode,Integer> remove() {
        Triple<UndirectedNode, UndirectedNode, Integer> value = this.binh.get(0);
        if (!isLeaf(0)) {
            this.binh.set(0, this.binh.get(this.binh.size() - 1));
            this.binh.remove(binh.size() - 1);
            this.percolateDown(0);
        }
        return value;
    }

    /*
        While the node has a father and its father value
        is lower that its value, we swap the nodes to 
        bring higher the lower value.
    */
    private void percolateUp(int index) {
        if (hasFather(index)) {
            int value = this.binh.get(index).getThird();
            int fatherIndex = getFatherIndex(index);
            int fatherValue = this.binh.get(fatherIndex).getThird();

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
            int value = this.binh.get(index).getThird();

            if (bestChildIndex < value) {
                swap(index, bestChildIndex);
                percolateDown(bestChildIndex);
            }
        }
    }
    
    
    /**
    * From an edge indexed by src, find the child having the least weight and return it
    * 
    * @param src an index of the list edges
    * @return the index of the child edge with the least weight
    */
    private int getBestChildPos(int index) {
        /*
        if (hasSecondChild(index)) {
            int firstChildIndex = getFirstChildIndex(index);
            int secondChildIndex = getSecondChildIndex(index);
            int firstChildValue = this.binh.get(firstChildIndex).getThird();
            int secondChildValue =  this.binh.get(secondChildIndex).getThird();
            return (firstChildValue < secondChildValue) ? firstChildIndex : secondChildIndex;
        } else {
            return getFirstChildIndex(index);
        }*/
        return 0;
    }

    private boolean isLeaf(int src) {
    	int left = 2 * src + 1;
    	return left > binh.size()-1;
    }

    
    /**
	 * Swap two edges in the binary heap
	 * 
	 * @param father an index of the list edges
	 * @param child an index of the list edges
	 */
    private void swap(int father, int child) {         
    	Triple<UndirectedNode,UndirectedNode,Integer> temp = new Triple<>(binh.get(father).getFirst(), binh.get(father).getSecond(), binh.get(father).getThird());
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
        for (Triple<UndirectedNode,UndirectedNode,Integer> no: binh) {
            s.append(no).append(", ");
        }
        return s.toString();
    }
    
    
    private String space(int x) {
		StringBuilder res = new StringBuilder();
		for (int i=0; i<x; i++) {
			res.append(" ");
		}
		return res.toString();
	}
	
	/**
	 * Print a nice visualisation of the binary heap as a hierarchy tree
	 * 
	 */	
	public void lovelyPrinting(){
		int nodeWidth = this.binh.get(0).toString().length();
		int depth = 1+(int)(Math.log(this.binh.size())/Math.log(2));
		int index=0;
		
		for(int h = 1; h<=depth; h++){
			int left = ((int) (Math.pow(2, depth-h-1)))*nodeWidth - nodeWidth/2;
			int between = ((int) (Math.pow(2, depth-h))-1)*nodeWidth;
			int i =0;
			System.out.print(space(left));
			while(i<Math.pow(2, h-1) && index<binh.size()){
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
	 * 
	 */
    private boolean test() {
        return this.isEmpty() || testRec(0);
    }

    private boolean testRec(int root) {
    	int lastIndex = binh.size()-1; 
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
        System.out.print("\033[H\033[2J");
        System.out.flush();
        BinaryHeapEdge jarjarBin = new BinaryHeapEdge();
        System.out.println(jarjarBin.isEmpty()+"\n");
        int k = 3;
        int m = k;
        int min = 2;
        int max = 20;
        while (k > 0) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));  
            System.out.println("starting insert of: "+ rand);          
            jarjarBin.insert(new UndirectedNode(k), new UndirectedNode(k+30), rand);      
            System.out.println("insert done");    
            k--;
        }
        // A completer
        jarjarBin.remove();
        System.out.println("graph: " +jarjarBin);
        System.out.println("test: " + jarjarBin.test());
        
        jarjarBin.lovelyPrinting();
    }

}

