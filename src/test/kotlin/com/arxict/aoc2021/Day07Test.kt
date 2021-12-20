package com.arxict.aoc2021

import kotlin.test.*

class Day07Test {
    @Test
    fun sample() = Day07(sample("07").numbers()).let {
        assertEquals(37, it.part1())
        assertEquals(168, it.part2())
    }

    @Test
    fun puzzle() = Day07(lines("07").numbers()).let {
        assertEquals(357_353, it.part1())
        assertEquals(104_822_130, it.part2())
    }
}
