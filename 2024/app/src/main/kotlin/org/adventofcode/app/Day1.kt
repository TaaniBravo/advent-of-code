package org.adventofcode.app

import org.adventofcode.app.utils.readLines
import java.util.PriorityQueue

val DELIMITER = "   "

fun getGroupPriorityQueues(lines: List<String>): ArrayList<PriorityQueue<Int>> {
    val groups = ArrayList<PriorityQueue<Int>>()

    for (line in lines) {
        val nums = line.split(DELIMITER)
        for (i in nums.indices) {
            if (groups.size <= i) groups.add(PriorityQueue())
            groups[i].add(nums[i].toInt())
        }
    }

    return groups
}

fun getPair(lines: List<String>): Pair<HashMap<Int, Int>, List<Int>> {
    val map = hashMapOf<Int, Int>()
    val list = ArrayList<Int>()
    for (line in lines) {
        val nums = line.split(DELIMITER)
        val n1 = nums[0].toInt()
        val n2 = nums[1].toInt()

        if (!map.containsKey(n1)) {
            map.set(n1, 0)
        }

        list.add(n2)
    }

    return Pair(map, list)
}

fun day1(filename: String) {
    // first lets get our file in readlines
    val lines = readLines(filename)
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    // now we have our lines we need two heaps for each of the groups
    val groups = getGroupPriorityQueues(lines)
    // Now we need to grab the first item from each heap until
    // they are empty to calculate the diffs. I probably need
    // to check for absolute?
    val group1 = groups[0]
    val group2 = groups[1]

    var total = 0
    while (group1.isNotEmpty()) {
        total += Math.abs(group1.poll() - group2.poll())
    }

    println("day 1 part 1: $total")
}

private fun part2(lines: List<String>) {
    // we probably want a memo because a number that appears more then once
    // on the left side we don't want to iterate over a list again.
    // we need on iteration on the list for each number?
    val (map, list) = getPair(lines)

    for (n in list) {
        if (map.contains(n)) {
            map.set(n, map.get(n)!!.plus(1))
        }
    }

    var total = 0
    map.forEach { (key, value) -> total += key * value }
    println("day 1 part 2: $total")
}
