package collections

class Graph<T> {
    // Variables
    private var nodes: MutableList<T> = ArrayList()
    private var adjacencyList: MutableMap<T, MutableList<T>> = HashMap()

    // Constructors
    constructor()
    constructor(nodes: Array<T>, edges: Array<Pair<T, T>>) {
        for (node in nodes)
            addNode(node)
        for (edge in edges)
            addEdge(edge.first, edge.second)
    }

    // Methods
    fun addNode(node: T) {
        if (!nodes.contains(node)) {
            nodes.add(node)
            adjacencyList[node] = ArrayList()
        }
    }

    fun addEdge(node1: T, node2: T) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            adjacencyList[node1]!!.add(node2)
            adjacencyList[node2]!!.add(node1)
        }
    }

    fun removeNode(node: T) {
        if (nodes.contains(node)) {
            nodes.remove(node)
            adjacencyList.remove(node)

            for (list in adjacencyList.values)
                list.remove(node)
        }
    }

    fun removeEdge(node1: T, node2: T) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            adjacencyList[node1]!!.remove(node2)
            adjacencyList[node2]!!.remove(node1)
        }
    }

    fun getNodes(): List<T> {
        return nodes
    }

    fun getEdges(): List<Pair<T, T>> {
        val edges = ArrayList<Pair<T, T>>()
        for (node in nodes) {
            for (neighbor in adjacencyList[node]!!) {
                edges.add(Pair(node, neighbor))
            }
        }
        return edges
    }

    fun getNeighbors(node: T): List<T> {
        return if (nodes.contains(node))
            adjacencyList[node]!!
        else
            ArrayList()
    }

    fun getNumberOfNodes(): Int {
        return nodes.size
    }

    fun getNumberOfEdges(): Int {
        var edges = 0
        for (node in nodes)
            edges += adjacencyList[node]!!.size
        return edges / 2
    }

    fun isAdjacent(node1: T, node2: T): Boolean {
        if (nodes.contains(node1) && nodes.contains(node2))
            return adjacencyList[node1]!!.contains(node2)
        return false
    }

    fun depthFirstSearch(node: T): List<T> {
        val visited = ArrayList<T>()
        val stack = ArrayList<T>()
        stack.add(node)
        while (!stack.isEmpty()) {
            val current = stack.removeAt(0)
            if (!visited.contains(current)) {
                visited.add(current)
                for (neighbor in adjacencyList[current]!!)
                    stack.add(0, neighbor)
            }
        }
        return visited
    }

    fun breadthFirstSearch(node: T): List<T> {
        val visited = ArrayList<T>()
        val queue = ArrayList<T>()
        queue.add(node)
        while (!queue.isEmpty()) {
            val current = queue.removeAt(0)
            if (!visited.contains(current)) {
                visited.add(current)
                for (neighbor in adjacencyList[current]!!)
                    queue.add(neighbor)
            }
        }
        return visited
    }

    fun getShortestPath(node1: T, node2: T): List<T> {
        val visited = ArrayList<T>()
        val queue = ArrayList<ArrayList<T>>()
        queue.add(arrayListOf(node1))
        while (!queue.isEmpty()) {
            val path = queue.removeAt(0)
            val current = path[path.size - 1]
            if (current == node2)
                return path
            if (!visited.contains(current)) {
                visited.add(current)
                for (neighbor in adjacencyList[current]!!) {
                    val newPath = ArrayList(path)
                    newPath.add(neighbor)
                    queue.add(newPath)
                }
            }
        }
        return arrayListOf()
    }

    fun getMinimumSpanningTree(): Graph<T> {
        val mst = Graph<T>()
        val visited = ArrayList<T>()
        val queue = ArrayList<Pair<T, T>>()
        queue.add(Pair(nodes[0], nodes[0]))
        while (!queue.isEmpty()) {
            val (parent, current) = queue.removeAt(0)
            if (!visited.contains(current)) {
                visited.add(current)
                mst.addNode(current)
                mst.addEdge(parent, current)
                for (neighbor in adjacencyList[current]!!)
                    queue.add(Pair(current, neighbor))
            }
        }
        return mst
    }

    fun getMinimumSpanningTreeWeight(): Int {
        var weight = 0
        val visited = ArrayList<T>()
        val queue = ArrayList<Triple<T, T, Int>>()
        queue.add(Triple(nodes[0], nodes[0], 0))
        while (!queue.isEmpty()) {
            val (parent, current, currentWeight) = queue.removeAt(0)
            if (!visited.contains(current)) {
                visited.add(current)
                weight += currentWeight
                for (neighbor in adjacencyList[current]!!)
                    queue.add(Triple(current, neighbor, 1))
            }
        }
        return weight
    }

    fun getDiameter(): Int {
        var max = 0
        for (node in nodes) {
            val path = breadthFirstSearch(node)
            max = Math.max(max, path.size)
        }
        return max
    }

    fun getConnectedComponents(): List<Graph<T>> {
        val components = ArrayList<Graph<T>>()
        val visited = ArrayList<T>()
        for (node in nodes) {
            if (!visited.contains(node)) {
                val component = Graph<T>()
                val nodes = breadthFirstSearch(node)
                for (node in nodes) {
                    visited.add(node)
                    component.addNode(node)
                    for (neighbor in adjacencyList[node]!!) {
                        if (nodes.contains(neighbor))
                            component.addEdge(node, neighbor)
                    }
                }
                components.add(component)
            }
        }
        return components
    }

    fun areConnected(node1: T, node2: T): Boolean {
        return breadthFirstSearch(node1).contains(node2)
    }

    fun getClosenessCentrality(node: T): Double {
        val pathLengths = ArrayList<Int>()
        for (other in nodes) {
            if (other != node)
                pathLengths.add(getShortestPath(node, other).size - 1)
        }
        var sum = 0
        for (length in pathLengths)
            sum += length
        return if (sum == 0)
            0.0
        else
            1.0 / sum
    }

    fun getBetweennessCentrality(node: T): Double {
        val paths = ArrayList<List<T>>()
        for (i in nodes.indices) {
            for (j in i + 1 until nodes.size) {
                val path = getShortestPath(nodes[i], nodes[j])
                if (path.isNotEmpty())
                    paths.add(path)
            }
        }
        var numberOfPathsThroughNode = 0
        for (path in paths)
            if (path.contains(node))
                numberOfPathsThroughNode++
        return numberOfPathsThroughNode.toDouble() / paths.size
    }

    fun getEccentricity(node: T): Int {
        var max = 0
        for (other in nodes) {
            if (other != node) {
                val path = getShortestPath(node, other)
                max = Math.max(max, path.size - 1)
            }
        }
        return max
    }

    fun getRadius(): Int {
        var min = Int.MAX_VALUE
        for (node in nodes)
            min = Math.min(min, getEccentricity(node))
        return min
    }

    fun getDegreeCentrality(node: T): Double {
        return adjacencyList[node]!!.size.toDouble() / (nodes.size - 1)
    }
}