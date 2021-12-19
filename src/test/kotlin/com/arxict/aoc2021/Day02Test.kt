package com.arxict.aoc2021

import kotlin.test.*

class Day02Test {

    @Test
    fun sample() = Day02(sample("02")).let {
        assertEquals(150, it.part1())
        assertEquals(900, it.part2())
    }

    @Test
    fun puzzle() = Day02(lines("02")).let {
        assertEquals(1_698_735, it.part1())
        assertEquals(1_594_785_890, it.part2())
    }

}
