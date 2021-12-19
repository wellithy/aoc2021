package com.arxict.aoc2021

val spaceSeparator = Regex("""\s+""")

fun <T> Pair<List<T>, List<T>>.select(largest: Boolean): List<T> =
    if (largest xor (first.size < second.size)) second else first
