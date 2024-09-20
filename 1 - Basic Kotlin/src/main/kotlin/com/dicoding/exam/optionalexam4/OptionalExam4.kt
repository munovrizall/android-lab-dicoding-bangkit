package com.dicoding.exam.optionalexam4

// TODO
fun getMiddleCharacters(string: String): String {
    // Mengecek apakah string panjangnya 1 - 100
    if (string.length in 1..100) {
        val stringLength = string.length
        val middleCharacterIndex: Int
        var stringArray = string.toCharArray()

        // Mengecek apakah jumlah character ganjil atau bukan
        if (stringLength % 2 == 0) {
            var middleCharArray = arrayOf<Char>()

            // mengambil index tengah
            middleCharacterIndex = (stringLength / 2)

            // mendapatkan char index tengah dan memasukannya ke array
            middleCharArray += stringArray[middleCharacterIndex - 1]
            middleCharArray += stringArray[middleCharacterIndex]

            // menggabungkan semua char di array dan menjadikannya string
            return middleCharArray.joinToString("")
        } else {
            // mengambil index tengah
            middleCharacterIndex = (stringLength / 2)

            // mengembalikan char pada array
            return stringArray[middleCharacterIndex].toString()
        }
    }
    return ""
}
