package com.illuzor.mightypreferences

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
object PrefsHelper {

    private lateinit var prefs: Prefs

    fun init(prefs: Prefs) {
        this.prefs = prefs
    }

    fun putString(key: String, value: String) = prefs.putString(key, value)
    fun getString(key: String, default: String = Prefs.DEFAULT_STRING): String = prefs.getString(key, default)

    fun putFloat(key: String, value: Float) = prefs.putFloat(key, value)
    fun getFloat(key: String, default: Float = Prefs.DEFAULT_FLOAT) = prefs.getFloat(key, default)

    fun putDouble(key: String, value: Double) = prefs.putDouble(key, value)
    fun getDouble(key: String, default: Double = Prefs.DEFAULT_DOUBLE): Double = prefs.getDouble(key, default)

    fun putLong(key: String, value: Long) = prefs.putLong(key, value)
    fun getLong(key: String, default: Long = Prefs.DEFAULT_LONG): Long = prefs.getLong(key, default)

    fun putByte(key: String, value: Byte) = prefs.putByte(key, value)
    fun getByte(key: String, default: Byte = Prefs.DEFAULT_BYTE): Byte = prefs.getByte(key, default)

    fun putInt(key: String, value: Int) = prefs.putInt(key, value)
    fun getInt(key: String, default: Int = Prefs.DEFAULT_INT): Int = prefs.getInt(key, default)

    fun putBool(key: String, value: Boolean) = prefs.putBool(key, value)
    fun getBool(key: String, default: Boolean = Prefs.DEFAULT_BOOL): Boolean = prefs.getBool(key, default)

    fun <T : Any> putCollection(key: String, collection: Collection<T>, separator: String = ",") = prefs.putCollection(key, collection, separator)
    fun <T : Any> getCollection(key: String, separator: String = ","): java.util.Collection<T> = prefs.getCollection(key, separator)

    fun <K : Any, V : Any> putMap(key: String, map: Map<K, V>, separator1: String = ":", separator2: String = ",") = prefs.putMap(key, map, separator1, separator2)
    fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V> = prefs.getMap(key, separator1, separator2)

    fun <T : Any> putArray(key: String, array: Array<T>, separator: String = ",") = prefs.putArray(key, array, separator)
    fun <T : Any> getArray(key: String, separator: String = ","): Array<Any> = prefs.getArray<T>(key, separator)

    fun contains(key: String) = prefs.contains(key)
    fun containsAll(keys: Array<String>) = prefs.containsAll(keys)
    fun notContains(key: String) = !contains(key)
    fun clear() = prefs.clear()
    fun remove(key: String) = prefs.remove(key)

    fun onChange(listener: (String) -> Unit) = prefs.onChange(listener)
    fun onChange(listener: (Prefs, String) -> Unit) = prefs.onChange(listener)
    fun removeListener() = prefs.removeListener()
}