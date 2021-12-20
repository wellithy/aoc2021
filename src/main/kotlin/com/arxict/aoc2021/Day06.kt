package com.arxict.aoc2021

import java.math.BigInteger

class Day06(val lines: List<Int>) {
    private companion object {
        const val states = 9
        const val restart = states - 3

        class State(lines: List<Int>) {
            val frequency = Array<BigInteger>(states) { BigInteger.ZERO }.apply { lines.forEach { this[it]++ } }

            fun advance(days: Int = 1) =
                repeat(days) {
                    frequency.rotate()
                    frequency[restart] += frequency.last()
                }

            fun list(): List<Int> =
                frequency.flatMapIndexed { index, count -> MutableList(count.intValueExact()) { index } }

            val size: BigInteger
                get() = frequency.sumOf { it }
        }
    }

    fun state(): Sequence<List<Int>> =
        State(lines).let {
            generateSequence {
                it.advance()
                it.list()
            }
        }

    fun size(days: Int): BigInteger =
        State(lines).let {
            it.advance(days)
            it.size
        }
}
