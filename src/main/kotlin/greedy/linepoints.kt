package greedy

fun coverLineSegmentsWithPoints(segments: Array<Array<Int>>): MutableList<Int> {
    val sortedSegments = segments.sortedBy { it[1] }
    var iteration = 0
    var lastPoint: Int

    val points: MutableList<Int> = ArrayList()

    while (iteration < sortedSegments.size) {
        lastPoint = sortedSegments[iteration][1]
        points.add(lastPoint)

        while (iteration < sortedSegments.size && lastPoint >= sortedSegments[iteration][0])
            iteration++
    }

    return points
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val numSegments = scanner.nextInt()
    val segments = Array(numSegments) { arrayOf(scanner.nextInt(), scanner.nextInt()) }

    val points = coverLineSegmentsWithPoints(segments)
    println(points.size)
    println(points.joinToString(" "))
}