package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
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
}
