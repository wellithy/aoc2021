package com.arxict.aoc2021

val spaceSeparator = Regex("""\s+""")

typealias Point = Pair<Int, Int>

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
