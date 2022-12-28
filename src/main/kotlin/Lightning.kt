fun main(args: Array<String>) {
    val firstString = "1"
    val secondString = "1"
    val thirdString = "3"

    require(runCatching { firstString.toInt() }.isSuccess)

    println(getCommonArray(
        firstString.toInt(),
        getArrayFromString(secondString),
        getArrayFromString(thirdString)
    ).fold("") { commonString: String, currentValue: Int ->
        "$commonString $currentValue"
    })
}

fun getArrayFromString(arrayString: String): Array<Int> {
    return arrayString.split(" ", ignoreCase = true).map {
        runCatching { it.toInt() }.getOrElse { 0 }
    }.toTypedArray()
}

private fun getCommonArray(
    length: Int, array1: Array<Int>, array2: Array<Int>
): Array<Int> {
    val commonArray = mutableListOf<Int>()

    for (index in 0 until length) {
        val firstNumber = array1[index]
        val secondNumber = array2[index]

        commonArray.add(firstNumber)
        commonArray.add(secondNumber)
    }

    return commonArray.toTypedArray()
}