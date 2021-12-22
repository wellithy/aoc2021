package com.arxict.aoc2021

import kotlin.test.*

class Day09Test {
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
