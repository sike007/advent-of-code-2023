fun main() {
    fun part1(input: List<String>): Int {
        val steps = input[0]
        val map = hashMapOf<String,Pair<String,String>>()
        for (i in 2..<input.size){
            map[input[i].substring(0,3)] = Pair(input[i].substring(7,10),input[i].substring(12,15))
        }
        var curr = "AAA"
        var ans = 0
        var stepIndex = 0
        while (curr != "ZZZ"){
            ans ++
            curr = if (steps[stepIndex] == 'L')
                map[curr]?.first.toString()
            else
                map[curr]?.second.toString()
            stepIndex = (stepIndex+1)%steps.length
        }
        return ans
    }
    fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
    fun part2(input: List<String>): Long {
        val steps = input[0]
        val map = hashMapOf<String,Pair<String,String>>()
        for (i in 2..<input.size){
            map[input[i].substring(0,3)] = Pair(input[i].substring(7,10),input[i].substring(12,15))
        }
        var ans = 1L
        for (start in map.keys){
            if (start[2] != 'A')
                continue
            var stepCount = 0L
            var curr = start
            var stepIndex = 0
            while (curr[2] !='Z'){
                stepCount ++
                curr = if (steps[stepIndex] == 'L')
                    map[curr]?.first.toString()
                else
                    map[curr]?.second.toString()
                stepIndex = (stepIndex+1)%steps.length
            }
            ans = findLCM(ans,stepCount)

        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part2(testInput) == 6L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}