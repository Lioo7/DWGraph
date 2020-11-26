package tests;

import api.DWGraph_Algo;
import api.DWGraph_DS;
import api.NodeData;
import api.node_data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {
    public final double INFINITY = Double.POSITIVE_INFINITY;
    DWGraph_Algo graph_algo = new DWGraph_Algo();
    DWGraph_DS myGraph = new DWGraph_DS();
    DWGraph_DS connected_graph = new DWGraph_DS();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 9; i++){
            NodeData newNode = new NodeData(i);
            myGraph.addNode(newNode);
        }

        myGraph.connect(0,1,3);
        myGraph.connect(0,2,9);
        myGraph.connect(1,2,7);
        myGraph.connect(1,3,5);
        myGraph.connect(1,4,1);
        myGraph.connect(3,4,12);
        myGraph.connect(5,4,11);
        myGraph.connect(6,4,7);
        myGraph.connect(7,3,8);
        myGraph.connect(8,2,10);

        for (int i = 0; i < 5; i++){
            NodeData newNode = new NodeData(i);
            myGraph.addNode(newNode);
        }

        connected_graph.connect(0,1,5);
        connected_graph.connect(0,2,30);
        connected_graph.connect(1,2,11);
    }

    @AfterEach
    void tearDown() {
        Collection<node_data> vertices = myGraph.getV();
        for (node_data currNode : vertices){
            currNode.setInfo("WHITE");
        }
    }

    @Test
    void init() {
        graph_algo.init(myGraph);
        String expected = graph_algo.toString();
        graph_algo.init(connected_graph);
        String actual = graph_algo.toString();
        assertNotEquals(expected, actual);
    }

    @Test
    void saveLoad() {
        graph_algo.init(myGraph);
        assertTrue(graph_algo.save("data/myGraph.txt"));
        assertTrue(graph_algo.load("data/myGraph.txt"));

        graph_algo.init(connected_graph);
        assertTrue(graph_algo.save("data/connected_graph.txt"));
        assertTrue(graph_algo.load("data/connected_graph.txt"));
        assertNotEquals(connected_graph,myGraph);
    }

    @Test
    void getGraph() {
        graph_algo.init(myGraph);
        assertEquals(myGraph,graph_algo.myGraph);
    }

    @Test
    void copy() {
        graph_algo.init(myGraph);
        DWGraph_DS copyGraph = (DWGraph_DS) graph_algo.copy();
        assertEquals(copyGraph,myGraph);
    }

    @Test
    void isConnected() {
        graph_algo.init(myGraph);
        assertFalse(graph_algo.isConnected());
        graph_algo.init(connected_graph);
        assertTrue(graph_algo.isConnected());
    }

    @Test
    void shortestPathDist() {
        graph_algo.init(myGraph);
        double result = graph_algo.shortestPathDist(1, 4);
        System.out.println(result);
        assertEquals(result, 1);
    }

    @Test
    void shortestPath() {
        graph_algo.init(myGraph);
        ArrayList<node_data> expected = (ArrayList<node_data>) graph_algo.shortestPath(0, 4);
        ArrayList<node_data> actual = new ArrayList<>();
        actual.add(myGraph.getNode(0));
        actual.add(myGraph.getNode(1));
        actual.add(myGraph.getNode(4));
        assertEquals(expected, actual);
    }
}