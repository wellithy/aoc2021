package com.arxict.aoc2021

import kotlin.test.*

class Day04Test {
    @Test
    fun puzzle() = Day04(lines("04")).let {
        assertEquals(22_680, it.part1())
        assertEquals(16_168, it.part2())
    }
}
