package com.arxict.aoc2021

import java.util.*

private typealias Point = Pair<Int, Int>

class Day09(val numbers: List<List<Int>>) {
    private val bottom = numbers.lastIndex
    private val right = numbers.first().lastIndex

    private companion object {

    }

    private fun lowPoint(i: Int, j: Int, cell: Int): Boolean =
        (i == 0 || cell < numbers[i - 1][j])
                && (i == bottom || cell < numbers[i + 1][j])
                && (j == 0 || cell < numbers[i][j - 1])
                && (j == right || cell < numbers[i][j + 1])

    fun part1(): Int {
        var risk = 0
        numbers.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                if (cell < 9 && lowPoint(i, j, cell))
                    risk += cell.inc()
            }
        }
        return risk
    }

    private fun ArrayDeque<Point>.test(i: Int, j: Int, cell: Int, visited: MutableSet<Point>) {
        if (i in 0..bottom &&
            j in 0..right &&
            numbers[i][j] in (cell + 1)..8
        ) (i to j).let { if (visited.add(it)) addFirst(it) }
    }

    private fun basin(i: Int, j: Int): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addFirst(i to j)
        while (stack.isNotEmpty())
            stack.removeFirst().let { point ->
                visited.add(point)
                val (x, y) = point
                val cell = numbers[x][y]
                stack.test(x - 1, y, cell, visited)
                stack.test(x + 1, y, cell, visited)
                stack.test(x, y - 1, cell, visited)
                stack.test(x, y + 1, cell, visited)
            }
        return visited.size
    }

    fun part2(): Int {
        val queue = PriorityQueue<Int>(4)
        numbers.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                if (cell < 9 && lowPoint(i, j, cell))
                    queue.apply {
                        add(basin(i, j))
                        if (size > 3) poll()
                    }
            }
        }
        return queue.reduce(Int::times)
    }
}
