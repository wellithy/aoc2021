package com.arxict.aoc2021

val spaceSeparator = Regex("""\s+""")

typealias Cell = Pair<Int, Int>

operator fun <T> List<List<T>>.get(cell: Cell): T? =
    getOrNull(cell.first)?.getOrNull(cell.second)

fun <T> List<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()

fun <T> Pair<List<T>, List<T>>.select(largest: Boolean): List<T> =
    if (largest xor (first.size < second.size)) second else first

fun String.numbers(): List<Int> =
    split(",").map { it.trim().toInt() }

fun List<String>.numbers(): List<Int> =
    first().numbers()

fun <T> Array<T>.rotate() {
    val first = first()
    this.copyInto(this, startIndex = 1)
    this[lastIndex] = first
}
