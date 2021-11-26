package Graphs.Abstraction;

import Graphs.Nodes.UndirectedNode;

public interface IUndirectedGraph extends IGraph {

	/**
	 * @return the number of edges in the graph
 	 */
	int getNbEdges();

	/**
	 * @param x arc A
	 * @param y arc B
	 * @return true if there is an edge between x and y
	 */
	boolean isEdge(UndirectedNode x, UndirectedNode y);

	/**
	 * @param x arc A
	 * @param y arc B
	 * Removes edge (x,y) if there exists one
     */
	void removeEdge(UndirectedNode x, UndirectedNode y);

	/**
	 * @param x arc A
	 * @param y arc B
	 * Adds edge (x,y), requires that nodes x and y already exist
     */
	void addEdge(UndirectedNode x, UndirectedNode y);

}
