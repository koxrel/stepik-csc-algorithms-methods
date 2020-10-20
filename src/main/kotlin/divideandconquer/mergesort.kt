package divideandconquer

import java.util.*

var numInversions = 0L

fun mergeSortIterative(array: Array<Int>): Array<Int> {
    val queue: Queue<Array<Int>> = ArrayDeque()
    queue.addAll(array.map { arrayOf(it) })

    while (queue.size > 1) {
        val firstArray = queue.poll()
        val secondArray = queue.poll()

        var firstIndex = 0
        var secondIndex = 0

        val sortedArray: Array<Int> = Array(firstArray.size + secondArray.size) { 0 }

        for (idx in sortedArray.indices) {
            when {
                firstIndex >= firstArray.size -> {
                    sortedArray[idx] = secondArray[secondIndex++]
                }
                secondIndex >= secondArray.size -> {
                    sortedArray[idx] = firstArray[firstIndex++]
                }
                else -> {
                    val firstValue = firstArray[firstIndex]
                    val secondValue = secondArray[secondIndex]

                    if (firstValue > secondValue) {
                        sortedArray[idx] = secondValue
                        secondIndex++
                    } else {
                        sortedArray[idx] = firstValue
                        firstIndex++
                    }
                }
            }
        }

        queue.add(sortedArray)
    }

    return queue.poll()
}

fun mergeSortRecursive(array: Array<Int>): Array<Int> {
    return if (array.size > 1) {
        val middleIndex = array.lastIndex / 2
        merge(
            mergeSortRecursive(array.sliceArray(0..middleIndex)),
            mergeSortRecursive(array.sliceArray((middleIndex + 1)..array.lastIndex))
        )
    } else {
        array
    }
}

fun merge(firstArray: Array<Int>, secondArray: Array<Int>): Array<Int> {
    var firstIndex = 0
    var secondIndex = 0

    val sortedArray: Array<Int> = Array(firstArray.size + secondArray.size) { 0 }

    for (idx in sortedArray.indices) {
        when {
            firstIndex >= firstArray.size -> {
                sortedArray[idx] = secondArray[secondIndex++]
            }
            secondIndex >= secondArray.size -> {
                sortedArray[idx] = firstArray[firstIndex++]
            }
            else -> {
                val firstValue = firstArray[firstIndex]
                val secondValue = secondArray[secondIndex]

                if (firstValue > secondValue) {
                    sortedArray[idx] = secondValue
                    secondIndex++
                    numInversions += firstArray.size - firstIndex
                } else {
                    sortedArray[idx] = firstValue
                    firstIndex++
                }
            }
        }
    }

    return sortedArray
}

fun main() {
    val scanner = Scanner(System.`in`)
    val array = Array(scanner.nextInt()) { scanner.nextInt() }

    mergeSortRecursive(array)
    println(numInversions)
}