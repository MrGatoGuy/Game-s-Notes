package com.example.gamesnotes.activity.options

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.ActivityInfoBinding
import com.example.gamesnotes.other.Theme.putExtraTheme

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putExtraTheme(binding.themeView,binding.shadow)
    }
}