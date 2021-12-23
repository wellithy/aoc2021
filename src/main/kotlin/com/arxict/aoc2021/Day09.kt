package com.arxict.aoc2021

import java.util.*

class Day09(val numbers: List<List<Int>>) {
    private val bottom = numbers.lastIndex
    private val right = numbers.first().lastIndex

    private fun Int.test(i: Int, j: Int): Boolean =
        i !in 0..bottom || j !in 0..right || this < numbers[i][j]

    private fun loop(action: (Cell) -> Unit) =
        numbers.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                cell.apply {
                    if (this < 9 && test(i, j - 1) && test(i, j + 1) && test(i - 1, j) && test(i + 1, j))
                        action(i to j)
                }
            }
        }

    fun part1(): Int {
        var risk = 0
        loop { risk += numbers[it]!!.inc() }
        return risk
    }

    private fun basin(cell: Cell): Int {
        val visited = mutableSetOf<Cell>()
        val stack = ArrayDeque<Cell>().apply { addFirst(cell) }

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
        loop { queue.apply {
                add(basin(it))
                if (size > max) poll()
            }
        }
        return queue.reduce(Int::times)
    }
}
