package intro

fun fibonacci(n: Long): Long {
    when (n) {
        0L -> return 0
        1L -> return 1
    }
    var currentFibonacci = 1L
    var previousFibonacci = 0L

    var iteration = 1L

    while (iteration < n) {
        val tempFibonacci = currentFibonacci
        currentFibonacci += previousFibonacci
        previousFibonacci = tempFibonacci

        iteration++
    }

    return currentFibonacci
}

fun lastFibonacciDigit(n: Int): Int {
    when (n) {
        0 -> return 0
        1 -> return 1
    }
    var currentFibonacci = 1
    var previousFibonacci = 0

    repeat(n - 1) {
        val tempFibonacci = currentFibonacci
        currentFibonacci = (currentFibonacci + previousFibonacci) % 10
        previousFibonacci = tempFibonacci
    }

    return currentFibonacci
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    println(fibonacci(scanner.nextLong()) % scanner.nextLong())
}