import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
        val mutableInput = mutableListOf<String>()
        for (line in input){
            mutableInput.add(line)
            if (line.find { it == '#' } == null)
                mutableInput.add(line)
        }
        var k = 0
        while (k<mutableInput[0].length){
            var flag = true
            for (i in 0..<mutableInput.size){
                if (mutableInput[i][k] == '#')
                    flag = false
            }
            if (flag){
                for (i in 0..<mutableInput.size){
                    mutableInput[i] = StringBuilder(mutableInput[i].substring(0,k) + "." + mutableInput[i].substring(k)).toString()
                }
                k++
            }
            k++
        }
        val pos = mutableListOf<Pair<Int,Int>>()
        for (i in mutableInput.indices){
            for (j in mutableInput[i].indices){
                if (mutableInput[i][j] == '#'){
                    pos.add(Pair(i,j))
                }
            }
        }
        var ans = 0L
        for (i in pos.indices) {
            for (j in i + 1..<pos.size)
                ans += abs(pos[i].first - pos[j].first) + abs(pos[i].second - pos[j].second)
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        val rowsToBeAdded = Array(input.size) { 0L }
        val colsToBeAdded = Array(input[0].length) { 0L }
        for (i in input.indices){
            rowsToBeAdded[i] = rowsToBeAdded.getOrElse(i-1) {0L}
            if (input[i].find { it == '#' } == null){
                rowsToBeAdded[i] += 1000000L - 1
            }
        }
        for (k in input[0].indices){
            colsToBeAdded[k] = colsToBeAdded.getOrElse(k-1) {0L}
            var flag = true
            for (element in input){
                if (element[k] == '#')
                    flag = false
            }
            if (flag)
                colsToBeAdded[k] += 1000000L - 1
        }
        val pos = mutableListOf<Pair<Long,Long>>()
        for (i in input.indices){
            for (j in input[i].indices){
                if (input[i][j] == '#'){
                    pos.add(Pair(i+rowsToBeAdded[i],j+colsToBeAdded[j]))
                }
            }
        }
        var ans = 0L
        for (i in pos.indices) {
            for (j in i + 1..<pos.size)
                ans += abs(pos[i].first - pos[j].first) + abs(pos[i].second - pos[j].second)
        }
        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 374L)
//    check(part2(testInput) == 1L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}