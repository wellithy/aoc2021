package com.arxict.aoc2021

private typealias Node = String
private typealias Graph = Map<Node, Set<Node>>
private typealias Path = List<Node>

class Day12(val lines: List<String>) {

    private companion object {
        const val START: Node = "start"
        const val END: Node = "end"

        fun Graph(lines: List<String>, delimiter: Char = '-'): Graph = buildMap<Node, MutableSet<Node>> {
            fun Node.connect(to: Node) =
                getOrPut(this) { mutableSetOf() }.add(to)
            lines.forEach {
                it.split(delimiter).let { (n1, n2) ->
                    n1.connect(n2)
                    n2.connect(n1)
                }
            }
        }

        fun Graph.paths(): List<Path> {
            val visited = mutableSetOf<Node>()
            val current = ArrayDeque<Node>()
            fun dfs(node: Node, paths: MutableList<Path>): List<Path> {
                if (node.first().isLowerCase() && !visited.add(node)) return paths
                current.addLast(node)
                if (node == END) {
                    paths += current.toList()
                    visited -= node
                    current.removeLast()
                    return paths
                }
                this[node]?.forEach { dfs(it, paths) }
                visited -= node
                current.removeLast()
                return paths
            }
            return dfs(START, mutableListOf())
        }
    }

    fun part1(): Int =
        Graph(lines).paths().size

}
