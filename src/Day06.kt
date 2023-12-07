fun main() {
    fun part1(input: List<String>): Int {
        val times = input[0]
            .split(':')[1]
                .split(' ')
                .toMutableList()
        times.removeIf { it.isBlank() }
        val distances = input[1]
            .split(':')[1]
            .split(' ')
            .toMutableList()
        distances.removeIf { it.isBlank() }
        var ans = 1
        for (j in times.indices){
            val timeInt = times[j].toInt()
            for (i in (1..(timeInt/2)).reversed()) {
                if ((i*(timeInt-i)) <= distances[j].toInt()){
                    ans *= (timeInt/2-i)*2-((timeInt+1)%2)
                    break
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        val times = input[0]
            .split(':')[1]
            .split(' ')
            .toMutableList()
        times.removeIf { it.isBlank() }
        val distances = input[1]
            .split(':')[1]
            .split(' ')
            .toMutableList()
        distances.removeIf { it.isBlank() }
        var time = ""
        var distance = ""
        times.forEach { time+=it }
        distances.forEach { distance+=it }
        var ans = 1L
        val timeInt = time.toLong()
        for (i in (1..(timeInt/2)).reversed()) {
            if ((i*(timeInt-i)) <= distance.toLong()){
                ans *= (timeInt/2-i)*2-((timeInt+1)%2)
                break
            }
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}