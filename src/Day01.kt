import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val onlyDigits = ArrayList<String>()
        input.forEach {
            onlyDigits.add(
                it.filter { it.isDigit() })
        }
        onlyDigits.forEach { str ->
            sum += ("" + str.first() + str.last()).toInt()
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val alphaDigit = mapOf(
            "one" to "1",
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9'
        )
        val onlyDigits = ArrayList<Int>()
        input.forEach {
            var str = ""
            var j = 0
            for (i in 0..it.length) {
                if (it[i].isDigit()) {
                    str += it[i]
                    j = i + 1
                    break
                }
                var test = it.substring(i, min(i + 2, it.length))
                for (k in 2..min(4,it.length-1-i)){
                    test += it[k+i]
                    if(test in alphaDigit.keys) {
                        str += alphaDigit[test]
                        j = i + k + 1
                        break
                    }
                }
                if(str.length == 1)
                    break
            }
            val newIt = it.substring(j)
            for (i in newIt.length - 1 downTo 0) {
                if (newIt[i].isDigit()) {
                    str += newIt[i]
                    break
                }
                var test = newIt.substring(i, min(i + 2, newIt.length))
                for (k in 2..min(4,newIt.length-1-i)){
                    test += newIt[k+i]
                    if(test in alphaDigit.keys) {
                        str += alphaDigit[test]
                        break
                    }
                }
                if(str.length == 2)
                    break
            }
            if (str.length == 1) str += str[0]
            print("$it  =  $str\n")
            onlyDigits.add(str.toInt())
        }
        return onlyDigits.sum()
    }

//     test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
