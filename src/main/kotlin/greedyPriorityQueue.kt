class ListMaxHeapPriorityQueue {
    private val container: MutableList<Int> = ArrayList()

    private fun siftUp() {
        var currentIndex = container.lastIndex
        var parentIndex = ((currentIndex - 1) / 2.0).toInt()
        while (currentIndex >= 0 && container[parentIndex] < container[currentIndex]) {
            container[parentIndex] = container[currentIndex].also { container[currentIndex] = container[parentIndex] }
            currentIndex = parentIndex
            parentIndex = ((currentIndex - 1) / 2.0).toInt()
        }
    }

    private fun siftDown() {
        var currentIndex = 0

        while (currentIndex >= 0) {
            val firstChildNodeIndex = currentIndex * 2 + 1
            val secondChildNodeIndex = currentIndex * 2 + 2

            val firstChildNodeValue = container.getOrNull(firstChildNodeIndex) ?: Int.MIN_VALUE
            val secondChildNodeValue = container.getOrNull(secondChildNodeIndex) ?: Int.MIN_VALUE

            val targetIndex =
                if (firstChildNodeValue > container[currentIndex] && secondChildNodeValue > container[currentIndex]) {
                    if (firstChildNodeValue - secondChildNodeValue >= 0) firstChildNodeIndex else secondChildNodeIndex
                } else if (firstChildNodeValue > container[currentIndex]) {
                    firstChildNodeIndex
                } else if (secondChildNodeValue > container[currentIndex]) {
                    secondChildNodeIndex
                } else {
                    -1
                }

            if (targetIndex > -1) {
                container[currentIndex] =
                    container[targetIndex].also { container[targetIndex] = container[currentIndex] }
            }

            currentIndex = targetIndex
        }
    }

    fun poll(): Int {
        require(container.lastIndex > -1)
        val returnValue = container[0]
        val newRootValue = container.removeAt(container.lastIndex)

        if (container.isNotEmpty()) {
            container[0] = newRootValue
            siftDown()
        }
        return returnValue
    }

    fun add(el: Int) {
        container.add(el)
        siftUp()
    }

    override fun toString(): String {
        return container.toString()
    }

    val isNotEmpty: Boolean
        get() {
            return container.isNotEmpty()
        }
}

fun main(args: Array<String>) {
    val scanner = java.util.Scanner(System.`in`)
    val queue = ListMaxHeapPriorityQueue()

    repeat(scanner.nextInt()) {
        when (scanner.next()) {
            "Insert" -> queue.add(scanner.nextInt())
            "ExtractMax" -> println(queue.poll())
        }
    }
}