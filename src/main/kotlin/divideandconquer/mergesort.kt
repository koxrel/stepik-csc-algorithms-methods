package divideandconquer

import java.util.*

var numInversions = 0L

fun mergeSortIterative(array: IntArray): IntArray {
    val queue: Queue<IntArray> = ArrayDeque()
    queue.addAll(array.map { intArrayOf(it) })

    while (queue.size > 1) {
        val firstArray = queue.poll()
        val secondArray = queue.poll()

        var firstIndex = 0
        var secondIndex = 0

        val sortedArray = IntArray(firstArray.size + secondArray.size)

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

fun mergeSortRecursive(array: IntArray): IntArray {
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

fun merge(firstArray: IntArray, secondArray: IntArray): IntArray {
    var firstIndex = 0
    var secondIndex = 0

    val sortedArray= IntArray(firstArray.size + secondArray.size)

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
    val array = IntArray(scanner.nextInt()) { scanner.nextInt() }

    mergeSortRecursive(array)
    println(numInversions)
}