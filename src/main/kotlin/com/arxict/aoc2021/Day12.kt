package com.arxict.aoc2021

private typealias Node = String
private typealias Graph = Map<Node, Set<Node>>

class Day12(val lines: List<String>) {

    private companion object {
        const val START: Node = "start"
        const val END: Node = "end"

        fun Graph(lines: List<String>): Graph = buildMap<Node, MutableSet<Node>> {
            fun Node.connect(to: Node) {
                if (this != END && to != START)
                    getOrPut(this) { mutableSetOf() } += to
            }
            lines.forEach {
                it.split('-').apply {
                    first().connect(last())
                    last().connect(first())
                }
            }
        }

        val Node.oneVisit: Boolean get() = first().isLowerCase()

        fun Graph.paths(): Int {
            val visited = mutableSetOf<Node>()
            var count = 0
            fun dfs(node: Node) {
                if (!node.oneVisit || visited.add(node)) {
                    if (node == END) count++
                    else this[node]?.forEach(::dfs)
                    visited -= node
                }
            }
            dfs(START)
            return count
        }
    }

    fun part1(): Int =
        Graph(lines).paths()

}
