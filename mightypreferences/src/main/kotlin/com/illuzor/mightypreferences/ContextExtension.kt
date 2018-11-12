package com.illuzor.mightypreferences

import android.content.Context
import android.preference.PreferenceManager

val Context.defaultPrefs
    get() = Prefs(PreferenceManager.getDefaultSharedPreferences(this))
