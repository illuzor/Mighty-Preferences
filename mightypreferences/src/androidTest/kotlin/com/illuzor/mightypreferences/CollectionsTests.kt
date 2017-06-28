package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollectionsTests {

    private val prefs: Prefs by lazy { InstrumentationRegistry.getTargetContext().defaultPrefs }

    @Test
    @Throws(Exception::class)
    fun types() {
        prefs.putCollection("list1", arrayListOf("hello", "my", "dear", "friend", "dfgdgdg"))

        val list1 = prefs.getCollection<String>("list1")
        assertEquals(list1.javaClass.simpleName, "ArrayList")

        prefs.putCollection("list2", listOf(1, 2, 3, Int.MAX_VALUE))

        val list2 = prefs.getCollection<Int>("list1")
        assertEquals(list2.javaClass.simpleName, "ArrayList")

        prefs.putCollection("set1",hashSetOf( 12f, Float.MIN_VALUE, Float.MAX_VALUE))
        val set1 = prefs.getCollection<Float>("set1")
        assertEquals(set1.javaClass.simpleName, "HashSet")

        prefs.putCollection("set2", linkedSetOf(1,2,3,4,5,6))
        val set2 = prefs.getCollection<Int>("set2")
        assertEquals(set2.javaClass.simpleName, "LinkedHashSet")

        assertNotEquals(set1.javaClass.simpleName, set2.javaClass.simpleName)
        assertNotEquals(list1.javaClass.simpleName, set2.javaClass.simpleName)
    }

    @Test
    @Throws(Exception::class)
    fun equality() {

        val list1 = arrayListOf("hello", "my", "dear", "friend", "dfgdgdg")
        prefs.putCollection("list1", list1)

        val list1p = prefs.getCollection<String>("list1")
        assertEquals(list1, list1p)

        val list2 = listOf(1, 2, 4, 5, 6)
        prefs.putCollection("list2", list2)

        val list2p = prefs.getCollection<Int>("list2")
        assertEquals(list2, list2p)

        val list3 = linkedSetOf(1f, 2.toFloat(), 4.4f, Float.MAX_VALUE, Float.MIN_VALUE)
        prefs.putCollection("list3", list3)

        val list3p = prefs.getCollection<Float>("list3")
        assertEquals(list3, list3p)
    }

    @Test
    @Throws(Exception::class)
    fun separator() {
        val list1 = arrayListOf("hello, friend", "let`go,")
        prefs.putCollection("list1", list1, "$$")
        val list1p = prefs.getCollection<String>("list1", "$$")
        assertEquals(list1, list1p)

        val list2 = listOf(12.2f, 0.0001f, Float.MAX_VALUE)
        prefs.putCollection("list2", list2, "%")
        val list2p = prefs.getCollection<Float>("list2", "%")
        assertEquals(list2, list2p)
    }
}