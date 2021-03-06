package Graphs.AdjacencyMatrix;

import Graphs.Abstraction.AbstractMatrixGraph;
import Graphs.Abstraction.IUndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.AbstractNode;
import Graphs.Nodes.UndirectedNode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the undirected graphs structured by an adjacency matrix.
 * It is possible to have simple and multiple graph
 */
public class AdjacencyMatrixUndirectedGraph extends AbstractMatrixGraph<UndirectedNode> implements IUndirectedGraph {
	
	//--------------------------------------------------
	// 				Constructors
	//-------------------------------------------------- 
	
	public AdjacencyMatrixUndirectedGraph() {
		super();
	}

	public AdjacencyMatrixUndirectedGraph(int[][] mat) {
		this.order=mat.length;
		this.matrix = new int[this.order][this.order];
		for(int i = 0; i<this.order; i++){
			for(int j = i; j<this.order; j++){
				this.matrix[i][j] = mat[i][j];
				this.matrix[j][i] = mat[i][j];
				this.m += mat[i][j];
			}
		}	
	}
	
	public AdjacencyMatrixUndirectedGraph(IUndirectedGraph g) {
		this.order = g.getNbNodes(); 				
		this.m = g.getNbEdges(); 				
		this.matrix = g.toAdjacencyMatrix(); 
	}

	//--------------------------------------------------
	// 					Accessors
	//--------------------------------------------------

	@Override
	public int getNbEdges() {
		return this.m;
	}

	public List<Integer> getNeighbours(AbstractNode x) {
		List<Integer> l = new ArrayList<>();
		for(int i = 0; i<matrix[x.getLabel()].length; i++){
			if(matrix[x.getLabel()][i]>0){
				l.add(i);
			}
		}
		return l;
	}
	
	// ------------------------------------------------
	// 					Methods 
	// ------------------------------------------------		
	
	@Override
	public boolean isEdge(UndirectedNode x, UndirectedNode y) {
		if (matrix[x.getLabel()][y.getLabel()] == 0) { return false; }
		if (matrix[y.getLabel()][x.getLabel()] == 0) { return false; }

		return true;
	}
	
	/**
	 * @param x arc from
	 * @param y arc to
     * removes the edge (x,y) if there exists at least one between these nodes in the graph.
     */
	@Override
	public void removeEdge(UndirectedNode x, UndirectedNode y) {
		matrix[x.getLabel()][y.getLabel()]--;
		matrix[y.getLabel()][x.getLabel()]--;
	}

	/**
	 * @param x arc from
	 * @param y arc to
     * adds the edge (x,y), we allow the multi-graph.
     */
	@Override
	public void addEdge(UndirectedNode x, UndirectedNode y) {
		matrix[x.getLabel()][y.getLabel()]++;
		matrix[y.getLabel()][x.getLabel()]++;
	}

	
	/**
     * @return the adjacency matrix representation int[][] of the graph
     */
	public int[][] toAdjacencyMatrix() {
		return this.matrix;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Adjacency Matrix: \n");
		for (int[] ints : this.matrix) {
			for (int anInt : ints) {
				s.append(anInt).append(" ");
			}
			s.append("\n");
		}
		s.append("\n");
		return s.toString();
	}

	public static void main(String[] args) {
		int[][] mat2 = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
		// GraphTools.afficherMatrix(mat2);

		AdjacencyMatrixUndirectedGraph am = new AdjacencyMatrixUndirectedGraph(mat2);
		System.out.println(am);
		System.out.println("N = "+am.getNbNodes()+ "\nM = "+am.getNbEdges());

		List<Integer> t2 = am.getNeighbours(new UndirectedNode(2));
		List<String> t2s = new ArrayList<String>();
		for (Integer integer : t2) { t2s.add(integer.toString()); }
		System.out.println(String.join(", ", t2s));

		am.isEdge(new UndirectedNode(2), new UndirectedNode(5));
		for(int i = 0; i < 3;i++)
			am.addEdge(new UndirectedNode(2), new UndirectedNode(5));

		System.out.println(am);
		am.removeEdge(new UndirectedNode(2), new UndirectedNode(5));
		System.out.println(am);
		// A completer
	}
}
