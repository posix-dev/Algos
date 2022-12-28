fun main() {
    val intArray = IntArray(1)
    intArray[0] = 1

    println(plusOne(intArray).map { it.toString() })
}

private fun plusOne(digits: IntArray): IntArray {
    var isLala = false
    val index = if (digits.last() == 9) {
        digits[digits.lastIndex] = 0
        if (digits.lastIndex > 0) {
            digits.lastIndex - 1
        } else {
            isLala = true
            0
        }
    } else {
        digits.lastIndex
    }
    digits[index] = digits[index] + 1


    return if (digits.size == 1 && digits[digits.lastIndex] == 1 && isLala) digits.plus(0) else digits
}