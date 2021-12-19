package com.arxict.aoc2021

import kotlin.math.absoluteValue


class Day05(val lines: List<String>) {
    private companion object {
        data class Point(val x: Int, val y: Int)

        val coordinateSeparator: Regex = Regex(",")

        fun Point(str: String): Point =
            str.trim().split(coordinateSeparator).map { it.trim().toInt() }.let { (x, y) -> Point(x, y) }

        enum class LintType {
            horizontal, vertical, diagonal;

            companion object {
                fun of(first: Point, second: Point): LintType = when {
                    first.x == second.x -> vertical
                    first.y == second.y -> horizontal
                    (first.x - second.x).absoluteValue == (first.y - second.y).absoluteValue -> diagonal
                    else -> error("Unsupported $first $second")
                }
            }
        }

        fun range(start: Int, end: Int): Sequence<Int> =
            IntProgression.fromClosedRange(start, end, if (start <= end) 1 else -1).asSequence()

        class Line(val start: Point, val end: Point) {
            fun points(diagonal: Boolean): Sequence<Point> = when (LintType.of(start, end)) {
                LintType.horizontal -> range(start.x, end.x).map { Point(it, start.y) }
                LintType.vertical -> range(start.y, end.y).map { Point(start.x, it) }
                LintType.diagonal -> if (!diagonal) emptySequence() else {
                    (range(start.x, end.x) zip range(start.y, end.y)).map { Point(it.first, it.second) }
                }
            }
        }

        val pointSeparator: Regex = Regex("->")

        fun Line(str: String): Line =
            str.split(pointSeparator).map(::Point).let { (start, end) -> Line(start, end) }

    }

    private fun count(diagonal: Boolean): Int =
        lines
            .asSequence()
            .map(::Line)
            .flatMap { it.points(diagonal) }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    fun part1(): Int =
        count(false)

    fun part2(): Int =
        count(true)
}
