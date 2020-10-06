class ListMaxHeapPriorityQueue {
    private val container: MutableList<Int> = ArrayList()

    private fun siftUp() {
        var currentIndex = container.lastIndex
        var parentIndex = (currentIndex / 2.0).toInt()
        while (currentIndex >= 0 && container[parentIndex] < container[currentIndex]) {
            container[parentIndex] = container[currentIndex].also { container[currentIndex] = container[parentIndex] }
            currentIndex = parentIndex
            parentIndex = (currentIndex / 2.0).toInt()
        }
    }

    private fun siftDown() {
        var currentIndex = 0
        var firstChildNodeIndex = currentIndex * 2 + 1
        var secondChildNodeIndex = currentIndex * 2 + 2

        while (currentIndex <= container.lastIndex
            && (firstChildNodeIndex <= container.lastIndex || secondChildNodeIndex <= container.lastIndex)
            && (container[currentIndex] < container[firstChildNodeIndex]
                    || container[currentIndex] < container[secondChildNodeIndex])
        ) {
            val firstChildNodeValue = container[firstChildNodeIndex]
            val secondChildNodeValue =
                if (secondChildNodeIndex <= container.lastIndex) container[secondChildNodeIndex] else null

            if (firstChildNodeValue > container[currentIndex]) {
                container[currentIndex] =
                    firstChildNodeValue.also { container[firstChildNodeIndex] = container[currentIndex] }
                currentIndex = firstChildNodeIndex
                firstChildNodeIndex = currentIndex * 2 + 1
                secondChildNodeIndex = currentIndex * 2 + 2
            } else if (secondChildNodeValue != null && secondChildNodeValue > container[currentIndex]) {
                container[currentIndex] =
                    secondChildNodeValue.also { container[secondChildNodeIndex] = container[currentIndex] }
                currentIndex = secondChildNodeIndex
                firstChildNodeIndex = currentIndex * 2 + 1
                secondChildNodeIndex = currentIndex * 2 + 2
            }
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
    val pq = ListMaxHeapPriorityQueue()
    pq.add(10)
    pq.add(8)
    pq.add(9)
    pq.add(20)
    pq.add(15)
    pq.add(320)
//    println(pq)

    while (pq.isNotEmpty) {
        println(pq)
        println(pq.poll())
        println(pq)
    }
}