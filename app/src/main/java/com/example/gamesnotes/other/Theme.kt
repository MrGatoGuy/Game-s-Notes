package com.example.gamesnotes.other
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate


object Theme {
    lateinit var pref: SharedPreferences
    var nyn = false
    fun initTheme(){
        val theme = pref.getInt("theme",1000)
        when(theme){
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                nyn = false
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                nyn = false
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                nyn = true
            }
        }
    }
    fun putTheme(i: Int){
        val editor = pref.edit()
        editor.putInt("theme",i)
        editor.apply()
    }
    fun putExtraTheme(v: ImageView,v2: View){
        if(nyn){
            v.visibility = View.VISIBLE
            v2.visibility = View.VISIBLE
        }
    }
}