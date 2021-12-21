package com.arxict.aoc2021

private typealias SDD = Set<Char>

class Day08(val lines: List<String>) {
    private val entries = lines.map(::Entry)

    private companion object {
        class Entry(val patterns: List<SDD>, val output: List<SDD>)

        fun String.sddList(): List<SDD> =
            trim().split(' ').map(String::toSet)

        fun Entry(line: String): Entry =
            line.split('|').let { Entry(it[0].sddList(), it[1].sddList()) }

        val uniqueLengths = setOf(2, 4, 3, 7) // 1 4 7 8

        fun Entry.uniqueLengths(): Int = output.count { it.size in uniqueLengths }

        fun SDD.intersectSize(other: SDD): Int = count { it in other }

        fun Entry.number(): Int =
            arrayOfNulls<SDD>(10).apply {
                patterns.forEach {
                    when (it.size) {
                        2 -> this[1] = it  // 1 = cf (2)
                        4 -> this[4] = it  // 4 = bcdf (4)
                        3 -> this[7] = it // 7 = acf (3)
                        7 -> this[8] = it   // 8 = abcdefg (7)
                    }
                }
                val cf = this[1]!!
                val bd = this[4]!! - cf // bcdf - cf
                patterns.forEach {
                    when (it.size) {
                        5 -> when { // 2 | 3 | 5 = acdeg | acdfg | abdfg (5)
                            cf.intersectSize(it) == 2 -> this[3] = it // 2 | 3 | 5 X cf = c | cf | f
                            bd.intersectSize(it) == 2 -> this[5] = it // 2 | 3 | 5 X bd = d | d | bd
                            else -> this[2] = it
                        }
                        6 -> when { // 0 | 6 | 9 = abcefg | abdefg | abcdfg (6)
                            cf.intersectSize(it) == 1 -> this[6] = it // 0 | 6 | 9 X cf = cf | f | cf
                            bd.intersectSize(it) == 1 -> this[0] = it // 0 | 6 | 9 X bd = b | bd | bd
                            else -> this[9] = it
                        }
                    }
                }
            }.let { array ->
                output.map(array::indexOf).joinToString("").toInt()
            }
    }

    fun part1(): Int = entries.sumOf { it.uniqueLengths() }

    fun part2(): Int = entries.sumOf { it.number() }
}
