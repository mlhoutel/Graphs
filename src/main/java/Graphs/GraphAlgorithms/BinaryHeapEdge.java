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
        this.binh = new ArrayList<Triple<UndirectedNode,UndirectedNode,Integer>>();
    }

    public boolean isEmpty() {
        return binh.isEmpty();
    }

    public Triple<UndirectedNode,UndirectedNode,Integer> getFatherTriple(UndirectedNode n){
        for (Triple<UndirectedNode,UndirectedNode,Integer> temp : binh){
            if(temp.getSecond().equals(n)){
                return temp;
            }
        }
        return null;
    }


   
    public Triple<UndirectedNode,UndirectedNode,Integer>  getFirstChildTriple(UndirectedNode n) {
        for (Triple<UndirectedNode,UndirectedNode,Integer> temp : binh){
            if(temp.getFirst().equals(n)){
                return temp;
            }
        }
        return null;    
    }

    public boolean hasFirstChild(UndirectedNode n){
        return !getFirstChildTriple(n).equals(null);
    }

    public Triple<UndirectedNode,UndirectedNode,Integer> getSecondChildTriple(UndirectedNode n) {
        int cpt = 0;
        for (Triple<UndirectedNode,UndirectedNode,Integer> temp : binh){
            if(temp.getFirst().equals(n)){
                if(cpt == 1){
                    return temp;
                }else{
                    cpt++;
                }
            }
        }
        return null;    
    }
    
    public boolean hasSecondChild(UndirectedNode n){
        return !getSecondChildTriple(n).equals(null);
    }

   

    /**
	 * Insert a new edge in the binary heap
	 * 
	 * @param from one node of the edge
	 * @param to one node of the edge
	 * @param val the edge weight
	 */
    public void insert(UndirectedNode from, UndirectedNode to, int val) {
		//TODO
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
    private void percolateUp(UndirectedNode n) {
        Triple<UndirectedNode, UndirectedNode, Integer> fatherTriple = getFatherTriple(n);
        if (fatherTriple != null) {
            UndirectedNode father = fatherTriple.getFirst();
            int fatherEdgeWeight = fatherTriple.getThird();

            Triple<UndirectedNode, UndirectedNode, Integer> grandFatherTriple = getFatherTriple(n);
            if (grandFatherTriple != null) {
                int grandFatherEdgeWeight = grandFatherTriple.getThird();
                if(fatherEdgeWeight < grandFatherEdgeWeight){
                    swap(grandFatherTriple, fatherTriple);
                }
            }
        }
    }

    /*
        While the node has at least one child, we take
        the child with the min value and swap it with the
        current node to bring lower the higher value.
    */
    private void percolateDown(UndirectedNode n) {
        if (!isLeaf(n)) {
            Triple<UndirectedNode, UndirectedNode, Integer> bestChild = getBestChildTriple(n);
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
    private Triple<UndirectedNode, UndirectedNode, Integer> getBestChildTriple(UndirectedNode n) {
        Triple<UndirectedNode, UndirectedNode, Integer> firstChildTriple = getFirstChildTriple(n);
        Triple<UndirectedNode, UndirectedNode, Integer> secondChildTriple = getSecondChildTriple(n);
        if (secondChildTriple != null) {
            return (firstChildTriple.getThird() < secondChildTriple.getThird()) ? firstChildTriple : secondChildTriple;
        }else if (firstChildTriple != null){
            return firstChildTriple ;
        }else{
            return null;
        }
    }

    private boolean isLeaf(UndirectedNode n) {
    	return !hasFirstChild(n);
    }

    
    private void swap(UndirectedNode n1, UndirectedNode n2) {         
    	UndirectedNode temp;
        temp = n1;
        n1 = n2;
        n2 = temp;
    }


    private void swap(Triple<UndirectedNode, UndirectedNode, Integer> t1, 
    Triple<UndirectedNode, UndirectedNode, Integer> t2) {         
    	swap(t1.getFirst(), t2.getSecond());
        int temp;
        temp = t1.getThird();
        t1.setThird(t2.getThird());
        t2.setThird(temp);
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
        System.out.println("removing in : " +jarjarBin);

        jarjarBin.remove();
        System.out.println("graph: " +jarjarBin);
        System.out.println("test: " + jarjarBin.test());
        
        //jarjarBin.lovelyPrinting();
    }
}

