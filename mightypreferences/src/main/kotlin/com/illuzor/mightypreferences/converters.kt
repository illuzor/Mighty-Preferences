package com.illuzor.mightypreferences

internal fun <T> arrayToString(array: Array<T>, separator: String = ","): String {
    if (array.isEmpty()) return ""
    return array.joinToString(separator) { it.toString() }
}

internal inline fun <reified T> stringToArray(
    string: String,
    type: String,
    separator: String = ","
): Array<T> {
    if (string.isEmpty()) return emptyArray()
    return string.split(separator).map { typeFromString(it, type) as T }.toTypedArray()
}

internal fun <K, V> mapToString(
    map: Map<K, V>,
    separator1: String = ":",
    separator2: String = ","
): String {
    if (map.isEmpty()) return ""
    return map.asSequence().joinToString(separator2) { (key, value) ->
        "$key$separator1$value"
    }
}

internal inline fun <reified K, reified V> stringToMap(
    string: String,
    keyType: String,
    valueType: String,
    separator1: String = ":",
    separator2: String = ","
): Map<K, V> {
    if (string.isEmpty()) return mapOf()
    return string.split(separator2).map {
        val pair = it.split(separator1)
        typeFromString(pair[0], keyType) as K to typeFromString(pair[1], valueType) as V
    }.toMap()
}

private fun typeFromString(str: String, type: String): Any =
    when (type) {
        "Boolean" -> str.toBoolean()
        "Byte" -> str.toByte()
        "Short" -> str.toShort()
        "Integer" -> str.toInt()
        "Long" -> str.toLong()
        "Float" -> str.toFloat()
        "Double" -> str.toDouble()
        else -> str
    }
