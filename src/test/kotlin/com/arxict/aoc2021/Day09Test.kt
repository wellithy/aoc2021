package com.arxict.aoc2021

import kotlin.test.*

class Day09Test {
    private companion object {
        fun List<String>.nums(): List<List<Int>> =
            map { it.map(Char::digitToInt).toList() }
    }


    @Test
    fun sample() = Day09(sample("09").nums()).let {
        assertEquals(15, it.part1())
        assertEquals(1_134, it.part2())
    }

    @Test
    fun puzzle() = Day09(lines("09").nums()).let {
        assertEquals(516, it.part1())
        assertEquals(1_023_660, it.part2())
    }
}
