[ ![Download](https://api.bintray.com/packages/illuzor/maven/MightyPreferences/images/download.svg) ](https://bintray.com/illuzor/maven/MightyPreferences/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Build Status](https://travis-ci.org/illuzor/Mighty-Preferences.svg?branch=master)](https://travis-ci.org/illuzor/Mighty-Preferences)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

# Mighty-Preferences

Kotlin library for android. Wrapper for [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences.html) which provides additional possibilities.

How to use
-----
#### Gradle dependency

```groovy
repositories {
    jcenter()
}

implementation 'com.illuzor.mightypreferences:mightypreferences:0.0.11:@aar'
```
#### Initialization

```kotlin
// use defaultPrefs
val prefs = defaultPrefs // for context
// or your SharedPreferences
val prefs = Prefs(getPreferences(Context.MODE_PRIVATE))
// or
val prefs = Prefs(PreferenceManager.getDefaultSharedPreferences(this))
// or
val prefs = Prefs(MyCustomSuperMegaSharedPreferences())

```

#### Basic types

```kotlin
// strings
prefs.putString("string1", "myString")
val myString1 = prefs.getString("string1")
//or
val myString2 = prefs.getString("string1", default = "not found")

// bools
prefs.putBool("bool1", true)
val myBool1 = prefs.getBool("bool1")
//or
val myBool2 = prefs.getBool("bool1", default = false)

// bytes
prefs.putByte("byte1", 0x00)
val myByte1 = prefs.getByte("byte1")
// or
val myByte2 = prefs.getByte("byte1", default = 0x01)

// shorts
prefs.putShort("short1", 0)
val myShort1 = prefs.getShort("short1")
// or
val myShort2 = prefs.getShort("short1", default = 12)

// ints
prefs.putInt("int1", 42)
val myInt1 = prefs.getInt("int1")
// or
val myInt2 = prefs.getInt("int1", default = 0)

// longs
prefs.putLong("long1", 42L)
val myLong1 = prefs.getLong("long1")
// or
val myLong2 = prefs.getLong("long1", default = 336L)

// floats
prefs.putFloat("float1", 12.1f)
val myFloat1 = prefs.getFloat("float1")
// or
val myFloat2 = prefs.getFloat("float1", default = 0.0f)

// doubles
prefs.putDouble("double1", 0.123456789123)
val myDouble1 = prefs.getDouble("double1")
// or
val myDouble2 = prefs.getDouble("double1", default = 12.1222222222)
```

#### Arrays, collections and maps

Supported arrays, lists, sets, maps only with basic types

Arrays:

```kotlin
prefs.putArray("myArray1", arrayOf("string1", "string2", "string3"))
val myArray = prefs.getArray<String>("myArray1")

// Important: generic type in getArray<T>() must be the same as in puted array
```

Lists:

```kotlin
prefs.putList("myList1", listOf("string1", "string2", "string3"))
val myList = prefs.getList<String>("myList1")

// Important: generic type in getList<T>() must be the same as in puted list
```

Sets:

```kotlin
prefs.putSet("mySet1", setOf(1,2,3))
val mySet = prefs.getSet<Int>("mySet1")

// Important: generic type in getSet<T>() must be the same as in puted set
```

Maps:

```kotlin
prefs.putMap("myMap1", mapOf("one" to 1, "two" to 2, "three" to 3))
val myMap = prefs.getMap<String, Int>("myMap1")

// Important: generic types in getMap<K, V>() must be the same as in puted map
```

**Separators for arrays, collections and maps**

putArray()/getArray(), putList()/getList() and putSet()/getSet() methods contains default argument **separator** equals to ","
```kotlin
fun <T> putArray(key: String, array: Array<T>, separator: String = ",")
fun <T> getArray(key: String, separator: String = ","): Array<T>
```
If strings in your collection contains **","** symbol, you need to replace separator to your, which not present in your strings.

For example:

```kotlin
prefs.putArray("myArray1", arrayOf("one, two", "three, four, five"), separator = "##")
val myArray = prefs.getArray<String>("myArray1", separator = "##")
```
The same with maps, but putMap() and getMap() methods contains two separators:

```kotlin
fun <K, V> putMap(key: String, map: Map<K, V>, separator1: String = ":", separator2: String = ",") 
fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V>
```
If strings in your maps contains ":" or/and ",", you need to replace separators to yours, which not present in your strings.

Example:

```kotlin
prefs.putMap("myMap1", hashMapOf(Pair("one", "one, two, three:good"), Pair("two", "four, five:bad")), separator1 = "##", separator2 = "%%")
val myMap = prefs.getMap<String, String>("myMap1", separator1 = "##", separator2 = "%%")
```

#### Multiple put DSL
```kotlin
prefs.put {
    bool("myBool1", true)
    bool("myBool2", false)
    byte("myByte", 0x6)
    short("myShort", 22)
    int("myInt", 222)
    long("myLong", 2233L)
    float("myFloat", 1.2f)
    double("myDouble", 1.2222)
    string("myString", "hello")
    map("myMap", mapOf(1 to 2, 2 to 3, 3 to 4))
    array("myArray", arrayOf(1, 2, 3, 4, 5))
    list("myList", listOf(1, 2, 3, 4, 5))
    set("mySet", setOf(1, 2, 3, 4, 5))
}
```
```kotlin
prefs.put {
    repeat(100) { count: Int ->
        int(count.toString(), count)
    }
}
```

#### Default values

You can change default values:

```kotlin
Prefs.DEFAULT_BOOL = false
Prefs.DEFAULT_BYTE = 0x01
Prefs.DEFAULT_SHORT = 42
Prefs.DEFAULT_INT = 222
Prefs.DEFAULT_LONG = 188L
Prefs.DEFAULT_FLOAT = 12.2f
Prefs.DEFAULT_DOUBLE = 123.14
Prefs.DEFAULT_STRING = "my default string"
```

#### contains, notContains, remove, clear

```kotlin
prefs.contains("key") // returns true if entry exist
prefs.containsAll(arrayOf("param1", "param2", "param3") // returns true if all entries exist
prefs.notContains("key") // returns true if entry does not exist
prefs.remove("key") // removes entry
prefs.clear() // removes all entries
```

#### Listener

Add listener

```kotlin
prefs.addListener { prefs:SharedPreferences, key:String ->
   // ..
}
```
Remove listener

```kotlin
val listener = { prefs:SharedPreferences, key:String ->
    // ..
}

prefs.addListener(listener)
prefs.removeListener(listener)
```
Remove all listeners
```kotlin
prefs.clearListeners()
```

#### Caution

Migthy Preferences provides simple way to store maps and collections, but it based on SharedPreferences. Do not use it for store huge maps and collections - it may be slow. For big massives of data use database.
