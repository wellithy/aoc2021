package com.arxict.aoc2021

import java.util.*

class Day03(val lines: List<String>) {
    private val width = lines.first().length
    private val all = lines.size

    init {
        require(width < Long.SIZE_BITS)
    }

    private fun BitSet.power(): Long {
        val gamma = toLongArray()[0]
        flip(0, width)
        val epsilon = toLongArray()[0]
        return Math.multiplyExact(gamma, epsilon)
    }

    fun part1(): Long = BitSet(width).apply {
        for (column in 0 until width)
            lines.count { it[column] == '1' }
                .let { if (it > all - it) set(width - 1 - column) }
    }.power()

    private tailrec fun List<String>.calculate(oxygen: Boolean, index: Int = 0): Long =
        if (size == 1) single().toLong(2)
        else partition { it[index] == '1' }.select(oxygen).calculate(oxygen, index + 1)

    fun part2(): Long =
        Math.multiplyExact(lines.calculate(false), lines.calculate(true))

}
