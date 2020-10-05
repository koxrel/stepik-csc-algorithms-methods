data class Item(val price: Int, val volume: Int)

fun fillBackpack(availableItems: Array<Item>, capacity: Int): Double {
    var currentCapacity = capacity
    var cost = 0.0
    val sortedItems = availableItems.sortedByDescending { it.price / it.volume.toDouble() }

    var index = 0
    while (currentCapacity > 0 && index < sortedItems.size) {
        val currentItem = sortedItems[index]
        if (currentItem.volume < currentCapacity) {
            cost += currentItem.price
            currentCapacity -= currentItem.volume
        } else {
            val ratio = currentCapacity / currentItem.volume.toDouble()
            cost += currentItem.price * ratio
            currentCapacity = 0
        }
        index++
    }
    return cost
}

fun main(args: Array<String>) {
    val scanner = java.util.Scanner(System.`in`)
    val numItems = scanner.nextInt()
    val backpackCapacity = scanner.nextInt()

    val items = Array(numItems) { Item(scanner.nextInt(), scanner.nextInt()) }

    val points = fillBackpack(items, backpackCapacity)
    println(String.format("%.3f", points))
}