package com.arxict.aoc2021

import kotlin.test.*

class Day10Test {

    @Test
    fun sample() = Day10(sample("10")).let {
        assertEquals(26_397, it.part1())
        assertEquals(288_957, it.part2())
    }

    @Test
    fun puzzle() = Day10(lines("10")).let {
        assertEquals(339_411, it.part1())
        assertEquals(2_289_754_624, it.part2())
    }
}
