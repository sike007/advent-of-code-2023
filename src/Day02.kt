import kotlin.math.max

fun main() {
    fun solve1(i: Int, args: List<String>): Int {
        val ballLimit = mapOf("red" to 12, "green" to 13, "blue" to 14)
        for (k in 0..<args.size / 2) {
            if (ballLimit[args[k * 2 + 1]]!! < args[k * 2].toInt()) {
                return 0
            }
        }
        return i + 1
    }

    fun process(inp: String): List<String> {
        var str = inp
        str = str.split(':')[1]
        str = str
            .replace(";", "")
            .replace(",", "")
            .substring(1)
        return str.split(' ')
    }

    fun part1(input: List<String>): Int {
        var ans = 0
        input.forEachIndexed { i, it ->
            val args = process(it)
            ans += solve1(i, args)
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        input.forEachIndexed { _, it ->
            val args = process(it)
            val map = HashMap<String, Int>()
            map["blue"] = 0
            map["green"] = 0
            map["red"] = 0
            for (k in 0..<args.size / 2) {
                map[args[k * 2 + 1]] = max(map[args[k * 2 + 1]]!!, args[k * 2].toInt())
            }
            ans += map.values.reduce { acc, j -> acc * j }
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day02_test")
//    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    check(part1(input) == 2563)
    check(part2(input) == 70768)
}
