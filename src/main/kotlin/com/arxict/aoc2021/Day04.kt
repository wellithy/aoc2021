package com.arxict.aoc2021


class Day04(val lines: List<String>) {
    private val numbers = lines.numbers()
    private fun mutableBoards() = ((1 + separatorLines)..lines.size step (boardRows + separatorLines))
        .map {
            Board(lines.subList(it, it + boardRows))
        }

    private companion object {
        const val boardRows = 5
        const val separatorLines = 1

        class MutableBoard(val state: List<Array<Int?>>) {
            val cells = buildMap {
                state.flatMapIndexed { i, row ->
                    row.mapIndexed { j, cell ->
                        this[cell] = i to j
                    }
                }
            }
            var sum = state.sumOf { it.reduce { i, j -> i?.plus(j!!) }!! }
            fun take(num: Int): Int? =
                cells[num]?.let { (i, j) ->
                    require(state[i][j] != null) { "$num is drawn twice" }
                    sum -= num
                    state[i][j] = null
                    (sum * num).takeIf { state[i].all { it == null } || state.all { it[j] == null } }
                }
        }

        fun Board(lines: List<String>): MutableBoard =
            MutableBoard(lines.map { spaceSeparator.split(it.trim()).map(String::toInt).toTypedArray() })
    }


    fun part1(): Int {
        mutableBoards().apply {
            numbers.forEach { num ->
                forEach { board ->
                    board.take(num)?.let { return it }
                }
            }
            error("No first winners!")
        }
    }

    fun part2(): Int {
        var last: Int? = null
        mutableBoards().toMutableList().apply {
            numbers.forEach { num ->
                iterator().apply {
                    forEach { board ->
                        board.take(num)?.let {
                            last = it
                            remove()
                        }
                    }
                }
            }
        }
        return last!!
    }
}