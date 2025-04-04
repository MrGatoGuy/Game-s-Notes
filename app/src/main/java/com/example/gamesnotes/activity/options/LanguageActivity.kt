package com.example.gamesnotes.activity.options

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesnotes.R
import com.example.gamesnotes.activity.MainActivity
import com.example.gamesnotes.databinding.ActivityLanguageBinding
import com.example.gamesnotes.other.Language
import com.example.gamesnotes.other.Theme.putExtraTheme

class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putExtraTheme(binding.themeView,binding.shadow)
    }
    fun takeLanguage(view: View){
        var i = ""
        when(view.id){
            R.id.ru_b -> i = "ru"
            R.id.eng_b -> i = "en"
        }
        Language.putLanguage(i)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}