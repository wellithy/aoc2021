private fun zeroTwo(name: String, withAim: Boolean = false): Int {
    class Position(var horizontal: Int, var depth: Int, var aim: Int = 0) {
        fun exec(instruction: String, value: Int) = when (instruction) {
            "forward" -> {
                horizontal += value
                depth += aim * value
            }
            "down" -> if (withAim) aim += value else depth += value
            "up" -> if (withAim) aim -= value else depth -= value
            else -> error("Unknown instruction: $instruction")
        }
    }
    return file(name).useLines {
        it.fold(Position(0, 0)) { currentPosition, nextCommand ->
            nextCommand.split(" ").let { (instruction, value) ->
                currentPosition.apply { exec(instruction, value.toInt()) }
            }
        }
    }.let { it.horizontal * it.depth }
}

fun main() {
    check(zeroTwo("02") == 150)
//    println(zeroTwo("input"))
    check(zeroTwo("02", true) == 900)
//    println(zeroTwo("input", true))
}

