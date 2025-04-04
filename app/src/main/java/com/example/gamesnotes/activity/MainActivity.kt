package com.example.gamesnotes.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesnotes.R
import com.example.gamesnotes.activity.options.InfoActivity
import com.example.gamesnotes.activity.options.LanguageActivity
import com.example.gamesnotes.activity.options.ThemeActivity
import com.example.gamesnotes.adapters.TitleAdapter
import com.example.gamesnotes.databinding.ActivityMainBinding
import com.example.gamesnotes.db.MainDb
import com.example.gamesnotes.entity.Title
import com.example.gamesnotes.other.Language
import com.example.gamesnotes.other.Theme
import com.example.gamesnotes.other.MenuRoll
import com.example.gamesnotes.other.Theme.pref
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MainDb
    private var delVar = 0
    private val adapter = TitleAdapter()
    private var addTitleLauncher: ActivityResultLauncher<Intent>? = null
    private var editTitleLauncher: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDb.getDb(this)
        initL()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTheme()
        initTitlesWithAdapter()
        initActivityResult()
        initDrawer()
    }

    private fun initL(){
        Language.pref = getSharedPreferences("app_preferences", MODE_PRIVATE)
        Language.initLanguage(resources)
    }
    private fun initTheme(){
        Theme.apply {
            pref = getSharedPreferences("app_preferences", MODE_PRIVATE)
            initTheme()
            putExtraTheme(binding.themeView,binding.shadow)
        }
    }

    fun initNavView(view: View) {
        when (view.id) {
            R.id.info -> {
                startActivity(Intent(this, InfoActivity::class.java))
            }

            R.id.language -> {
                startActivity(Intent(this, LanguageActivity::class.java))
            }

            R.id.themes -> {
                startActivity(Intent(this, ThemeActivity::class.java))
            }
        }
    }

    private fun initDrawer() {
        binding.main.addDrawerListener(MenuRoll(binding.opM))
        binding.opM.setOnClickListener {
            if (!binding.main.isDrawerOpen(GravityCompat.START)) {
                binding.main.openDrawer(GravityCompat.START)
            }
        }
    }

    //Отображение заголовков
    private fun initActivityResult() {
        addTitleLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    var l: List<Title>
                    Thread {
                        l = db.getDao().getAllTitles()
                        runOnUiThread {
                            adapter.updateTitles(ArrayList(l))
                        }
                    }.start()
                }
            }
        editTitleLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    var l: List<Title>
                    Thread {
                        l = db.getDao().getAllTitles()
                        runOnUiThread {
                            adapter.updateTitles(ArrayList(l))
                        }
                    }.start()
                }
            }
    }

    //Отображение заголовков
    private fun initTitlesWithAdapter() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }
        var l: List<Title>
        Thread {
            l = db.getDao().getAllTitles()
            runOnUiThread {
                adapter.updateTitles(ArrayList(l))
            }
        }.start()
    }

    //Добавление заголовка
    fun addTitle(view: View) {
        addTitleLauncher?.launch(Intent(this@MainActivity, EditTitleActivity::class.java))
    }

    //Изменение заголовка
    fun editTitle(view: View) {
        val t = adapter.getT(view.id)
        val i = Intent(this@MainActivity, EditTitleActivity::class.java)
        i.putExtra("title", t)
        editTitleLauncher?.launch(i)
    }

    //Удаление Заголовка
    fun beforeDeleteTitle(view: View) {
        binding.warTitle.visibility = View.VISIBLE
        delVar = view.id
        Log.d("MyLog", view.id.toString())
    }

    fun deleteTitle(view: View) {
        binding.warTitle.visibility = View.GONE
        var l: List<Title>
        Thread {
            db.getDao().deleteTitle(delVar)
            db.getDao().deleteAllNotes(delVar.toString())
            l = db.getDao().getAllTitles()
            runOnUiThread {
                adapter.updateTitles(ArrayList(l))
            }
        }.start()
    }

    fun notDeleteTitle(view: View) {
        binding.warTitle.visibility = View.GONE
    }

    //переход к заметкам
    fun toNote(view: View) {
        val i = Intent(this, NoteActivity::class.java)
        i.putExtra("id_title", view.id.toString())
        startActivity(i)
    }
}