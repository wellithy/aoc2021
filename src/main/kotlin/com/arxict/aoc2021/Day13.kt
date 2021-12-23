package com.arxict.aoc2021

class Day13(val lines: List<String>) {

    private companion object {
        data class Fold(val horizontal: Boolean, val amount: Int)
        class Instruction(val paper: Set<Cell>, val corner: Cell, val fold: List<Fold>)

        fun Instruction.display(): String =
            Array(corner.second.inc()) { CharArray(corner.first.inc()) { '.' } }.let { mesh ->
                paper.forEach { mesh[it.second][it.first] = '#' }
                mesh.joinToString("\n") { String(it) }
            }

        val Instruction.dots: Int get() = paper.size

        fun Instruction(lines: List<String>): Instruction {
            val paper = mutableSetOf<Cell>()
            val fold = mutableListOf<Fold>()
            lines.forEach { line ->
                line.split(',').takeIf { it.size == 2 }?.let { paper += Cell(it[0].toInt(), it[1].toInt()) }
                line.split('=').takeIf { it.size == 2 }?.let { fold += Fold(it[0].last() == 'y', it[1].toInt()) }
            }
            val corner = Cell(paper.maxOf(Cell::first), paper.maxOf(Cell::second))
            return Instruction(paper, corner, fold)
        }
                fun Instruction.fold(): Instruction {
                    val (horizontal, amount) = fold.first()
                    val foldedPaper = if (horizontal) paper.filter{it.second != amount}.partition { it.second < amount }.let { (top, bottom) ->
                        top.toMutableSet().apply { addAll(bottom.map { it.first to amount + amount - it.second }) }
                    } else {
                        paper.filter{it.first != amount}.partition { it.first < amount }.let { (left, right) ->
                            left.toMutableSet().apply { addAll(right.map { amount + amount - it.first to it.second }) }
                        }
                    }
                    val foldedCorner = if (horizontal) corner.copy(second = amount.dec())
                    else corner.copy(first = amount.dec())
                    return Instruction(foldedPaper, foldedCorner, fold.drop(1))
                }
    }

    fun part1(): Int = Instruction(lines).fold().dots

    fun part2(): String {
        var ins = Instruction(lines)

        while (ins.fold.isNotEmpty()) {
            ins = ins.fold()
        }
        return ins.display()
    }

}
