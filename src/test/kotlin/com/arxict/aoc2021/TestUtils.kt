package com.arxict.aoc2021

import java.io.File

val dataDir: File =
    File(File("src", "test"), "data")

fun lines(name: String): List<String> =
    File(dataDir, "Day${name}.txt").readLines()

fun sample(name: String): List<String> =
    lines("${name}_sample")

