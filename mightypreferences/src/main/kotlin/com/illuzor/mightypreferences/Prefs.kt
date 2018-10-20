package com.illuzor.mightypreferences

import android.content.SharedPreferences
import java.util.LinkedList

@Suppress("PrivatePropertyName")
class Prefs(private val prefs: SharedPreferences) {

    companion object {
        var DEFAULT_STRING = "undefined"
        var DEFAULT_BOOL = false
        var DEFAULT_INT = 0
        var DEFAULT_FLOAT = 0f
        var DEFAULT_DOUBLE = 0.0
        var DEFAULT_LONG = 0L
        var DEFAULT_BYTE: Byte = 0x00
    }

    private val M_POSTFIX = "_mapClasses"
    private val C_POSTFIX = "_collectionClasses"
    private val A_POSTFIX = "_arrayClasses"
    private var changeListener: SharedPreferences.OnSharedPreferenceChangeListener? = null

    fun putString(key: String, value: String) = prefs.edit().putString(key, value).apply()
    fun getString(key: String, default: String = DEFAULT_STRING): String = prefs.getString(key, default)!!

    fun putFloat(key: String, value: Float) = prefs.edit().putFloat(key, value).apply()
    fun getFloat(key: String, default: Float = DEFAULT_FLOAT): Float = prefs.getFloat(key, default)

    fun putDouble(key: String, value: Double) = prefs.edit().putString(key, value.toString()).apply()
    fun getDouble(key: String, default: Double = DEFAULT_DOUBLE): Double = prefs.getString(key, default.toString())!!.toDouble()

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

        val string = buildString {
            collection.forEachIndexed { i, value ->
                if (i > 0) append(separator)
                append(value.toString())
            }
        }

        var cClass = collection.javaClass.name
        if (cClass.contains("Arrays$")) cClass = cClass.replace("Arrays$", "")
        val cGeneric = collection.elementAt(0).javaClass.simpleName

        putString(key, string)
        putString(key + C_POSTFIX, "$cClass:$cGeneric")
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getCollection(key: String, separator: String = ","): Collection<T> {
        if (notContains(key) || notContains(key + C_POSTFIX)) return LinkedList()

        val values = getString(key).split(separator)
        val types = getString(key + C_POSTFIX)
        val collectionType = types.substringBefore(":")
        val valuesType = types.substringAfter(":")

        val collectionInstance = Class.forName(collectionType).newInstance() as MutableCollection<T>
        values.forEach { value ->
            collectionInstance.add(typeFromString(value, valuesType) as T)
        }

        return collectionInstance
    }

    fun <K : Any, V : Any> putMap(key: String, map: Map<K, V>, separator1: String = ":", separator2: String = ",") {
        if (map.isEmpty()) return
        val string = buildString {
            map.forEach { (key, value) ->
                append("$key$separator1$value$separator2")
            }
        }

        val keyClass = map.keys.iterator().next().javaClass.simpleName
        val valueClass = map.values.iterator().next().javaClass.simpleName

        putString(key, string.substring(0, string.length - separator2.length))
        putString(key + M_POSTFIX, "${map.javaClass.name}:$keyClass:$valueClass")
    }

    @Suppress("UNCHECKED_CAST")
    fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V> {
        if (notContains(key) || notContains(key + M_POSTFIX)) return mapOf()

        val types = getString(key + M_POSTFIX)
        val mapType = types.substringBefore(":")
        val keyType = types.substringBeforeLast(":").substringAfter(":")
        val valueType = types.substringAfterLast(":")

        val mapInstance = Class.forName(mapType).newInstance() as MutableMap<K, V>
        val pairsArray = getString(key).split(separator2)

        pairsArray.forEach { string ->
            val pair = string.split(separator1)
            mapInstance[typeFromString(pair[0], keyType) as K] = typeFromString(pair[1], valueType) as V
        }

        return mapInstance
    }

    fun <T : Any> putArray(key: String, array: Array<T>, separator: String = ",") {
        if (array.isEmpty()) return

        val string = buildString {
            array.forEachIndexed { i, value ->
                if (i > 0) append(separator)
                append(value.toString())
            }
        }

        val cGeneric = array.elementAt(0).javaClass.simpleName

        putString(key, string)
        putString(key + A_POSTFIX, cGeneric)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getArray(key: String, separator: String = ","): Array<Any> {
        if (notContains(key) || notContains(key + A_POSTFIX)) return emptyArray()

        val values = getString(key).split(separator)
        val type = getString(key + A_POSTFIX)

        val array: Array<Any> = Array(values.size) { 0 }
        values.forEachIndexed { i, value ->
            array[i] = typeFromString(value, type) as T
        }
        return array
    }

    private fun typeFromString(str: String, type: String): Any =
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
    fun containsAll(keys: Array<String>) = keys.all { contains(it) }
    fun notContains(key: String) = !prefs.contains(key)
    fun clear() = prefs.edit().clear().apply()
    fun remove(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        when {
            contains(key + M_POSTFIX) -> editor.remove(key + M_POSTFIX)
            contains(key + C_POSTFIX) -> editor.remove(key + C_POSTFIX)
            contains(key + A_POSTFIX) -> editor.remove(key + A_POSTFIX)
        }
        editor.apply()
    }

    fun onChange(listener: (String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(C_POSTFIX) && !key.contains(A_POSTFIX))
                listener(key)
        }
        prefs.registerOnSharedPreferenceChangeListener(changeListener)
    }

    fun onChange(listener: (Prefs, String) -> Unit) {
        removeListener()
        changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (!key.contains(M_POSTFIX) && !key.contains(C_POSTFIX) && !key.contains(A_POSTFIX))
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