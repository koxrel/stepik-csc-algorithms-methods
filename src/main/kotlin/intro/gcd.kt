package intro

/**
 * Calculates the greatest common denominator
 * @author Igor Tresoumov
 * @param[a] The first number
 * @param[b] The second number
 * @return The greates common denominator*/
fun greatestCommonDenominator(a: Long, b: Long): Long {
    return when {
        a == 0L -> return b
        b == 0L -> return a
        a >= b -> greatestCommonDenominator(a % b, b)
        else -> greatestCommonDenominator(a, b % a)
    }
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    println(greatestCommonDenominator(scanner.nextLong(), scanner.nextLong()))
}