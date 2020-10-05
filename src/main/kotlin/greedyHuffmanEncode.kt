import java.util.*
import kotlin.collections.HashMap

data class HuffmanItem(val value: String, val priority: Int) : Comparable<HuffmanItem> {
    override fun compareTo(other: HuffmanItem): Int = if (priority != other.priority) priority - other.priority
    else value.compareTo(other.value)
}

fun createPriorityQueue(text: String): Queue<HuffmanItem> {
    val charFrequency =
        text
            .asSequence()
            .groupingBy { it }
            .eachCount()
            .map { HuffmanItem(it.key.toString(), it.value) }
    return PriorityQueue(charFrequency)
}

fun buildHuffmanTree(queue: Queue<HuffmanItem>): Map<String, Array<String>> {
    require(queue.size > 0)
    val tree: MutableMap<String, Array<String>> = HashMap()
    var index = 0

    while (queue.isNotEmpty()) {
        val firstNode = queue.poll()
        val secondNode = queue.poll()

        val nodeName = if (queue.isEmpty()) "root" else "node${++index}"

        val newNode = HuffmanItem(nodeName, firstNode.priority + (secondNode?.priority ?: 0))
        if (queue.isNotEmpty()) queue.add(newNode)

        tree[newNode.value] = arrayOf(firstNode.value, secondNode?.value ?: "")
    }

    return tree
}

fun buildHuffmanTable(tree: Map<String, Array<String>>, rootElement: String = "root"): Map<String, String> {
    val pendingNodes: Queue<String> = PriorityQueue()
    pendingNodes.add(rootElement)
    val codes: MutableMap<String, String> = HashMap()

    while (pendingNodes.isNotEmpty()) {
        val currentNode = pendingNodes.poll()

        val childNodes = tree[currentNode] ?: continue
        for (i in 0..1) {
            val candidateNode = childNodes[i]
            codes[candidateNode] = "${codes[currentNode] ?: ""}$i"

            if (candidateNode.length > 1) {
                pendingNodes.add(candidateNode)
            }
        }
    }

    return codes.filterKeys { it.length == 1 }
}

fun encode(text: String, encodingTable: Map<String, String>): String {
    return text
        .map { encodingTable[it.toString()] }
        .joinToString("")
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val text = scanner.next()
    val queue = createPriorityQueue(text)
    val tree = buildHuffmanTree(queue)
    val table = buildHuffmanTable(tree)
    val encodedText = encode(text, table)

    println("${table.keys.size} ${encodedText.length}")
    table.forEach { (t, u) -> println("$t: $u") }
    println(encodedText)
}