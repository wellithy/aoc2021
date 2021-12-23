package com.arxict.aoc2021

import kotlin.test.*

class Day13Test {

    @Test
    fun sample1() = Day13(sample("13")).let {
        assertEquals(17, it.part1())
        val zero = """
            #####
            #...#
            #...#
            #...#
            #####
            .....
            .....
        """.trimIndent()
        assertEquals(zero, it.part2())
    }

    @Test
    fun puzzle() = Day13(lines("13")).let {
        assertEquals(729, it.part1())
        val RGZLBHFP = """
            ###...##..####.#....###..#..#.####.###..
            #..#.#..#....#.#....#..#.#..#.#....#..#.
            #..#.#......#..#....###..####.###..#..#.
            ###..#.##..#...#....#..#.#..#.#....###..
            #.#..#..#.#....#....#..#.#..#.#....#....
            #..#..###.####.####.###..#..#.#....#....
        """.trimIndent()
        assertEquals(RGZLBHFP, it.part2())
    }
}
