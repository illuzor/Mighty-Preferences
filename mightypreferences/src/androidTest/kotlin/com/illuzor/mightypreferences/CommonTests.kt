package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CommonTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun contains_and_not_contains(){
        prefs.clear()

        assertFalse(prefs.contains("s1"))
        assertTrue(prefs.notContains("s1"))
        assertFalse(prefs.contains("s2"))
        assertTrue(prefs.notContains("s2"))
        assertFalse(prefs.contains("s3"))
        assertTrue(prefs.notContains("s3"))

        prefs.putString("s1", "vvv")
        prefs.putInt("s2", 222)
        prefs.putLong("s3", 666)

        assertTrue(prefs.contains("s1"))
        assertFalse(prefs.notContains("s1"))
        assertTrue(prefs.contains("s2"))
        assertFalse(prefs.notContains("s2"))
        assertTrue(prefs.contains("s3"))
        assertFalse(prefs.notContains("s3"))
    }

    @Test
    fun contains_all() {
        prefs.clear()

        assertFalse(prefs.containsAll(listOf("s1", "s2", "s3")))

        prefs.putString("s1", "vvv")
        prefs.putInt("s2", 222)
        prefs.putLong("s3", 666)

        assertTrue(prefs.containsAll(listOf("s1", "s2", "s3")))
        assertFalse(prefs.containsAll(listOf("s1", "s2", "s3", "s4")))
    }

    @Test
    fun remove() {
        prefs.clear()

        prefs.putString("s1", "bbb")
        assertTrue(prefs.contains("s1"))

        prefs.remove("s1")
        assertFalse(prefs.contains("s1"))
    }
}
