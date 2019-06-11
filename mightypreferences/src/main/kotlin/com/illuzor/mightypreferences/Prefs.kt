package com.illuzor.mightypreferences

import android.content.SharedPreferences

@Suppress("NOTHING_TO_INLINE")
class Prefs(private val prefs: SharedPreferences) {

    companion object {
        var DEFAULT_STRING = "undefined"
        var DEFAULT_BOOL = false
        var DEFAULT_INT = 0
        var DEFAULT_FLOAT = 0f
        var DEFAULT_DOUBLE = 0.0
        var DEFAULT_LONG = 0L
        var DEFAULT_BYTE: Byte = 0x00
        private val M_POSTFIX = "_mapClasses"
        private val A_POSTFIX = "_arrayClasses"
    }

    private var changeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    fun putString(key: String, value: String) = prefs.edit().putString(key, value).apply()
    fun getString(key: String, default: String = DEFAULT_STRING) = prefs.getString(key, default)!!

    fun putFloat(key: String, value: Float) = prefs.edit().putFloat(key, value).apply()
    fun getFloat(key: String, default: Float = DEFAULT_FLOAT) = prefs.getFloat(key, default)

    fun putDouble(key: String, value: Double) = prefs.edit()
        .putString(key, value.toString())
        .apply()

    fun getDouble(key: String, default: Double = DEFAULT_DOUBLE) =
        prefs.getString(key, default.toString())!!.toDouble()

    fun putLong(key: String, value: Long) = prefs.edit().putLong(key, value).apply()
    fun getLong(key: String, default: Long = DEFAULT_LONG) = prefs.getLong(key, default)

    fun putByte(key: String, value: Byte) = prefs.edit().putInt(key, value.toInt()).apply()
    fun getByte(key: String, default: Byte = DEFAULT_BYTE) =
        prefs.getInt(key, default.toInt()).toByte()

    fun putInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()
    fun getInt(key: String, default: Int = DEFAULT_INT) = prefs.getInt(key, default)

    fun putBool(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()
    fun getBool(key: String, default: Boolean = DEFAULT_BOOL) = prefs.getBoolean(key, default)

    fun <K : Any, V : Any> putMap(
        key: String,
        map: Map<K, V>,
        separator1: String = ":",
        separator2: String = ","
    ) {
        if (map.isEmpty()) return
        val keyClass = map.keys.iterator().next().javaClass.simpleName
        val valueClass = map.values.iterator().next().javaClass.simpleName

        prefs.edit().apply {
            putString(key, mapToString(map, separator1, separator2))
            putString(key + M_POSTFIX, "$keyClass:$valueClass")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V> {
        if (notContains(key) || notContains(key + M_POSTFIX)) return emptyMap()
        val types = getString(key + M_POSTFIX)
        val keyType = types.substringBeforeLast(":").substringAfter(":")
        val valueType = types.substringAfterLast(":")

        return stringToMap<Any, Any>(
            getString(key),
            keyType,
            valueType,
            separator1,
            separator2
        ) as Map<K, V>
    }

    fun <T : Any> putArray(key: String, array: Array<T>, separator: String = ",") {
        if (array.isEmpty()) return
        val cGeneric = array.elementAt(0).javaClass.simpleName
        prefs.edit().apply {
            putString(key, arrayToString(array, separator))
            putString(key + A_POSTFIX, cGeneric)
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getArray(key: String, separator: String = ","): Array<T> {
        if (notContains(key) || notContains(key + A_POSTFIX))
            return emptyArray<Any>() as Array<T>
        val type = getString(key + A_POSTFIX)
        return stringToArray<Any>(getString(key), type, separator) as Array<T>
    }

    inline fun <reified T : Any> putList(key: String, list: List<T>, separator: String = ",") =
        putArray(key, list.toTypedArray(), separator)

    inline fun <T> getList(key: String, separator: String = ","): List<T> =
        getArray<T>(key, separator).toList()

    inline fun <reified T : Any> putSet(key: String, set: Set<T>, separator: String = ",") =
        putArray(key, set.toTypedArray(), separator)

    inline fun <T> getSet(key: String, separator: String = ","): Set<T> =
        getArray<T>(key, separator).toSet()

    fun contains(key: String) = prefs.contains(key)
    fun containsAll(keys: Array<String>) = keys.all { contains(it) }
    fun notContains(key: String) = !prefs.contains(key)
    fun clear() = prefs.edit().clear().apply()
    fun remove(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        when {
            contains(key + M_POSTFIX) -> editor.remove(key + M_POSTFIX)
            contains(key + A_POSTFIX) -> editor.remove(key + A_POSTFIX)
        }
        editor.apply()
    }

    fun onChange(listener: (String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(A_POSTFIX))
                listener(key)
        }
        prefs.registerOnSharedPreferenceChangeListener(changeListener)
    }

    fun onChange(listener: (Prefs, String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(A_POSTFIX))
                listener(this, key)
        }
        prefs.registerOnSharedPreferenceChangeListener(changeListener)
    }

    fun removeListener() {
        if (changeListener != null) {
            prefs.unregisterOnSharedPreferenceChangeListener(changeListener)
            changeListener = null
        }
    }
}
