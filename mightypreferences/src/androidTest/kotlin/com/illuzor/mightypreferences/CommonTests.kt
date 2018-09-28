package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommonTests {

    private val prefs: Prefs by lazy { InstrumentationRegistry.getTargetContext().defaultPrefs }

    @Test
    fun addRemoveClear() {
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
    fun bools() {
        prefs.putBool("b1", true)
        prefs.putBool("b2", false)
        assertTrue(prefs.getBool("b1"))
        assertFalse(prefs.getBool("b2"))
    }

    @Test
    fun defaults() {
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

    @Test
    fun listeners() {
        prefs.clear()

        prefs.onChange { key ->
            assertEquals("i", key)
        }
        prefs.putInt("i", 1)
        prefs.removeListener()

        prefs.onChange { key ->
            assertEquals("map", key)
        }
        prefs.putMap("map", mapOf(Pair(1, 2)))
        prefs.removeListener()

        prefs.onChange { p, key ->
            assertEquals(p, prefs)
            assertEquals(key, "s")
        }
        prefs.putString("s", "str")
        prefs.removeListener()
    }

}