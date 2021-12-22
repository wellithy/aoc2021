package com.arxict.aoc2021

import kotlin.test.*

class Day11Test {

    @Test
    @Ignore
    fun small() = Day11(sample("11", "1").nums()).let {
        assertEquals(9, it.sample())
        assertEquals(259, it.part1())
        assertEquals(6, it.part2())
    }

    @Test
    fun sample() = Day11(sample("11", "2").nums()).let {
        assertEquals(1_656, it.part1())
        assertEquals(195, it.part2())
    }

    @Test
    fun puzzle() = Day11(lines("11").nums()).let {
        assertEquals(1_741, it.part1())
        assertEquals(440, it.part2())
    }
}
