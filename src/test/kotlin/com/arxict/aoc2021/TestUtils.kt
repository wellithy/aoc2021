package com.arxict.aoc2021

import java.io.File

val dataDir: File =
    File(File("src", "test"), "data")

fun lines(name: String): List<String> =
    File(dataDir, "Day${name}.txt").readLines()

fun sample(name: String, suffix:String=""): List<String> =
    lines("${name}_sample$suffix")

fun List<String>.nums(): List<List<Int>> =
    map { it.map(Char::digitToInt).toList() }

