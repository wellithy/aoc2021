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

        fun MutableMap<SDD, Char>.putSingle(list: List<SDD>, value: Char): SDD =
            list.single().also { require(put(it, value) == null) }

        fun MutableMap<SDD, Char>.putSingles(
            patterns: List<SDD>,
            first: Char, second: Char, third: Char, size: Int,
            par1: SDD, size1: Int,
            par2: SDD, size2: Int,
        ) =
            patterns.filter { it.size == size }
                .partition { (it subtract par1).size == size1 }
                .let { p1 ->
                    putSingle(p1.first, first)
                    p1.second.partition { (it subtract par2).size == size2 }.let { p2 ->
                        putSingle(p2.first, second)
                        putSingle(p2.second, third)
                    }
                }

        fun MutableMap<SDD, Char>.unique(patterns: List<SDD>, size: Int, value: Char): SDD =
            putSingle(patterns.filter { it.size == size }, value)


        fun Entry.number(): Int =
            buildMap {
                val one = unique(patterns, 2, '1')  // 1 = cf (2)
                val four = unique(patterns, 4, '4')  // 4 = bcdf (4)
                unique(patterns, 3, '7')  // 7 = acf (3)
                val eight = unique(patterns, 7, '8')  // 8 = abcdefg (7)
                val aeg = eight subtract four // 8 - 4 = aeg
                putSingles(
                    patterns,
                    '2', '3', '5', 5, // 2 | 3 | 5 = acdeg | acdfg | abdfg (5)
                    aeg, 2, // 2 | 3 | 5 - aeg = cd | cdf | bdf
                    one, 3, //  3 | 5 - cf = adg | abdg
                )
                putSingles(
                    patterns,
                    '9', '0', '6', 6,  // 0 | 6 | 9 = abcefg | abdefg | abcdfg (6)
                    aeg, 4, // 0 | 6 | 9 - aeg = bcf | bdf | bcdf
                    one, 4, //  0 | 6 - cf = abeg | abdeg
                )
            }.let { map ->
                output.map { map[it] }.joinToString("").toInt()
            }
    }

    fun part1(): Int = entries.sumOf { it.uniqueLengths() }

    fun part2(): Int = entries.sumOf { it.number() }
}
