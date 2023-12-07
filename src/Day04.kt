import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        for (inp in input){
            var ans = 0
            val str = inp.split(':')[1]
                .substring(1)
            val winningCards = str
                .split('|')[0]
                    .split(' ')
                    .toMutableSet()
            winningCards.remove("")
            val myCards = str
                .split('|')[1]
                    .split(' ')
                    .toMutableSet()
            myCards.remove("")
            for (card in myCards){
                if (card in winningCards){
                    if (ans == 0)
                        ans = 1
                    else
                        ans *= 2
                }
            }
            sum += ans
        }
        return sum
    }
    fun part2(input: List<String>): Int {
        var sum = 0
        val cardCopy = HashMap<Int,Int>()
        for (i in input.indices){
            cardCopy[i] = 1
        }
        cardCopy[0]=1
        for (i in input.indices){
            if (cardCopy[i] == 0)
                break
            var ans = 0
            val str = input[i]
                .split(':')[1]
                    .substring(1)
            val winningCards = str
                .split('|')[0]
                    .split(' ')
                    .toMutableSet()
            winningCards.remove("")
            val myCards = str
                .split('|')[1]
                    .split(' ')
                    .toMutableSet()
            myCards.remove("")
            for (card in myCards){
                if (card in winningCards){
                    ans += 1
                }
            }
            if (ans != 0) {
                for (j in min(i + 1, input.size - 1)..<min(i + ans + 1, input.size)) {
                    cardCopy[j] = cardCopy[j]!! + cardCopy[i]!!
                }
            }
            sum += cardCopy[i]!!
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}