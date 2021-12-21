package com.arxict.aoc2021

import kotlin.test.*

class Day08Test {

    @Test
    fun sample() = Day08(sample("08")).let {
        assertEquals(26, it.part1())
        assertEquals(61_229, it.part2())
    }

    @Test
    fun puzzle() = Day08(lines("08")).let {
        assertEquals(301, it.part1())
        assertEquals(908_067, it.part2())
    }
}
