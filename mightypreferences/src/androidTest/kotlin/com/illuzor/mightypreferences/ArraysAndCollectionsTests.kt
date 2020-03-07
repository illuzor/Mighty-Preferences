package com.illuzor.mightypreferences

import androidx.test.InstrumentationRegistry
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class ArraysAndCollectionsTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun arrays_equality() {
        val array1 = arrayOf("hello", "my", "dear", "friend", "dfgdgdg")
        prefs.putArray("array1", array1)
        Assert.assertArrayEquals(array1, prefs.getArray<String>("array1"))

        val array2 = arrayOf(1, 2, 3, 4, 5, 6, 7, 9, 22)
        prefs.putArray("array2", array2)
        Assert.assertArrayEquals(array2, prefs.getArray<Int>("array2"))

        val array3 = arrayOf(1f, 2.toFloat(), 4.4f, Float.MAX_VALUE, Float.MIN_VALUE)
        prefs.putArray("array3", array3)
        Assert.assertArrayEquals(array3, prefs.getArray<Float>("array3"))

        val array4: Array<Long> = arrayOf(
            22L, 33L, 45L, 882L, 92422L, Long.MAX_VALUE, Long.MIN_VALUE
        )
        prefs.putArray("array4", array4)
        Assert.assertArrayEquals(array4, prefs.getArray<Long>("array4"))
    }

    @Test
    fun arrays_separator() {
        val array1 = arrayOf("hello, friend", "let`go,")
        prefs.putArray("array1", array1, "$$")
        Assert.assertArrayEquals(array1, prefs.getArray<String>("array1", "$$"))

        val array2 = arrayOf(12.2f, 0.0001f, Float.MAX_VALUE)
        prefs.putArray("array2", array2, "%")
        Assert.assertArrayEquals(array2, prefs.getArray<Float>("array2", "%"))
    }

    @Test
    fun arrays_empty() {
        val emptyArray = arrayOf<String>()
        prefs.putArray("empty1", emptyArray)
        Assert.assertArrayEquals(emptyArray, prefs.getArray<String>("empty1"))
    }

    @Test
    fun lists_equality() {
        val list1 = listOf("hello", "my", "dear", "friend", "dfgdgdg")
        prefs.putList("array1", list1)
        assertEquals(list1, prefs.getList<String>("array1"))

        val list2 = listOf(1, 2, 3, 4, 5, 6, 7, 9, 22)
        prefs.putList("array2", list2)
        assertEquals(list2, prefs.getList<Int>("array2"))

        val list3 = listOf(1f, 2.toFloat(), 4.4f, Float.MAX_VALUE, Float.MIN_VALUE)
        prefs.putList("array3", list3)
        assertEquals(list3, prefs.getList<Float>("array3"))

        val list4: List<Long> = listOf(
            22L, 33L, 45L, 882L, 92422L, Long.MAX_VALUE, Long.MIN_VALUE
        )
        prefs.putList("array4", list4)
        assertEquals(list4, prefs.getList<Long>("array4"))
    }

    @Test
    fun lists_separator() {
        val list1 = listOf("hello, friend", "let`go,")
        prefs.putList("array1", list1, "$$")
        assertEquals(list1, prefs.getList<String>("array1", "$$"))

        val list2 = listOf(12.2f, 0.0001f, Float.MAX_VALUE)
        prefs.putList("array2", list2, "%")
        assertEquals(list2, prefs.getList<Float>("array2", "%"))
    }

    @Test
    fun lists_empty() {
        val emptyList = emptyList<String>()
        prefs.putList("empty1", emptyList)
        assertEquals(emptyList, prefs.getList<String>("empty1"))
    }

    @Test
    fun sets_equality() {
        val set1 = setOf("hello", "my", "dear", "friend", "dfgdgdg")
        prefs.putSet("array1", set1)
        assertEquals(set1, prefs.getSet<String>("array1"))

        val set2 = setOf(1, 2, 3, 4, 5, 6, 7, 9, 22)
        prefs.putSet("array2", set2)
        assertEquals(set2, prefs.getSet<Int>("array2"))

        val set3 = setOf(1f, 2.toFloat(), 4.4f, Float.MAX_VALUE, Float.MIN_VALUE)
        prefs.putSet("array3", set3)
        assertEquals(set3, prefs.getSet<Float>("array3"))

        val set4: Set<Long> = setOf(
            22L, 33L, 45L, 882L, 92422L, Long.MAX_VALUE, Long.MIN_VALUE
        )
        prefs.putSet("array4", set4)
        assertEquals(set4, prefs.getSet<Long>("array4"))
    }

    @Test
    fun sets_separator() {
        val set1 = setOf("hello, friend", "let`go,")
        prefs.putSet("array1", set1, "$$")
        assertEquals(set1, prefs.getSet<String>("array1", "$$"))

        val set2 = setOf(12.2f, 0.0001f, Float.MAX_VALUE)
        prefs.putSet("array2", set2, "%")
        assertEquals(set2, prefs.getSet<Float>("array2", "%"))
    }

    @Test
    fun sets_empty() {
        val emptySet = emptySet<String>()
        prefs.putSet("empty1", emptySet)
        assertEquals(emptySet, prefs.getSet<String>("empty1"))
    }
}
