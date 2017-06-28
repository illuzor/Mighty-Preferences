package com.illuzor.mightypreferences

import android.content.SharedPreferences
import java.util.*

class Prefs(private val prefs: SharedPreferences) {

    companion object {
        var DEFAULT_STRING = "undefined"
        var DEFAULT_BOOL = false
        var DEFAULT_INT = 0
        var DEFAULT_FLOAT = 0f
        var DEFAULT_DOUBLE: Double = 0.0
        var DEFAULT_LONG = 0L
        var DEFAULT_BYTE: Byte = 0x00
    }

    private val M_POSTFIX = "_mapClasses"
    private val C_POSTFIX = "_collectionClasses"
    private var changeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    fun putString(key: String, value: String) = prefs.edit().putString(key, value).apply()
    fun getString(key: String, default: String = DEFAULT_STRING): String = prefs.getString(key, default)

    fun putFloat(key: String, value: Float) = prefs.edit().putFloat(key, value).apply()
    fun getFloat(key: String, default: Float = DEFAULT_FLOAT): Float = prefs.getFloat(key, default)

    fun putDouble(key: String, value: Double) = prefs.edit().putString(key, value.toString()).apply()
    fun getDouble(key: String, default: Double = DEFAULT_DOUBLE): Double = prefs.getString(key, default.toString()).toDouble()

    fun putLong(key: String, value: Long) = prefs.edit().putLong(key, value).apply()
    fun getLong(key: String, default: Long = DEFAULT_LONG): Long = prefs.getLong(key, default)

    fun putByte(key: String, value: Byte) = prefs.edit().putInt(key, value.toInt()).apply()
    fun getByte(key: String, default: Byte = DEFAULT_BYTE): Byte = prefs.getInt(key, default.toInt()).toByte()

    fun putInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()
    fun getInt(key: String, default: Int = DEFAULT_INT): Int = prefs.getInt(key, default)

    fun putBool(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()
    fun getBool(key: String, default: Boolean = DEFAULT_BOOL): Boolean = prefs.getBoolean(key, default)

    fun <T : Any> putCollection(key: String, collection: Collection<T>, separator: String = ",") {
        if (collection.isEmpty()) return

        var string = ""
        collection.forEachIndexed { i, value ->
            if (i > 0) string += separator
            string += value.toString()
        }

        var cClass = collection.javaClass.name
        if (cClass.contains("Arrays$")) cClass = cClass.replace("Arrays$", "")
        val cGeneric = collection.elementAt(0).javaClass.simpleName

        putString(key, string)
        putString(key + C_POSTFIX, "$cClass:$cGeneric")
    }

    @Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun <T : Any> getCollection(key: String, separator: String = ","): java.util.Collection<T> {
        if (notContains(key) || notContains(key + C_POSTFIX)) return LinkedList<T>() as java.util.Collection<T>

        val values = getString(key).split(separator)
        val types = getString(key + C_POSTFIX).split(":")
        val valuesType = types[1]

        val collectionInstance = Class.forName(types[0]).newInstance() as java.util.Collection<T>
        values.forEach { value ->
            collectionInstance.add(toType(value, valuesType) as T)
        }

        return collectionInstance
    }

    fun <K : Any, V : Any> putMap(key: String, map: Map<K, V>, separator1: String = ":", separator2: String = ",") {
        if (map.isEmpty()) return
        var string = ""

        map.forEach { entry ->
            string += "${entry.key}$separator1${entry.value}$separator2"
        }

        val keyClass = map.keys.iterator().next().javaClass.simpleName
        val valueClass = map.values.iterator().next().javaClass.simpleName

        putString(key, string.substring(0, string.length - separator2.length))
        putString(key + M_POSTFIX, "${map.javaClass.name}:$keyClass:$valueClass")
    }

    @Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V> {
        if (notContains(key) || notContains(key + M_POSTFIX)) return mapOf()

        val types = getString(key + M_POSTFIX).split(":")

        val mapInstance = Class.forName(types[0]).newInstance() as java.util.Map<K, V>
        val pairsArray = getString(key).split(separator2)

        pairsArray.forEach { string ->
            val pair = string.split(separator1)
            mapInstance.put(toType(pair[0], types[1]) as K, toType(pair[1], types[2]) as V)
        }

        return mapInstance as Map<K, V>
    }

    private fun toType(str: String, type: String): Any =
            when (type) {
                "Boolean" -> str.toBoolean()
                "Byte" -> str.toByte()
                "Integer" -> str.toInt()
                "Long" -> str.toLong()
                "Float" -> str.toFloat()
                "Double" -> str.toDouble()
                else -> str
            }

    fun contains(key: String) = prefs.contains(key)
    fun notContains(key: String) = !prefs.contains(key)
    fun clear() = prefs.edit().clear().apply()
    fun remove(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        if (contains(key + M_POSTFIX)) editor.remove(key + M_POSTFIX)
        if (contains(key + C_POSTFIX)) editor.remove(key + C_POSTFIX)
        editor.apply()
    }

    fun onChange(listener: (String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(C_POSTFIX))
                listener(key)
        }
        prefs.registerOnSharedPreferenceChangeListener(changeListener)
    }

    fun onChange(listener: (Prefs, String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(C_POSTFIX))
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