package com.dicoding.exam.optionalexam3

import java.lang.Exception

// TODO
fun manipulateString(str: String, int: Int): String {
    val numberArray = str.toList()
    var arrayNumberInString = arrayOf<Int>()
    var word = arrayOf<Char>()

    // Mengecek, jika angka maka dimasukkan ke array angka, jika string dimasukkan ke array string
    for (char in numberArray) {
        try {
            arrayNumberInString += char.digitToInt()
        } catch (_: Exception) {
            word += char
        }
    }
    // Menggabungkan semua elemen pada array menjadi string
    val wordMerged = word.joinToString("")

    // Menggabungkan semua elemen pada array menjadi angka
    val stringArrayNumber = arrayNumberInString.joinToString("")

    // Mengecek apakah input str ada yang angka atau tidak, jika tidak maka nilai 1 sebagai default
    val numberToTimes = if (stringArrayNumber.isEmpty()) 1 else stringArrayNumber.toInt()

    // Mengkalikan angka pada input str dan input int
    val timesResult = numberToTimes * int

    return wordMerged + timesResult.toString()
}
