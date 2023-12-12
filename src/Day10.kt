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

    fun part(input: List<String>): Int {
        var st = Pair(0, 0)
        for (i in input.indices) {
            st = Pair(i, input[i].indexOf('S'))
            if (st.second != -1) break
        }
        var vis = Array(input.size) { Array(input[0].length) { false } }
        fun solve(posVal: Pair<Int, Int>, prevVal: Pair<Int, Int>, stepsVal: Int): Int {
            var pos = posVal
            var prev = prevVal
            var steps = stepsVal
            vis[prev.first][prev.second] = true
            while (true) {
                var (x, y) = pos
                if (pos == st) return steps / 2
                if (vis[x][y] or (input[x][y] == '.')) return -1
                vis[x][y] = true
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
                if ((x < 0) or (x == input.size) or (y < 0) or (y == input[0].length)) return -1
                prev = pos
                pos = Pair(x, y)
                steps++
            }
        }

        var ans1 = -100
        for ((x, y) in charMap['S']!!) {
            var ans2 = 0
            val x1 = st.first + x
            val y1 = st.second + y
            if ((x1 < 0) or (x1 == input.size) or (y1 < 0) or (y1 == input[0].length)) continue
            vis = Array(input.size) { Array(input[0].length) { false } }
            ans1 = maxOf(ans1, solve(Pair(x1, y1), st, 1))
            if (ans1 <= 0) continue
            val replaceS = "-"      // added manually :)
            val mutableInput = input.toMutableList()
            mutableInput[st.first].replaceRange(st.second,st.second+1,replaceS)
            for (i in mutableInput.indices) {
                for (j in mutableInput[i].indices)
                    if (!vis[i][j])
                        mutableInput[i].replaceRange(j,j+1,".")
            }
            for (str in mutableInput) {
                var insideTop = false
                var insideBottom = false
                for (c in str) {
                    when (c) {
                        '|' -> {
                            insideTop = !insideTop
                            insideBottom = !insideBottom
                        }

                        in listOf('F', '7') -> insideBottom = !insideBottom
                        in listOf('J', 'L') -> insideTop = !insideTop
                    }
                    if (insideTop and insideBottom and (c == '.')) ans2++
                }
            }
            println(ans1)
            return ans2
        }
        return 0
    }
    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day00_test")
//    check(part(testInput) == 10)


    val input = readInput("Day00")
    part(input).println()
}