package com.dicoding.exam.optionalexam2

// TODO
fun minAndMax(number: Int): Int {
    val stringArray = number.toString()
    val numberArray = stringArray.toList()
    val minNumber = numberArray.min().digitToInt()
    val maxNumber = numberArray.max().digitToInt()

    return minNumber + maxNumber
}
