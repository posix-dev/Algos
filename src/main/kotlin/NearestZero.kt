import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val length = 6
    val arr = arrayOf(0, 7, 9, 4, 8, 20)

    val nearestArray = nearestToZeroArray(length, arr)
    nearestArray.forEach {
        println(it)
    }
}

fun nearestToZeroArray(length: Int, arr: Array<Int>): Array<Int> {
    val zeroArrPositions = mutableListOf<Int>()

    arr.forEachIndexed { index, value -> if (value == 0) zeroArrPositions.add(index) }

    val nearestArr = findNearestPoint(arr, zeroArrPositions)
    return nearestArr.toTypedArray()
}

fun findNearestPoint(arr: Array<Int>, zeroArrPositions: List<Int>): MutableList<Int> {
    val list = mutableListOf<Int>()

    arr.forEach { index ->
        var nearestIndex = Int.MAX_VALUE

        zeroArrPositions.forEach { zeroIndex ->
            if (index != zeroIndex) {
                if (nearestIndex > (index - zeroIndex).absoluteValue) {
                    nearestIndex = (index - zeroIndex).absoluteValue
                }
            } else {
                nearestIndex = 0
            }
        }

        list.add(index, nearestIndex)
    }

    return list
}
