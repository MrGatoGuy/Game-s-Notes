package com.example.gamesnotes.activity.options

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.ActivityThemeBinding
import com.example.gamesnotes.other.Theme
import com.example.gamesnotes.other.Theme.putExtraTheme

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putExtraTheme(binding.themeView,binding.shadow)
    }

    fun takeTheme(view: View){
        var i = 0
        when(view.id){
            R.id.w_theme -> i = 0
            R.id.b_theme -> i = 1
            R.id.n_theme -> i = 2
        }
        Theme.putTheme(i)
        Theme.initTheme()
    }
}