package com.arxict.aoc2021

import java.util.*

class Day09(val numbers: List<List<Int>>) {
    private val bottom = numbers.lastIndex
    private val right = numbers.first().lastIndex

    private fun Int.test(i: Int, j: Int): Boolean =
        i !in 0..bottom || j !in 0..right || this < numbers[i][j]

    private fun loop(action: (Int, Int) -> Unit) =
        numbers.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                cell.apply {
                    if (this < 9 && test(i, j - 1) && test(i, j + 1) && test(i - 1, j) && test(i + 1, j))
                        action(i, j)
                }
            }
        }

    fun part1(): Int {
        var risk = 0
        loop { i, j -> risk += numbers[i][j].inc() }
        return risk
    }

    private fun basin(i: Int, j: Int): Int {
        val visited = mutableSetOf<Point>()
        val stack = ArrayDeque<Point>().apply { addFirst(i to j) }

        fun Int.test(x: Int, y: Int) {
            if (x in 0..bottom && y in 0..right && numbers[x][y] in inc()..8)
                (x to y).let { if (visited.add(it)) stack.addFirst(it) }
        }

        while (stack.isNotEmpty())
            stack.removeFirst().let { point ->
                visited.add(point)
                val (x, y) = point
                numbers[x][y].apply {
                    test(x - 1, y)
                    test(x + 1, y)
                    test(x, y - 1)
                    test(x, y + 1)
                }
            }
        return visited.size
    }

    fun part2(): Int {
        val max = 3
        val queue = PriorityQueue<Int>(max.inc())
        loop { i, j ->
            queue.apply {
                add(basin(i, j))
                if (size > max) poll()
            }
        }
        return queue.reduce(Int::times)
    }
}
