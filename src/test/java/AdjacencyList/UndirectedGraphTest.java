package AdjacencyList;
import Graphs.AdjacencyList.DirectedGraph;
import Graphs.AdjacencyList.UndirectedGraph;
import Graphs.GraphAlgorithms.GraphTools;
import Graphs.Nodes.DirectedNode;
import Graphs.Nodes.UndirectedNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/*
int[][] mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);

0 0 0 1 1 0 0 1 0 1 
0 0 1 1 0 0 0 1 1 1
0 1 0 1 0 0 0 0 0 1
1 1 1 0 0 0 0 1 0 1
1 0 0 0 0 1 0 0 0 1
0 0 0 0 1 0 1 1 1 0
0 0 0 0 0 1 0 0 0 1
1 1 0 1 0 1 0 0 1 0
0 1 0 0 0 1 0 1 0 0
1 1 1 1 1 0 1 0 0 0

neighbours of node-0 : node-3 node-4 node-7 node-9
neighbours of node-1 : node-2 node-3 node-7 node-8 node-9
neighbours of node-2 : node-1 node-3 node-9
neighbours of node-3 : node-0 node-1 node-2 node-7 node-9
neighbours of node-4 : node-0 node-5 node-9
neighbours of node-5 : node-4 node-6 node-7 node-8
neighbours of node-6 : node-5 node-9
neighbours of node-7 : node-0 node-1 node-3 node-5 node-8
neighbours of node-8 : node-1 node-5 node-7
neighbours of node-9 : node-0 node-1 node-2 node-3 node-4 node-6
*/

public class UndirectedGraphTest {

    @Test
    void isEdge() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        UndirectedGraph al = new UndirectedGraph(Matrix);
        UndirectedNode from = al.getNodes().get(0);
        UndirectedNode  to = al.getNodes().get(3);

        //WHEN
        Boolean result = al.isEdge(from, to);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    void removeEdge() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        UndirectedGraph al = new UndirectedGraph(Matrix);
        UndirectedNode  from = al.getNodes().get(0);
        UndirectedNode  to = al.getNodes().get(3);
        Assertions.assertTrue(al.isEdge(from, to));

        //WHEN
        al.removeEdge(from, to);
        Boolean result = al.isEdge(from,to);

        //THEN
        Assertions.assertFalse(result);
    }

    @Test
    void addEdge() {
        //GIVEN
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        UndirectedGraph al = new UndirectedGraph(Matrix);
        UndirectedNode  from = al.getNodes().get(0);
        UndirectedNode  to = al.getNodes().get(5);
        Assertions.assertFalse(al.isEdge(from, to));

        //WHEN
        al.addEdge(from, to);
        Boolean result = al.isEdge(from, to);

        //THEN
        Assertions.assertTrue(result);
    }
}
