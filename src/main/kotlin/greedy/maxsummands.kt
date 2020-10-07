package greedy

fun findSummands(number: Int): List<Int> {
    val summands: MutableList<Int> = ArrayList()

    var remainingSum = number
    for (summand in 1..number) {
        if (remainingSum - summand >= summand + 1) {
            summands.add(summand)
            remainingSum -= summand
        } else {
            summands.add(remainingSum)
            break
        }
    }

    return summands
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)

    val summands = findSummands(scanner.nextInt())
    println(summands.size)
    println(summands.joinToString(" "))
}