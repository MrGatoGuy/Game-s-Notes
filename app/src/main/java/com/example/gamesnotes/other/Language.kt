package com.example.gamesnotes.other

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

object Language {
    lateinit var pref: SharedPreferences
    fun initLanguage(res: Resources){
        val l = pref.getString("l","en")
        val c = res.configuration
        when(l){
            "ru" -> {
                val loc = Locale(l)
                Locale.setDefault(loc)
                c.setLocale(loc)
                res.updateConfiguration(c, res.displayMetrics)
            }
            "en" -> {
                val loc = Locale(l)
                Locale.setDefault(loc)
                c.setLocale(loc)
                res.updateConfiguration(c, res.displayMetrics)
            }

        }
    }
    fun putLanguage(i: String){
        val editor = pref.edit()
        editor.putString("l",i)
        editor.apply()

    }
}