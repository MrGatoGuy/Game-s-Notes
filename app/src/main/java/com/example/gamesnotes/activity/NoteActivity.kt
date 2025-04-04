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
import com.example.gamesnotes.adapters.NoteAdapter
import com.example.gamesnotes.databinding.ActivityNoteBinding
import com.example.gamesnotes.db.MainDb
import com.example.gamesnotes.entity.Note
import com.example.gamesnotes.entity.Title
import com.example.gamesnotes.other.MenuRoll
import com.example.gamesnotes.other.Theme.putExtraTheme
import java.util.ArrayList

class NoteActivity : AppCompatActivity() {
    private var delVar = 0
    private lateinit var idTitle: String
    private val adapter = NoteAdapter(this)
    private var addNoteLauncher: ActivityResultLauncher<Intent>? = null
    private var editNoteLauncher: ActivityResultLauncher<Intent>? = null
    private lateinit var binging: ActivityNoteBinding
    private lateinit var db: MainDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDb.getDb(this)
        binging = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binging.root)
        putExtraTheme(binging.themeView,binging.shadow)
        idTitle = intent?.getStringExtra("id_title") as String
        initNotesWithAdapter()
        initActivityResult()
        initTitle()
        initDrawer()
    }
    private fun initDrawer() {
        binging.main.addDrawerListener(MenuRoll(binging.opM))
        binging.opM.setOnClickListener {
            if (!binging.main.isDrawerOpen(GravityCompat.START)) {
                binging.main.openDrawer(GravityCompat.START)
            }
        }
    }
    fun initNavViewInNote(view: View) {
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
    private fun initTitle(){
        var t: Title
        Thread{
            t = db.getDao().getTitle(idTitle!!.toInt())
            runOnUiThread {
                if(t.name != ""){
                    binging.titleOfNotes.text = t.name
                }
            }
        }.start()
    }
    private fun initActivityResult(){
        addNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                var l: List<Note>
                Thread{
                    l = db.getDao().getAllNotes(idTitle)
                    runOnUiThread {
                        adapter.updateNotes(ArrayList(l))
                    }
                }.start()
            }
        }
        editNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                var l: List<Note>
                Thread{
                    l = db.getDao().getAllNotes(idTitle)
                    runOnUiThread {
                        adapter.updateNotes(ArrayList(l))
                    }
                }.start()
            }
        }
    }
    private fun initNotesWithAdapter(){
        binging.apply {
            rcView.layoutManager = LinearLayoutManager(this@NoteActivity)
            rcView.adapter = adapter
        }
        var l: List<Note>
        Thread{
            l = db.getDao().getAllNotes(idTitle)
            runOnUiThread{
                adapter.updateNotes(ArrayList(l))
            }
        }.start()
    }
    //Добавление заголовка
    fun addNote(view: View) {
        var i = Intent(this@NoteActivity, EditNoteActivity::class.java)
        i.putExtra("id_title",idTitle)
        addNoteLauncher?.launch(i)
    }
    //Изменение заголовка
    fun editNote(view: View) {
        val n = adapter.getN(view.id)
        val i = Intent(this@NoteActivity, EditNoteActivity::class.java)
        i.putExtra("note",n)
        editNoteLauncher?.launch(i)
    }
    //Удаление Заголовка
    fun beforeDeleteNote(view: View) {
        binging.warNote.visibility = View.VISIBLE
        delVar = view.id
        Log.d("MyLog",view.id.toString())
    }
    fun deleteNote(view: View) {
        binging.warNote.visibility = View.GONE
        var l: List<Note>
        Thread{
            db.getDao().deleteNote(delVar)
            l = db.getDao().getAllNotes(idTitle)
            runOnUiThread {
                adapter.updateNotes(ArrayList(l))
            }
        }.start()
    }
    fun notDeleteNote(view: View) {
        binging.warNote.visibility = View.GONE
    }
    fun expandNote(view: View) {
        Thread{
            val bool = db.getDao().getRotateNote(view.id)
            db.getDao().rotateNote(view.id,!bool)
            val l = db.getDao().getAllNotes(idTitle)
            runOnUiThread {
                adapter.updateNotes(ArrayList(l))
            }
        }.start()
    }
}