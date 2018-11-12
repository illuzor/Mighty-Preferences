package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArraysTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun equality_test() {
        val array1 = arrayOf("hello", "my", "dear", "friend", "dfgdgdg")
        prefs.putArray("array1", array1)
        assertArrayEquals(array1, prefs.getArray<String>("array1"))

        val array2 = arrayOf(1, 2, 3, 4, 5, 6, 7, 9, 22)
        prefs.putArray("array2", array2)
        assertArrayEquals(array2, prefs.getArray<Int>("array2"))

        val array3 = arrayOf(1f, 2.toFloat(), 4.4f, Float.MAX_VALUE, Float.MIN_VALUE)
        prefs.putArray("array3", array3)
        assertArrayEquals(array3, prefs.getArray<Float>("array3"))

        val array4: Array<Long> = arrayOf(22L, 33L, 45L, 882L, 92422L, Long.MAX_VALUE, Long.MIN_VALUE)
        prefs.putArray("array4", array4)
        assertArrayEquals(array4, prefs.getArray<Long>("array4"))
    }

    @Test
    fun separator_test() {
        val array1 = arrayOf("hello, friend", "let`go,")
        prefs.putArray("array1", array1, "$$")
        assertArrayEquals(array1, prefs.getArray<String>("array1", "$$"))

        val array2 = arrayOf(12.2f, 0.0001f, Float.MAX_VALUE)
        prefs.putArray("array2", array2, "%")
        assertArrayEquals(array2, prefs.getArray<Float>("array2", "%"))
    }

    @Test
    fun empty_test(){
        val emptyArray = arrayOf<String>()
        prefs.putArray("empty1", emptyArray)
        assertArrayEquals(emptyArray, prefs.getArray<String>("empty1"))
    }
}
