package com.arxict.aoc2021

class Day01(val lines: List<String>) {
    fun part1(): Int =
        sonarSweep(1)

    fun part2(): Int =
        sonarSweep(3)

    private fun sonarSweep(window: Int): Int =
        (0..lines.lastIndex - window).count {
            lines[it + window].toInt() > lines[it].toInt()
        }
}
