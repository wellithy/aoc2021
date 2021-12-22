package com.arxict.aoc2021


class Day11(val numbers: List<List<Int>>) {

    private companion object {
        class Point(val row: Int, val column: Int)
        class Octopuses(numbers: List<List<Int>>) {
            private val state = numbers.map { it.toIntArray() }
            private val validRows = 0..numbers.lastIndex
            private val validColumns = 0..numbers.first().lastIndex
            val size = numbers.size * numbers.first().size

            override fun toString(): String =
                state.joinToString("\n") { it.joinToString("") }

            fun step(): Int {
                val stack = ArrayDeque<Point>()
                var illuminated = 0
                fun inc(row: Int, column: Int) {
                    if (++state[row][column] > 9) {
                        state[row][column] = 0
                        illuminated++
                        stack.addFirst(Point(row, column))
                    }
                }
                state.forEachIndexed { row, array -> array.indices.forEach { column -> inc(row, column) } }
                while (stack.isNotEmpty())
                    stack.removeFirst().let { point ->
                        for (i in point.row - 1..point.row + 1)
                            if (i in validRows) for (j in point.column - 1..point.column + 1)
                                if (j in validColumns && state[i][j] > 0) inc(i, j)
                    }
                return illuminated
            }
        }
    }

    fun sample(): Int =
        Octopuses(numbers).let { octopus ->
            (0..2).sumOf {
                println("\nStep $it\n$octopus")
                octopus.step().also { flashed -> println(flashed) }
            }
        }

    fun part1(steps: Int = 100): Int =
        Octopuses(numbers).let { octopus ->
            (1..steps).sumOf { octopus.step() }
        }

    fun part2(): Int {
        val octopus = Octopuses(numbers)
        for (step in 1..Int.MAX_VALUE)
            if (octopus.step() == octopus.size)
                return step
        error("Not found!")
    }
}
