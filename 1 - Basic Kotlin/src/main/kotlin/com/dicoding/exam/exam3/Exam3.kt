package com.dicoding.exam.exam3

// TODO
fun <T> checkType(args: T): String {
    val datatype = when (args) {
        is String -> "String"
        is Boolean -> "Boolean"
        is Int -> "Integer"
        is Double -> "Double"
        is List<*> -> "List"
        is Map<*, *> -> "Map"
        else -> "Unknown"
    }
    return "Yes! it's $datatype"
}
