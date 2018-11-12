package com.illuzor.mightypreferences

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BasicTypesTests {

    private val prefs = InstrumentationRegistry.getTargetContext().defaultPrefs

    @Test
    fun strings_test() {
        val str1 = "Hello"
        val str2 = "Helloooo!!!"
        val str3 = "Hello my dear friend!"

        prefs.putString("str1", str1)
        prefs.putString("str2", str2)
        prefs.putString("str3", str3)

        assertEquals(str1, prefs.getString("str1"))
        assertEquals(str2, prefs.getString("str2"))
        assertEquals(str3, prefs.getString("str3"))
    }

    @Test
    fun bytes_test() {
        val byte1: Byte = 7
        val byte2 = Byte.MAX_VALUE
        val byte3 = Byte.MIN_VALUE

        prefs.putByte("b1", byte1)
        prefs.putByte("b2", byte2)
        prefs.putByte("b3", byte3)

        assertEquals(byte1, prefs.getByte("b1"))
        assertEquals(byte2, prefs.getByte("b2"))
        assertEquals(byte3, prefs.getByte("b3"))
    }

    @Test
    fun ints_test() {
        val int1 = 224
        val int2 = Int.MAX_VALUE
        val int3 = Int.MIN_VALUE

        prefs.putInt("int1", int1)
        prefs.putInt("int2", int2)
        prefs.putInt("int3", int3)

        assertEquals(int1, prefs.getInt("int1"))
        assertEquals(int2, prefs.getInt("int2"))
        assertEquals(int3, prefs.getInt("int3"))
    }

    @Test
    fun longs_test() {
        val long1 = 333L
        val long2 = Long.MAX_VALUE
        val long3 = Long.MIN_VALUE

        prefs.putLong("long1", long1)
        prefs.putLong("long2", long2)
        prefs.putLong("long3", long3)

        assertEquals(long1, prefs.getLong("long1"))
        assertEquals(long2, prefs.getLong("long2"))
        assertEquals(long3, prefs.getLong("long3"))
    }

    @Test
    fun floats_test() {
        val float1 = 0.123f
        val float2 = Float.MAX_VALUE
        val float3 = Float.MIN_VALUE

        prefs.putFloat("float1", float1)
        prefs.putFloat("float2", float2)
        prefs.putFloat("float3", float3)

        assertEquals(float1, prefs.getFloat("float1"))
        assertEquals(float2, prefs.getFloat("float2"))
        assertEquals(float3, prefs.getFloat("float3"))
    }

    @Test
    fun doubles_test() {
        val double1 = 0.123456789123
        val double2 = Double.MAX_VALUE
        val double3 = Double.MIN_VALUE

        prefs.putDouble("double1", double1)
        prefs.putDouble("double2", double2)
        prefs.putDouble("double3", double3)

        assertEquals(double1, prefs.getDouble("double1"), 0.0)
        assertEquals(double2, prefs.getDouble("double2"), 0.0)
        assertEquals(double3, prefs.getDouble("double3"), 0.0)
    }
}
