package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GraphTest {
    private val nodes = arrayOf(1, 2, 3, 4, 5, 6)
    private val edges = arrayOf(
        Pair(1, 2),
        Pair(1, 4),
        Pair(2, 3),
        Pair(2, 5),
        Pair(3, 4),
        Pair(3, 6),
        Pair(4, 5),
        Pair(5, 6)
    )
    private val graph = Graph(nodes, edges)

    @Test
    fun `test add node`() {
        val testNode = 10
        graph.addNode(testNode)
        assertEquals(testNode, graph.getNodes().last())
    }

    @Test
    fun `test add edge`() {
        val testEdge = Pair(7, 8)
        graph.addNode(testEdge.first)
        graph.addNode(testEdge.second)
        graph.addEdge(testEdge.first, testEdge.second)
        assertEquals(testEdge, graph.getEdges().last())
    }

    @Test
    fun `test remove node`() {
        val testNode = 2
        graph.removeNode(testNode)
        assertEquals(false, graph.getNodes().contains(testNode))
    }

    @Test
    fun `test remove edge`() {
        val testEdge = Pair(1, 2)
        graph.removeEdge(testEdge.first, testEdge.second)
        assertEquals(false, graph.getEdges().contains(testEdge))
    }

    @Test
    fun `test get nodes`() {
        assertEquals(nodes.toList(), graph.getNodes())
    }

    @Test
    fun `test get edges`() {
        assertEquals(edges.toList(), graph.getEdges())
    }

    @Test
    fun `test get neighbors`() {
        val testNode = 1
        assertEquals(listOf(2, 4), graph.getNeighbors(testNode))
    }

    @Test
    fun `test get number of nodes`() {
        assertEquals(nodes.size, graph.getNumberOfNodes())
    }

    @Test
    fun `test get number of edges`() {
        assertEquals(edges.size, graph.getNumberOfEdges())
    }

    @Test
    fun `test is adjacent`() {
        val testEdge = Pair(1, 2)
        assertEquals(true, graph.isAdjacent(testEdge.first, testEdge.second))
    }
}