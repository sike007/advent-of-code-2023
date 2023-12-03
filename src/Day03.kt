import kotlin.math.min
import kotlin.math.max

fun main() {
    var i = 0
    var j = 0
    val gearMap = HashMap<Pair<Int, Int>, ArrayList<Int>>()

    fun isPartNumber(inp: List<String>): Int {
        var starLocation = Pair(-1, -1)
        var value = ""
        var bool = false

        fun isAdjacent(): Boolean {
            for (k in max(0, i - 1)..min(i + 1, inp.size - 1)) {
                for (l in max(0, j - 1)..min(j + 1, inp[i].length - 1)) {
                    if (!inp[k][l].isDigit() and (inp[k][l] != '.')) {
                        if (inp[k][l] == '*') {
                            starLocation = Pair(k, l)
                        }
                        return true
                    }
                }
            }
            return false
        }

        while (j < inp[i].length) {
            if (!inp[i][j].isDigit())
                break
            value += inp[i][j]
            if (bool) {
                j++
                continue
            }
            bool = isAdjacent()
            j++
        }
        if (bool) {
            if (starLocation != Pair(-1, -1)) {
                if (gearMap[starLocation] == null)
                    gearMap[starLocation] = ArrayList()
                gearMap[starLocation]?.add(value.toInt())
            }
            return value.toInt()
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        i = 0
        while (i < input.size) {
            j = 0
            while (j < input[i].length) {
                if (input[i][j].isDigit()) {
                    ans += isPartNumber(input)
                } else j++
            }
            i++
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        gearMap.clear()
        var ans = 0
        i = 0
        while (i < input.size) {
            j = 0
            while (j < input[i].length) {
                if (input[i][j].isDigit()) {
                    isPartNumber(input)
                } else j++
            }
            i++
        }
        for (it in gearMap) {
            if (it.value.size == 2) {
                ans += it.value[0] * it.value[1]
            }
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
