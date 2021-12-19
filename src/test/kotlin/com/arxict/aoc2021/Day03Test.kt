package com.arxict.aoc2021

import kotlin.test.*

class Day03Test {
    @Test
    fun sample() = Day03(sample("03")).let {
        assertEquals(198, it.part1())
        assertEquals(230, it.part2())
    }

    @Test
    fun puzzle() = Day03(lines("03")).let {
        assertEquals(2_583_164, it.part1())
        assertEquals(2_784_375, it.part2())
    }

}
