import java.lang.StringBuilder

fun main() {
    val charMap = mapOf(
        'S' to listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1)),
        '|' to listOf(Pair(-1, 0), Pair(1, 0)),
        '-' to listOf(Pair(0, -1), Pair(0, 1)),
        'L' to listOf(Pair(-1, 0), Pair(0, 1)),
        'J' to listOf(Pair(-1, 0), Pair(0, -1)),
        '7' to listOf(Pair(0, -1), Pair(1, 0)),
        'F' to listOf(Pair(0, 1), Pair(1, 0))
    )
    fun part1(input: List<String>): Int {
        var st = Pair(0, 0)
        for (i in input.indices) {
            st = Pair(i,input[i].indexOf('S'))
            if (st.second != -1)
                break
        }
        var vis = Array(input.size) { Array(input[0].length) { 0 } }
        fun solve(posVal: Pair<Int, Int>, prevVal: Pair<Int, Int>, stepsVal: Int): Int {
            var pos = posVal
            var prev = prevVal
            var steps = stepsVal
            vis[prev.first][prev.second] = 1
            while (true) {
                var (x, y) = pos
                if (pos == st)
                    return steps / 2
                if (vis[x][y] == 1)
                    return -1
                vis[x][y] = 1
                if (input[x][y] == '.')
                    return -1
                val (x1, y1) = charMap[input[x][y]]!![0]
                val (x2, y2) = charMap[input[x][y]]!![1]
                when (prev) {
                    Pair(x + x1, y + y1) -> {
                        x += x2
                        y += y2
                    }
                    Pair(x + x2, y + y2) -> {
                        x += x1
                        y += y1
                    }
                    else -> return -1
                }
                if ((x < 0) or (x == input.size) or (y < 0) or (y == input[0].length))
                    return -1
                prev = pos
                pos = Pair(x,y)
                steps++
            }
        }

        var ans = -100
        for ((x,y) in charMap['S']!!){
            val x1 = st.first+x
            val y1 = st.second+y
            if ((x1 < 0) or (x1 == input.size) or (y1 < 0) or (y1 == input[0].length))
                continue
            vis = Array(input.size) {Array(input[0].length) {0} }
            ans = maxOf(ans, solve(Pair(x1,y1), st, 1))
        }
        return ans
    }
    fun part2(input: List<String>): Int {
        var st = Pair(0, 0)
        for (i in input.indices) {
            st = Pair(i,input[i].indexOf('S'))
            if (st.second != -1)
                break
        }
        var vis = Array(input.size) {Array(input[0].length) {0} }
        fun solve(posVal: Pair<Int, Int>, prevVal: Pair<Int, Int>): Int {
            var pos = posVal
            var prev = prevVal
            vis[prev.first][prev.second] = 1
            while (true) {
                var (x, y) = pos
                val replaceS = '-'      // added manually :)
                if (pos == st) {
                    var res = 0
                    val inputt = input.toMutableList()
                    for (i in inputt.indices)
                        for (j in inputt[i].indices){
                            if (inputt[i][j] == 'S') {
                                val sb = StringBuilder(inputt[i])
                                sb.setCharAt(j, replaceS)
                                inputt[i] = sb.toString()
                            }
                            if (vis[i][j] == 0){
                                val sb = StringBuilder(inputt[i])
                                sb.setCharAt(j,'.')
                                inputt[i] = sb.toString()
                            }
                        }
                    for (str in inputt){
                        var insideTop = false
                        var insideBottom = false
                        for (c in str){
                            when (c) {
                                '|' -> {
                                    insideTop = !insideTop
                                    insideBottom = !insideBottom
                                }
                                in listOf('F','7') -> insideBottom = !insideBottom
                                in listOf('J','L') -> insideTop = !insideTop
                            }
                            if (insideTop and insideBottom and (c == '.'))
                                res++
                        }
                    }
                    return res
                }
                if (vis[x][y] == 1)
                    return -1
                vis[x][y] = 1
                if (input[x][y] == '.')
                    return -1
                val (x1, y1) = charMap[input[x][y]]!![0]
                val (x2, y2) = charMap[input[x][y]]!![1]
                when (prev) {
                    Pair(x + x1, y + y1) -> {
                        x += x2
                        y += y2
                    }
                    Pair(x + x2, y + y2) -> {
                        x += x1
                        y += y1
                    }
                    else -> return -1
                }
                if ((x < 0) or (x == input.size) or (y < 0) or (y == input[0].length))
                    return -1
                prev = pos
                pos = Pair(x,y)
            }
        }

        var ans = -100
        for ((x,y) in charMap['S']!!){
            val x1 = st.first+x
            val y1 = st.second+y
            if ((x1 < 0) or (x1 == input.size) or (y1 < 0) or (y1 == input[0].length))
                continue
            vis = Array(input.size) {Array(input[0].length) {0} }
            ans = maxOf(ans, solve(Pair(x1,y1), st))
        }

        return ans
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 80)
    check(part2(testInput) == 10)


    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}