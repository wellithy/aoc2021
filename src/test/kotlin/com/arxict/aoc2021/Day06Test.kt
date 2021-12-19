package com.arxict.aoc2021

import kotlin.test.*

class Day06Test {

    @Test
    fun sample() {
        val lines = sample("06").map { it.split(':')[1].numbers() }
        val six = Day06(lines[0])
        val states = six.state().iterator()
        lines.drop(1).forEach {
            assertEquals(it.sorted(), states.next())
        }
        assertEquals(26, six.size(18).intValueExact())
        assertEquals(5_934, six.size(80).intValueExact())
        assertEquals(5_934, six.size(80).intValueExact())
        assertEquals( "26984457539".toBigInteger(), six.size(256))
    }

    @Test
    fun puzzle() = Day06(lines("06").numbers()).let {
        assertEquals(360_610, it.size(80).intValueExact())
        assertEquals("1631629590423".toBigInteger(), it.size(256))
    }
}
