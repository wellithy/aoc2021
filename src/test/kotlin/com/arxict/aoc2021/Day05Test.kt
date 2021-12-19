package com.arxict.aoc2021

import kotlin.test.*

class Day05Test {
    @Test
    fun sample() = Day05(sample("05")).let {
        assertEquals(5, it.part1())
        assertEquals(12, it.part2())
    }

    @Test
    fun puzzle() = Day05(lines("05")).let {
        assertEquals(5_167, it.part1())
        assertEquals(17_604, it.part2())
    }
}
