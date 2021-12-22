package com.arxict.aoc2021

import java.util.*


class Day10(val lines: List<String>) {

    private companion object {
        val closing = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val cost = mapOf<Char,Long>(')' to 3, ']' to 57, '}' to 1_197, '>' to 25_137)
        val value = mapOf<Char,Long>('(' to 1, '[' to 2, '{' to 3, '<' to 4)
        val open = closing.keys.joinToString("")
    }

    private fun String.value(expense: Boolean): Long? {
        val stack = ArrayDeque<Char>()
        forEach {
            if (it in open) stack.addFirst(it)
            else if (it != closing[stack.pollFirst()])
                return if (expense) null else cost[it]!!
        }
        return if (!expense) null
        else stack.map { value[it]!! }.reduce { acc, e -> Math.addExact(Math.multiplyExact(5, acc), e) }
    }

    fun part1(): Long =
        lines.mapNotNull { it.value(false) }.sum()

    fun part2(): Long =
        lines.mapNotNull { it.value(true) }.sorted().let { it[it.size / 2] }
}
