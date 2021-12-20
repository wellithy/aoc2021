package com.arxict.aoc2021

import kotlin.math.absoluteValue

class Day07(val numbers: List<Int>) {

    private companion object {
        class Trial(numbers: List<Int>, val weight: (Int) -> Int) {
            val frequency = numbers.groupingBy { it }.eachCount()

            fun solve(): Int {
                val keys = frequency.keys.sorted()
                var max = keys.last()
                var min = keys.first()
                while (max > min) {
                    val mid = (min + max) / 2
                    val cost = cost(mid)
                    when {
                        cost > cost(mid.inc()) -> min = mid.inc()
                        cost > cost(mid.dec()) -> max = mid.dec()
                        else -> return cost
                    }
                }
                return cost(min)
            }

            fun cost(guess: Int): Int =
                frequency.map { (num, freq) -> freq * weight(num - guess) }.sum()
        }
    }

    fun part1(): Int =
        Trial(numbers) { it.absoluteValue }.solve()

    fun part2(): Int =
        Trial(numbers) { num -> num.absoluteValue.let { it * it.inc() / 2 } }.solve()
}
