package com.illuzor.mightypreferences

import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ListenersTest {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun add_listener() {
        prefs.clear()

        var changed = false

        prefs.addListener { _, key ->
            assertEquals("i", key)
            changed = true
        }
        prefs.putInt("i", 1)
        Thread.sleep(100)
        assertTrue(changed)
        prefs.clearListeners()
    }

    @Test
    fun add_and_remove_listener() {
        prefs.clear()

        var changed = false

        val listener = { _: SharedPreferences, _: String ->
            changed = true
        }

        prefs.addListener(listener)
        prefs.removeListener(listener)
        prefs.putInt("i", 1)
        Thread.sleep(100)
        assertFalse(changed)
    }

    @Test
    fun add_multiple_listeners() {
        prefs.clear()

        var changed1 = false
        var changed2 = false
        var changed3 = false

        prefs.addListener { _, key ->
            assertEquals("i", key)
            changed1 = true
        }

        prefs.addListener { _, key ->
            assertEquals("i", key)
            changed2 = true
        }

        prefs.addListener { _, key ->
            assertEquals("i", key)
            changed3 = true
        }

        prefs.putInt("i", 1)
        Thread.sleep(100)
        assertTrue(changed1)
        assertTrue(changed2)
        assertTrue(changed3)
        prefs.clearListeners()
    }

    @Test
    fun remove_multiple_listeners() {
        prefs.clear()

        var changed1 = false
        var changed2 = false
        var changed3 = false

        prefs.addListener { _, _ -> changed1 = true }
        prefs.addListener { _, _ -> changed2 = true }
        prefs.addListener { _, _ -> changed3 = true }

        prefs.clearListeners()
        prefs.putInt("i", 1)
        Thread.sleep(100)
        assertFalse(changed1)
        assertFalse(changed2)
        assertFalse(changed3)
    }
}
