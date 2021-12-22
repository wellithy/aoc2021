package com.arxict.aoc2021

import java.util.concurrent.atomic.AtomicInteger

private typealias Node = String
private typealias Graph = Map<Node, Set<Node>>

class Day12(val lines: List<String>) {

    private companion object {
        const val START: Node = "start"
        const val END: Node = "end"

        fun Graph(
            lines: List<String>,
            delimiter: Char = '-'
        ): Graph = buildMap<Node, MutableSet<Node>>
        {
            fun Node.connect(to: Node) {
                if (this != END && to != START)
                    getOrPut(this) { mutableSetOf() }.add(to)
            }
            lines.forEach {
                it.split(delimiter).let { (n1, n2) ->
                    n1.connect(n2)
                    n2.connect(n1)
                }
            }
        }

        val Node.oneVisit: Boolean get() = first().isLowerCase()

        fun Graph.paths(): Int {
            val visited = mutableSetOf<Node>()
            val paths = AtomicInteger()
            fun dfs(node: Node) {
                if (node.oneVisit && !visited.add(node)) return
                if (node == END) paths.incrementAndGet()
                this[node]?.forEach { dfs(it) }
                if (node.oneVisit) visited -= node
            }
            dfs(START)
            return paths.get()
        }
    }

    fun part1(): Int =
        Graph(lines).paths()

}
