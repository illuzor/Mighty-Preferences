package com.illuzor.mightypreferences

import android.content.Context
import androidx.preference.PreferenceManager

val Context.defaultPrefs
    get() = Prefs(PreferenceManager.getDefaultSharedPreferences(this))
