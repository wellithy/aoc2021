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

        fun Entry.number(): Int {
            val array = arrayOfNulls<SDD>(10)
            patterns.forEach {
                when (it.size) {
                    2 -> array[1] = it  // 1 = cf (2)
                    4 -> array[4] = it  // 4 = bcdf (4)
                    3 -> array[7] = it // 7 = acf (3)
                    7 -> array[8] = it   // 8 = abcdefg (7)
                }
            }
            val cf = array[1]!!
            val bd = array[4]!! - cf // bcdf - cf
            patterns.forEach {
                when (it.size) {
                    5 -> when { // 2 | 3 | 5 = acdeg | acdfg | abdfg (5)
                        cf.intersectSize(it) == 2 -> array[3] = it // 2 | 3 | 5 X cf = c | cf | f
                        bd.intersectSize(it) == 2 -> array[5] = it // 2 | 3 | 5 X bd = d | d | bd
                        else -> array[2] = it
                    }
                    6 -> when { // 0 | 6 | 9 = abcefg | abdefg | abcdfg (6)
                        cf.intersectSize(it) == 1 -> array[6] = it // 0 | 6 | 9 X cf = cf | f | cf
                        bd.intersectSize(it) == 1 -> array[0] = it // 0 | 6 | 9 X bd = b | bd | bd
                        else -> array[9] = it
                    }
                }
            }
            return output.map(array::indexOf).joinToString("").toInt()
        }
    }

    fun part1(): Int = entries.sumOf { it.uniqueLengths() }

    fun part2(): Int = entries.sumOf { it.number() }
}
