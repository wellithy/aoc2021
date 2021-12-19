package com.arxict.aoc2021

class Day02(val lines: List<String>) {
    fun part1(): Int =
        dive(Part1())

    fun part2(): Int =
        dive(Part2())

    private companion object {
        enum class Instruction {
            forward, down, up
        }

        abstract class Position(var horizontal: Int = 0, var depth: Int = 0) {
            fun exec(command: String): Position = this.apply {
                spaceSeparator.split(command).let { (instruction, value) ->
                    exec(Instruction.valueOf(instruction), value.toInt())
                }
            }

            fun result(): Int =
                horizontal * depth

            abstract fun exec(instruction: Instruction, value: Int)
        }

        class Part1 : Position() {
            override fun exec(instruction: Instruction, value: Int) = when (instruction) {
                Instruction.forward -> horizontal += value
                Instruction.down -> depth += value
                Instruction.up -> depth -= value
            }
        }

        class Part2(var aim: Int = 0) : Position() {
            override fun exec(instruction: Instruction, value: Int) = when (instruction) {
                Instruction.forward -> {
                    horizontal += value
                    depth += aim * value
                }
                Instruction.down -> aim += value
                Instruction.up -> aim -= value
            }
        }
    }

    private fun dive(position: Position): Int =
        lines.fold(position, Position::exec).result()
}
