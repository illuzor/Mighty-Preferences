package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CommonTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun addRemoveClear_test() {
        prefs.putString("param1", "p1")
        assertTrue(prefs.contains("param1"))

        prefs.remove("param1")
        assertTrue(prefs.notContains("param1"))

        prefs.putString("param1", "p1")
        prefs.putString("param2", "p2")
        assertTrue(prefs.contains("param1"))
        assertTrue(prefs.contains("param2"))
        assertTrue(prefs.containsAll(arrayOf("param1", "param2")))

        prefs.clear()

        assertTrue(prefs.notContains("param1"))
        assertTrue(prefs.notContains("param2"))
    }

    @Test
    fun bools_test() {
        prefs.putBool("b1", true)
        prefs.putBool("b2", false)
        assertTrue(prefs.getBool("b1"))
        assertFalse(prefs.getBool("b2"))
    }

    @Test
    fun defaults_test() {
        prefs.clear()

        val key = "noting"

        assertEquals(prefs.getBool(key), Prefs.DEFAULT_BOOL)
        assertEquals(prefs.getString(key), Prefs.DEFAULT_STRING)
        assertEquals(prefs.getInt(key), Prefs.DEFAULT_INT)
        assertEquals(prefs.getByte(key), Prefs.DEFAULT_BYTE)
        assertEquals(prefs.getLong(key), Prefs.DEFAULT_LONG)
        assertEquals(prefs.getFloat(key), Prefs.DEFAULT_FLOAT)
        assertEquals(prefs.getDouble(key), Prefs.DEFAULT_DOUBLE, 0.0)
    }
}
