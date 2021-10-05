package java.AdjacenyMatrix;

import AdjacencyMatrix.AdjacencyMatrixUndirectedGraph;
import GraphAlgorithms.GraphTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class AdjacencyMatrixUndirectedGraphTest {

    int[][] mat;
    AdjacencyMatrixUndirectedGraph adj;

    @BeforeAll
    void initMatrix() {
        mat = GraphTools.generateGraphData(10, 35, false, true, false, 100001);
        adj = new AdjacencyMatrixUndirectedGraph(mat);
    }

    @Test
    void isEdge() {
        AdjacencyMatrixUndirectedGraph cadj = adj.Clone();

        Assertions.assertEquals(false, true);

    }

    @Test
    void removeEdge() {
        AdjacencyMatrixUndirectedGraph cadj = adj.Clone();

    }

    @Test
    void addEdge() {
        AdjacencyMatrixUndirectedGraph cadj = adj.Clone();

    }
}
