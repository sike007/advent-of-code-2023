
fun main() {
    fun handStrength1(hand: String): Int {
        val cardMap = mutableMapOf<Char, Int>()
        for (c in hand) {
            cardMap[c] = cardMap.getOrDefault(c, 0) + 1
        }
        val max = cardMap.values.max()
        if (max == 5) return 7
        if (max == 4)
            return 6
        if (max == 3) {
            if (cardMap.values.size == 2)
                return 5
            return 4
        }
        val predicate: (Int) -> Boolean = { it == 2 }
        if (cardMap.values.count(predicate) == 2)
            return 3
        if (cardMap.values.count(predicate) == 1)
            return 2
        return 1
    }

    fun cardStrength1(hand: String): String {
        var str = ""
        for (c in hand) {
            val charMap = mapOf('A' to 'e', 'K' to 'd', 'Q' to 'c', 'J' to 'b', 'T' to 'a')
            str += if (c in charMap.keys)
                charMap[c]
            else
                c
        }
        return str
    }

    val comparator = object : Comparator<List<String>> {
        override fun compare(a: List<String>, b: List<String>): Int {
            if (a[2] == b[2]) {
                return a[3].compareTo(b[3])
            }
            return a[2].toInt() - b[2].toInt()

        }
    }

    fun part1(input: List<String>): Long {
        val handList = mutableListOf<List<String>>()
        input.forEach() {
            val args = it.split(' ')
            val hand = listOf(args[0], args[1], handStrength1(args[0]).toString(), cardStrength1(args[0]))
            handList.add(hand)
        }
        handList.sortWith(comparator)
        var ans = 0L
        for (i in handList.indices) {
            ans += handList[i][1].toLong() * (i + 1)
        }
        return ans
    }

    fun handStrength2(hand: String): Int {
        val cardMap = mutableMapOf<Char, Int>()
        for (c in hand) {
            cardMap[c] = cardMap.getOrDefault(c, 0) + 1
        }
        if (hand == "JJ56J"){
            println("hi")
        }
        val jCount = cardMap.getOrDefault('J', 0)
        cardMap.remove('J')
        if (cardMap.isEmpty()) return 7
        val max = cardMap.values.max()
        if (max == 5) return 7
        if (max == 4)
            return 6 + jCount
        if (max == 3) {
            if (cardMap.values.size == 2)
                return 5 + jCount
            if (jCount > 0)
                return 4 + jCount + 1
            return 4
        }
        val predicate: (Int) -> Boolean = { it == 2 }
        if (cardMap.values.count(predicate) == 2)
            return 3 + jCount*2
        if (cardMap.values.count(predicate) == 1){
            if (jCount == 1) return 4
            if (jCount > 1) return 4 + jCount
            return 2
        }
        if (jCount == 1)
            return 2
        if (jCount == 2)
            return 4
        if (jCount >= 3)
            return 3 + jCount
        return 1
    }

    fun cardStrength2(hand: String): String {
        var str = ""
        for (c in hand) {
            val charMap = mapOf('A' to 'e', 'K' to 'd', 'Q' to 'c', 'J' to '1', 'T' to 'a')
            str += if (c in charMap.keys)
                charMap[c]
            else
                c
        }
        return str
    }

    fun part2(input: List<String>): Long {
        val handList = mutableListOf<List<String>>()
        input.forEach() {
            val args = it.split(' ')
            val hand = listOf(args[0], args[1], handStrength2(args[0]).toString(), cardStrength2(args[0]))
            handList.add(hand)
        }
        handList.sortWith(comparator)
        var ans = 0L
        handList.forEach { println(it[0] + ' ' + it[2]) }
        for (i in handList.indices) {
            ans += handList[i][1].toLong() * (i + 1)
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 6440L)
    check(part2(testInput) == 5905L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}