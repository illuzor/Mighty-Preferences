[ ![Download](https://api.bintray.com/packages/illuzor/maven/MightyPreferences/images/download.svg) ](https://bintray.com/illuzor/maven/MightyPreferences/_latestVersion)
[![Build Status](https://travis-ci.org/illuzor/Mighty-Preferences.svg?branch=master)](https://travis-ci.org/illuzor/Mighty-Preferences)

# Mighty-Preferences

Kotlin library for android. Wrapper for [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences.html) which provides additional possibilities.

How to use
-----
#### Gradle dependency

```groovy
repositories {
    jcenter()
}

compile 'com.illuzor.mightypreferences:mightypreferences:0.0.6'
```
#### Initialization

```kotlin
// use defaultPrefs
val prefs = defaultPrefs // for context
// or your SharedPreferences
val prefs = Prefs(getPreferences(Context.MODE_PRIVATE))
//...
val prefs = Prefs(PreferenceManager.getDefaultSharedPreferences(this))
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

#### Collections and maps

Lists and Sets:

```kotlin
prefs.putCollection("myList1", arrayListOf("string1", "string2", "string3"))
val myList = prefs.getCollection<String>("myList1")

prefs.putCollection("mySet1", hashSetOf(1,2,3))
val mySet = prefs.getCollection<Int>("mySet1")

// Important: generic type in getCollection<T>() must be the same as in puted collection
```

Maps:

```kotlin
prefs.putMap("myMap1", hashMapOf(Pair("one", 1), Pair("two", 2), Pair("three", 3)))
val myMap = prefs.getMap<String, Int>("myMap1")

// Important: generic types in getMap<K, V>() must be the same as in puted map
```

**Separators for maps and collections.**

putCollection() and getCollection() methods contains default argument **separator** equals to ","
```kotlin
fun <T : Any> putCollection(key: String, list: Collection<T>, separator: String = ",")
fun <T : Any> getCollection(key: String, separator: String = ","): Collection<T>
```
If strings in your collection contains **","** symbol, you need to replace separator to your, which not present in your strings.

For example:

```kotlin
prefs.putCollection("myList1", arrayListOf("one, two", "three, four, five"), separator = "##")
val myList = prefs.getCollection<String>("myList1", separator = "##")
```
The same with maps, but putMap() and getMap() methods contains two separators:

```kotlin
fun <K : Any, V : Any> putMap(key: String, map: Map<K, V>, separator1: String = ":", separator2: String = ",") 
fun <K, V> getMap(key: String, separator1: String = ":", separator2: String = ","): Map<K, V>
```
If strings in your maps contains ":" or/and ",", you need to replace separators to yours, which not present in your strings.

Example:

```kotlin
prefs.putMap("myMap1", hashMapOf(Pair("one", "one, two, three:good"), Pair("two", "four, five:bad")), separator1 = "##", separator2 = "%%")
val myMap = prefs.getMap<String, String>("myMap1", separator1 = "##", separator2 = "%%")
```

#### Default values

You can change default values:

```kotlin
Prefs.DEFAULT_BOOL = false
Prefs.DEFAULT_STRING = "my default string"
Prefs.DEFAULT_INT = 222
Prefs.DEFAULT_BYTE = 0x01
Prefs.DEFAULT_LONG = 188L
Prefs.DEFAULT_FLOAT = 12.2f
Prefs.DEFAULT_DOUBLE = 123.14
```

#### contains, notContains, remove, clear

```kotlin
prefs.contains("key") // returns true if entry exist
prefs.containsAll(arrayOf("param1", "param2", "param3") // returns true if all entries exist
prefs.notContains("key") // returns true if entry not exist
prefs.remove("key") // removes entry
prefs.clear() // removes all entries
```

#### Listener

```kotlin
prefs.onChange { key -> }
//or
prefs.onChange { prefs, key -> }

// simple listener removing
prefs.removeListener()
```

#### Caution

Migthy Preferences provides simple way to store maps and collections, but it based on SharedPreferences. Do not use it for store huge maps and collections - it may be slow. For big massives of data database or orm will be much better.
