import java.util.*

private fun zeroThreePart1(name: String): Long {
    class Power {
        lateinit var array: IntArray
        fun add(line: String) {
            if (!this::array.isInitialized) {
                array = IntArray(line.length)
                require(line.length < Long.SIZE_BITS)
            }
            line.forEachIndexed { index, bit ->
                if (bit == '1') array[index]++ else array[index]--
            }
        }

        fun value(): Long {
            val len = array.size
            val bitSet = BitSet(len)
            array.forEachIndexed { index, value ->
                if (value > 0) bitSet.set(len - 1 - index)
            }
            val gamma = bitSet.toLongArray()[0]
            bitSet.flip(0, len)
            val epsilon = bitSet.toLongArray()[0]
            return Math.multiplyExact(gamma, epsilon)
        }
    }
    return file(name).useLines {
        it.fold(Power()) { current, line -> current.apply { add(line) } }
    }.value()
}

private fun <T> Pair<List<T>, List<T>>.select(oxygen: Boolean): List<T> =
    if (oxygen xor (first.size < second.size)) second else first

private fun List<String>.select(oxygen: Boolean, index: Int = 0): Long =
    if (size == 1) first().toLong(2)
    else partition { it[index] == '1' }.select(oxygen).select(oxygen, index + 1)

private fun zeroThreePart2(name: String): Long =
    file(name).readLines().let {
        it.select(false) * it.select(true)
    }

fun main() {
    check(zeroThreePart1("03") == 198L)
//    println(zeroThreePart1("input"))
    check(zeroThreePart2("03") == 230L)
//    println(zeroThreePart2("input"))
}

