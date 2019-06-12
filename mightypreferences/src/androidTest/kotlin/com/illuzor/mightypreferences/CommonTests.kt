package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CommonTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun contains_and_not_contains() {
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

    @Test
    fun put_multiple() {
        prefs.clear()
        prefs.put {
            bool("k1", true)
            bool("k2", false)
            byte("k3", 0x6)
            short("k4", 22)
            int("k5", 222)
            long("k6", 2233L)
            float("k7", 1.2f)
            double("k8", 1.2222)
            string("k9", "hello")
        }
        assertTrue(prefs.containsAll(listOf("k1", "k2", "k3", "k4", "k5", "k6", "k7", "k8", "k9")))
        assertTrue(prefs.getBool("k1"))
        assertFalse(prefs.getBool("k2"))
        assertEquals(0x6.toByte(), prefs.getByte("k3"))
        assertEquals(22.toShort(), prefs.getShort("k4"))
        assertEquals(222, prefs.getInt("k5"))
        assertEquals(2233L, prefs.getLong("k6"))
        assertEquals(1.2f, prefs.getFloat("k7"))
        assertEquals(1.2222, prefs.getDouble("k8"), 0.0001)
    }
}
