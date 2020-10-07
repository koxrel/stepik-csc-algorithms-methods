package divideandconquer

import java.util.*

fun binarySearch(array: Array<Int>, searchElement: Int): Int {
    var lowerBound = 0
    var upperBound = array.lastIndex

    while (lowerBound <= upperBound) {
        val middleIndex = (upperBound - lowerBound) / 2 + lowerBound
        when {
            array[middleIndex] == searchElement -> return middleIndex
            array[middleIndex] > searchElement -> upperBound = middleIndex - 1
            else -> lowerBound = middleIndex + 1
        }
    }

    return -1
}

fun main() {
    val scanner = Scanner(System.`in`)
    val array = Array(scanner.nextInt()) { scanner.nextInt() }
    val elementsForSearch = Array(scanner.nextInt()) { scanner.nextInt() }

    println(elementsForSearch
        .map { binarySearch(array, it) }
        .map { if (it > -1) it + 1 else it }
        .joinToString(" ")
    )
}