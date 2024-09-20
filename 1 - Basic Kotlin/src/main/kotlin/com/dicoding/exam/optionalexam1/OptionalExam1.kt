package com.dicoding.exam.optionalexam1

// TODO
fun sumOfBigThree(vararg numbers: Int): Int {
    val numberArray = numbers.toTypedArray()
    val sortedArray = numberArray.sortedDescending()
    val bigThreeNumber = sortedArray.slice(0..2)

    return bigThreeNumber.sum()
}
