package com.arxict.aoc2021

import kotlin.test.*

class Day12Test {

    @Test
    fun sample1() = Day12(sample("12", "1")).let {
        assertEquals(10, it.part1())
    }

    @Test
    fun sample2() = Day12(sample("12", "2")).let {
        assertEquals(19, it.part1())
    }

    @Test
    fun sample3() = Day12(sample("12", "3")).let {
        assertEquals(226, it.part1())
    }

    @Test
    fun puzzle() = Day12(lines("12")).let {
        assertEquals(5_874, it.part1())
    }
}
