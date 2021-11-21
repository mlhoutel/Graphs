package Graphs.Algorithms;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;

public class PRIM {
    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(5, 6, false, true, false, 100001);
        DirectedGraph al = new DirectedGraph(mat);
    }  
}
