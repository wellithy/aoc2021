package com.arxict.aoc2021

private typealias SDD = Set<Char>

class Day08(val lines: List<String>) {
    private val entries = lines.map(::Entry)

    private companion object {
        class Entry(val patterns: List<SDD>, val output: List<SDD>)

        fun String.sddList(): List<SDD> = split(' ').map(String::toSet)

        fun Entry(line: String): Entry = line.split(" | ").let { Entry(it[0].sddList(), it[1].sddList()) }

        val unique = mapOf(2 to 1, 4 to 4, 3 to 7, 7 to 8)

        fun Entry.uniqueLengths(): Int = output.count { it.size in unique }

        fun List<SDD>.decode(): Array<SDD?> {
            val array = arrayOfNulls<SDD>(10)
            forEach { pattern -> unique[pattern.size]?.let { array[it] = pattern } }
            val cf = array[1]!!
            val bd = array[4]!! - cf // bcdf - cf
            fun SDD.populate(len: Int, a: Int, b: Int, c: Int) =
                when (len) {
                    cf.intersectSize(this) -> array[a] = this
                    bd.intersectSize(this) -> array[b] = this
                    else -> array[c] = this
                }
            forEach { pattern ->
                when (pattern.size) {
                    5 -> pattern.populate(2, 3, 5, 2)
                    6 -> pattern.populate(1, 6, 0, 9)
                }
            }
            return array
        }

        fun Entry.number(): Int =
            patterns.decode().let { array -> output.map(array::indexOf).toInt() }
    }

    fun part1(): Int = entries.sumOf { it.uniqueLengths() }

    fun part2(): Int = entries.sumOf { it.number() }
}
