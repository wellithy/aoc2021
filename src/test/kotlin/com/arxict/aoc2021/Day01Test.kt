package com.arxict.aoc2021

import kotlin.test.*

class Day01Test {

    @Test
    fun sample() = Day01(sample("01")).let {
        assertEquals(5, it.part2())
        assertEquals(7, it.part1())
    }

    @Test
    fun puzzle() = Day01(lines("01")).let {
        assertEquals(1_451, it.part1())
        assertEquals(1_395, it.part2())
    }

}
