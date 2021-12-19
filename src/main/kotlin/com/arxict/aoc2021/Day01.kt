package com.arxict.aoc2021

class Day01(val lines: List<String>) {
    fun part1(): Int =
        sonarSweep(2)

    fun part2(): Int =
        sonarSweep(4)

    private fun sonarSweep(window: Int): Int =
        lines.map(String::toInt).windowed(window).count { it.last() > it.first() }
}
