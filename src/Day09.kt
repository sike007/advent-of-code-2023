fun main() {
    fun solve1(arr: List<Long>): Long {
        val subArray = mutableListOf<Long>()
        for (i in 1..<arr.size)
            subArray.add(arr[i]-arr[i-1])
        if (subArray.max() == subArray.min()){
            return subArray.first() + arr.last()
        }

        return arr.last() + solve1(subArray)

    }

    fun part1(input: List<String>): Long {
        var ans = 0L
        for (line in input) {
            val arr = line.split(' ').map { it.toLong() }
            ans += solve1(arr)
        }
        return ans
    }
    fun solve2(arr: List<Long>): Long {
        val subArray = mutableListOf<Long>()
        for (i in 1..<arr.size)
            subArray.add(arr[i]-arr[i-1])

        if (subArray.max() == subArray.min()){
            return arr[0] - subArray[0]
        }

        return arr[0] - solve2(subArray)

    }

    fun part2(input: List<String>): Long {
        var ans = 0L
        for (line in input) {
            val arr = line.split(' ').map { it.toLong() }
            ans += solve2(arr)
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 114L)
    check(part2(testInput) == 2L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}