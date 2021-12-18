private fun zeroOne(name: String, window: Int): Int = file(name).useLines {
    it.map(String::toInt).windowed(window).count { list -> list.last() > list.first() }
}

fun main() {
    check(zeroOne("01", 2) == 7)
//    println(zeroOne("input", 2))
    check(zeroOne("01", 4) == 5)
//    println(zeroOne("input", 4))
}
