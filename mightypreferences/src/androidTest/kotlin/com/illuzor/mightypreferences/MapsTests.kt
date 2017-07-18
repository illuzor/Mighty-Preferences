package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class MapsTests {

    private val prefs: Prefs by lazy { InstrumentationRegistry.getTargetContext().defaultPrefs }

    private class TestCustomMap<K, V> : HashMap<K, V>()

    @Test
    @Throws(Exception::class)
    fun types() {
        val pair = Pair("str1", "str2")

        val hashMap: HashMap<String, String> = hashMapOf(pair)
        prefs.putMap("map1", hashMap)
        assertEquals(prefs.getMap<String, String>("map1").javaClass.name, "java.util.HashMap")

        val linkedHashMap: LinkedHashMap<String, String> = linkedMapOf(pair)
        prefs.putMap("map2", linkedHashMap)
        assertEquals(prefs.getMap<String, String>("map2").javaClass.name, "java.util.LinkedHashMap")

        val treeMap: TreeMap<String, String> = TreeMap(linkedHashMap)
        prefs.putMap("map3", treeMap)
        assertEquals(prefs.getMap<String, String>("map3").javaClass.name, "java.util.TreeMap")

        val customMap1: TestCustomMap<String, String> = TestCustomMap()
        customMap1.put("str1", "str2")
        prefs.putMap("map4", customMap1)
        assertEquals(prefs.getMap<String, String>("map4").javaClass.simpleName, "TestCustomMap")

        val customMap2: TestCustomMap<String, Int> = TestCustomMap()
        customMap2.put("str1", 1)
        prefs.putMap("map5", customMap2)
        assertEquals(prefs.getMap<String, String>("map5").javaClass.simpleName, "TestCustomMap")
    }

    @Test
    @Throws(Exception::class)
    fun equality() {
        val map1 = hashMapOf(Pair("key1", "value1"), Pair("key2", "value2"), Pair("key3", "value3"))
        prefs.putMap("map1", map1)
        val map1p = prefs.getMap<String, String>("map1")
        assertEquals(map1, map1p)

        val map2 = hashMapOf(Pair("key1", 12.2f), Pair("key2", 0.23f))
        prefs.putMap("map2", map2)
        val map2p = prefs.getMap<String, Float>("map2")
        assertEquals(map2, map2p)

        val map3 = linkedMapOf(Pair(1, Double.MAX_VALUE), Pair(7, Double.MIN_VALUE), Pair(12, 12.toDouble()))
        prefs.putMap("map3", map3)
        val map3p = prefs.getMap<Int, Double>("map3")
        assertEquals(map3, map3p)

        val map4 = mapOf(Pair(1.22f, 67778L), Pair(7.toFloat(), Long.MAX_VALUE), Pair(12f, Long.MIN_VALUE))
        prefs.putMap("map4", map4)
        val map4p = prefs.getMap<Float, Long>("map4")
        assertEquals(map4, map4p)
    }

    @Test
    @Throws(Exception::class)
    fun separators() {
        val map1 = mapOf(Pair("Hello, friend", "oh hello"), Pair("Hello, friend2", "oh hello2"))
        prefs.putMap("map1", map1, separator2 = "#")
        val map1p = prefs.getMap<String, String>("map1", separator2 = "#")
        assertEquals(map1, map1p)

        val map2 = linkedMapOf(Pair(12.2f, "hello, fiends!"), Pair(44.1f, "hello: friend1, friend2, friend3"))
        prefs.putMap("map2", map2, separator1 = "##", separator2 = "%%")
        val map2p = prefs.getMap<Float, String>("map2", "##", "%%")

        assertEquals(map2, map2p)

        prefs.putMap("map3", map2, "$$$", "****")

        val map3 = prefs.getMap<Float, String>("map3", "$$$", "****")

        assertEquals(map2, map3)
    }

}