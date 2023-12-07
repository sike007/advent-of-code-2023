import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Long {
        val seeds = input[0]
            .split(':')[1]
            .substring(1)
            .split(' ')
            .map { it.toLong() }.toMutableList()
        var i = 2
        while (i < input.size) {
            if (input[i].length > 0) {
                if (input[i][input[i].length - 1] == ':') {
                    i += 1
                    val ranges: MutableList<List<Long>> = mutableListOf()
                    while (input[i] != "") {
                        val range = input[i].split(' ').map { it.toLong() }
                        ranges.add(range)
                        i++
                        if (i == input.size)
                            break
                    }
                    for (j in seeds.indices) {
                        for (range in ranges) {
                            if (seeds[j] in range[1]..<(range[1] + range[2])) {
                                seeds[j] += range[0] - range[1]
                                break
                            }
                        }
                    }
                }
            }
            i += 1
        }
        return seeds.min()
    }

    fun part2(input: List<String>): Long {
        val seedList = input[0]
            .split(':')[1]
            .substring(1)
            .split(' ')
            .map { it.toLong() }.toMutableList()
        for (j in seedList.indices) {
            if (j % 2 == 1) {
                seedList[j] += seedList[j - 1] - 1
            }
        }
        var i = 2
        while (i < input.size) {
            if (input[i].isNotEmpty()) {
                if (input[i][input[i].length - 1] == ':') {
                    i += 1
                    val ranges: MutableList<MutableList<Long>> = mutableListOf()
                    while (input[i] != "") {
                        val range = input[i].split(' ').map { it.toLong() }.toMutableList()
                        range[2] += range[1] - 1
                        ranges.add(range)
                        i++
                        if (i == input.size)
                            break
                    }
                    ranges.sortBy { it[1] }
                    seedList.sort()
                    val moreSeeds: MutableList<Long> = mutableListOf()
                    var j = 0
                    var k = 0
                    while (k < seedList.size) {
                        val left = seedList[k]
                        val right = seedList[k + 1]
                        while (j < ranges.size) {
                            if (right < ranges[j][1])
                                break
                            else if (left > ranges[j][2]) {
                                j++
                                continue
                            }
                            val transform = ranges[j][0] - ranges[j][1]
                            seedList[k] = max(left, ranges[j][1]) + transform
                            if (ranges[j][1] > left) {
                                moreSeeds.add(ranges[j][1] - 1)
                                moreSeeds.add(left)
                            }
                            seedList[k + 1] = min(right, ranges[j][2]) + transform
                            if (ranges[j][2] < right) {
                                seedList.add(k + 2, right)
                                seedList.add(k + 2, ranges[j][2] + 1)

                            }
                            break
                        }
                        k += 2
                    }
                    seedList.addAll(moreSeeds)
                }
            }
            i += 1
        }
        return seedList.min()

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 35L)
    check(part2(testInput) == 46L)

    val input = readInput("Day00")
    part1(input).println()
    part2(input).println()
}