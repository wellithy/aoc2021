package com.arxict.aoc2021

import java.util.*


class Day10(val lines: List<String>) {

    private companion object {
        val closing = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val cost = mapOf(')' to 3, ']' to 57, '}' to 1_197, '>' to 25_137)
        val expense = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
        val open = closing.keys.joinToString("")
    }

    private fun String.score(): Int {
        val stack = ArrayDeque<Char>()
        forEach {
            if (it in open) stack.addFirst(it)
            else if (it != closing[stack.pollFirst()])
                return cost[it]!!
        }
        return 0
    }

    fun part1(): Int =
        lines.sumOf { it.score() }

    private fun String.value(): Long? {
        val stack = ArrayDeque<Char>()
        forEach {
            if (it in open) stack.addFirst(it)
            else if (it != closing[stack.pollFirst()])
                return null
        }
        return stack.map { expense[it]!!.toLong() }.reduce { acc, e -> Math.addExact(Math.multiplyExact(5, acc), e) }
    }

    fun part2(): Long =
        lines.mapNotNull { it.value() }.sorted().let { it[it.size / 2] }
}
