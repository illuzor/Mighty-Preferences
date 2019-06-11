package com.illuzor.mightypreferences

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    @Test
    fun array_to_string_string() {
        val array = arrayOf("hello", "my", "friend")
        val string = "hello,my,friend"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_byte() {
        val array = arrayOf(0x1, 0x2, 0x3, Byte.MAX_VALUE, Byte.MIN_VALUE)
        val string = "1,2,3,${Byte.MAX_VALUE},${Byte.MIN_VALUE}"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_int() {
        val array = arrayOf(1, 2, 3, Int.MAX_VALUE, Int.MIN_VALUE)
        val string = "1,2,3,${Int.MAX_VALUE},${Int.MIN_VALUE}"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_long() {
        val array = arrayOf(1L, 2L, 3L, Long.MAX_VALUE, Long.MIN_VALUE)
        val string = "1,2,3,${Long.MAX_VALUE},${Long.MIN_VALUE}"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_float() {
        val array = arrayOf(1.1f, 2.1f, 3.1f, Float.MAX_VALUE, Float.MIN_VALUE)
        val string = "1.1,2.1,3.1,${Float.MAX_VALUE},${Float.MIN_VALUE}"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_double() {
        val array = arrayOf(1.1, 2.1, 3.1, Double.MAX_VALUE, Double.MIN_VALUE)
        val string = "1.1,2.1,3.1,${Double.MAX_VALUE},${Double.MIN_VALUE}"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_boolean() {
        val array = arrayOf(true, false, true)
        val string = "true,false,true"
        assertEquals(string, arrayToString(array))
    }

    @Test
    fun array_to_string_empty() {
        assertEquals("", arrayToString<String>(emptyArray()))
        assertEquals("", arrayToString<Byte>(emptyArray()))
        assertEquals("", arrayToString<Int>(emptyArray()))
        assertEquals("", arrayToString<Long>(emptyArray()))
        assertEquals("", arrayToString<Float>(emptyArray()))
        assertEquals("", arrayToString<Double>(emptyArray()))
        assertEquals("", arrayToString<Boolean>(emptyArray()))
    }

    @Test
    fun array_to_string_separator() {
        val array1 = arrayOf("hello", "my", "friend")
        val string1 = "hello!!!my!!!friend"
        assertEquals(string1, arrayToString(array1, "!!!"))

        val array2 = arrayOf("hello", "my", "friend")
        val string2 = "hello##my##friend"
        assertEquals(string2, arrayToString(array2, "##"))

        val array3 = arrayOf(1, 2, 3)
        val string3 = "1-2-3"
        assertEquals(string3, arrayToString(array3, "-"))
    }

    @Test
    fun string_to_array_string() {
        val array = arrayOf("hello", "my", "friend")
        val string = "hello,my,friend"
        assertArrayEquals(array, stringToArray(string, "String"))
    }

    @Test
    fun string_to_array_byte() {
        val array = arrayOf(0x1, 0x2, 0x3, Byte.MAX_VALUE, Byte.MIN_VALUE)
        val string = "1,2,3,${Byte.MAX_VALUE},${Byte.MIN_VALUE}"
        assertArrayEquals(array, stringToArray(string, "Byte"))
    }

    @Test
    fun string_to_array_int() {
        val array = arrayOf(1, 2, 3, Int.MAX_VALUE, Int.MIN_VALUE)
        val string = "1,2,3,${Int.MAX_VALUE},${Int.MIN_VALUE}"
        assertArrayEquals(array, stringToArray(string, "Integer"))
    }

    @Test
    fun string_to_array_long() {
        val array = arrayOf(1L, 2L, 3L, Long.MAX_VALUE, Long.MIN_VALUE)
        val string = "1,2,3,${Long.MAX_VALUE},${Long.MIN_VALUE}"
        assertArrayEquals(array, stringToArray(string, "Long"))
    }

    @Test
    fun string_to_array_float() {
        val array = arrayOf(1.1f, 2.1f, 3.1f, Float.MAX_VALUE, Float.MIN_VALUE)
        val string = "1.1,2.1,3.1,${Float.MAX_VALUE},${Float.MIN_VALUE}"
        assertArrayEquals(array, stringToArray(string, "Float"))
    }

    @Test
    fun string_to_array_double() {
        val array = arrayOf(1.1, 2.1, 3.1, Double.MAX_VALUE, Double.MIN_VALUE)
        val string = "1.1,2.1,3.1,${Double.MAX_VALUE},${Double.MIN_VALUE}"
        assertArrayEquals(array, stringToArray(string, "Double"))
    }

    @Test
    fun string_to_array_boolean() {
        val array = arrayOf(true, false, true)
        val string = "true,false,true"
        assertArrayEquals(array, stringToArray(string, "Boolean"))
    }

    @Test
    fun string_to_array_empty() {
        assertArrayEquals(emptyArray(), stringToArray<String>("", "String"))
        assertArrayEquals(emptyArray(), stringToArray<Byte>("", "Byte"))
        assertArrayEquals(emptyArray(), stringToArray<Int>("", "Integer"))
        assertArrayEquals(emptyArray(), stringToArray<Long>("", "Long"))
        assertArrayEquals(emptyArray(), stringToArray<Float>("", "Float"))
        assertArrayEquals(emptyArray(), stringToArray<Double>("", "Double"))
        assertArrayEquals(emptyArray(), stringToArray<Boolean>("", "Boolean"))
    }

    @Test
    fun string_to_array_separator() {
        val array1 = arrayOf("hello", "my", "friend")
        val string1 = "hello!!!my!!!friend"
        assertArrayEquals(array1, stringToArray<String>(string1, "String", "!!!"))

        val array2 = arrayOf("hello", "my", "friend")
        val string2 = "hello##my##friend"
        assertArrayEquals(array2, stringToArray<String>(string2, "String", "##"))

        val array3 = arrayOf(1, 2, 3)
        val string3 = "1-2-3"
        assertArrayEquals(array3, stringToArray<Int>(string3, "Integer", "-"))
    }

    @Test
    fun map_to_string_string() {
        val map = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val string = "k1:v1,k2:v2,k3:v3"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_byte() {
        val map = mapOf(
            "k1" to 0x01,
            "k2" to 0x02,
            "k3" to 0x03,
            "k4" to Byte.MAX_VALUE,
            "k5" to Byte.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Byte.MAX_VALUE},k5:${Byte.MIN_VALUE}"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_int() {
        val map = mapOf(
            "k1" to 1,
            "k2" to 2,
            "k3" to 3,
            "k4" to Int.MAX_VALUE,
            "k5" to Int.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Int.MAX_VALUE},k5:${Int.MIN_VALUE}"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_long() {
        val map = mapOf(
            "k1" to 1L,
            "k2" to 2L,
            "k3" to 3L,
            "k4" to Long.MAX_VALUE,
            "k5" to Long.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Long.MAX_VALUE},k5:${Long.MIN_VALUE}"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_float() {
        val map = mapOf(
            "k1" to 1f,
            "k2" to 2.2f,
            "k3" to 3.4f,
            "k4" to Float.MAX_VALUE,
            "k5" to Float.MIN_VALUE
        )
        val string = "k1:1.0,k2:2.2,k3:3.4,k4:${Float.MAX_VALUE},k5:${Float.MIN_VALUE}"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_double() {
        val map = mapOf(
            "k1" to 1.0,
            "k2" to 2.2,
            "k3" to 3.4,
            "k4" to Double.MAX_VALUE,
            "k5" to Double.MIN_VALUE
        )
        val string = "k1:1.0,k2:2.2,k3:3.4,k4:${Double.MAX_VALUE},k5:${Double.MIN_VALUE}"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_boolean() {
        val map = mapOf("k1" to true, "k2" to false, "k3" to true)
        val string = "k1:true,k2:false,k3:true"
        assertEquals(string, mapToString(map))
    }

    @Test
    fun map_to_string_empty() {
        assertEquals("", mapToString<String, String>(emptyMap()))
        assertEquals("", mapToString<String, Int>(emptyMap()))
        assertEquals("", mapToString<Float, Int>(emptyMap()))
        assertEquals("", mapToString<Float, Boolean>(emptyMap()))
    }

    @Test
    fun map_to_string_separators() {
        val map1 = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val string1 = "k1!!!v1##k2!!!v2##k3!!!v3"
        assertEquals(string1, mapToString(map1, "!!!", "##"))

        val map2 = mapOf(1 to "v1", 2 to "v2", 3 to "v3")
        val string2 = "1-v1_2-v2_3-v3"
        assertEquals(string2, mapToString(map2, "-", "_"))

        val map3 = mapOf(1 to 111, 2 to 222, 3 to 333)
        val string3 = "1++111_-_2++222_-_3++333"
        assertEquals(string3, mapToString(map3, "++", "_-_"))
    }

    @Test
    fun string_to_map_string() {
        val map = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val string = "k1:v1,k2:v2,k3:v3"
        assertEquals(map, stringToMap<String, String>(string, "String", "String"))
    }

    @Test
    fun string_to_map_byte() {
        val map = mapOf<String, Byte>(
            "k1" to 0x01,
            "k2" to 0x02,
            "k3" to 0x03,
            "k4" to Byte.MAX_VALUE,
            "k5" to Byte.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Byte.MAX_VALUE},k5:${Byte.MIN_VALUE}"
        assertEquals(map, stringToMap<String, Byte>(string, "String", "Byte"))
    }

    @Test
    fun string_to_map_int() {
        val map = mapOf(
            "k1" to 1,
            "k2" to 2,
            "k3" to 3,
            "k4" to Int.MAX_VALUE,
            "k5" to Int.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Int.MAX_VALUE},k5:${Int.MIN_VALUE}"
        assertEquals(map, stringToMap<String, Int>(string, "String", "Integer"))
    }

    @Test
    fun string_to_map_long() {
        val map = mapOf(
            "k1" to 1L,
            "k2" to 2L,
            "k3" to 3L,
            "k4" to Long.MAX_VALUE,
            "k5" to Long.MIN_VALUE
        )
        val string = "k1:1,k2:2,k3:3,k4:${Long.MAX_VALUE},k5:${Long.MIN_VALUE}"
        assertEquals(map, stringToMap<String, Long>(string, "String", "Long"))
    }

    @Test
    fun string_to_map_float() {
        val map = mapOf(
            "k1" to 1f,
            "k2" to 2.2f,
            "k3" to 3.4f,
            "k4" to Float.MAX_VALUE,
            "k5" to Float.MIN_VALUE
        )
        val string = "k1:1.0,k2:2.2,k3:3.4,k4:${Float.MAX_VALUE},k5:${Float.MIN_VALUE}"
        assertEquals(map, stringToMap<String, Float>(string, "String", "Float"))
    }

    @Test
    fun string_to_map_double() {
        val map = mapOf(
            "k1" to 1.0,
            "k2" to 2.2,
            "k3" to 3.4,
            "k4" to Double.MAX_VALUE,
            "k5" to Double.MIN_VALUE
        )
        val string = "k1:1.0,k2:2.2,k3:3.4,k4:${Double.MAX_VALUE},k5:${Double.MIN_VALUE}"
        assertEquals(map, stringToMap<String, Double>(string, "String", "Double"))
    }

    @Test
    fun string_to_map_empty() {
        assertEquals(
            emptyMap<String, String>(),
            stringToMap<String, String>("", "String", "String")
        )
        assertEquals(
            emptyMap<String, Int>(),
            stringToMap<String, Int>("", "String", "Integer")
        )
        assertEquals(
            emptyMap<Float, Int>(),
            stringToMap<Float, Int>("", "Float", "Integer")
        )
        assertEquals(
            emptyMap<Float, Boolean>(),
            stringToMap<Float, Boolean>("", "Float", "Boolean")
        )
    }

    @Test
    fun string_to_map_separators() {
        val map1 = mapOf("k1" to "v1", "k2" to "v2", "k3" to "v3")
        val string1 = "k1!!!v1##k2!!!v2##k3!!!v3"
        assertEquals(map1, stringToMap<String, String>(string1, "String", "String", "!!!", "##"))

        val map2 = mapOf(1 to "v1", 2 to "v2", 3 to "v3")
        val string2 = "1-v1_2-v2_3-v3"
        assertEquals(map2, stringToMap<Int, String>(string2, "Integer", "String", "-", "_"))

        val map3 = mapOf(1 to 111, 2 to 222, 3 to 333)
        val string3 = "1++111_-_2++222_-_3++333"
        assertEquals(map3, stringToMap<Int, Int>(string3, "Integer", "Integer", "++", "_-_"))
    }
}
