package com.example.gamesnotes.activity

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesnotes.R
import com.example.gamesnotes.adapters.PointAdapter
import com.example.gamesnotes.databinding.ActivityEditNoteBinding
import com.example.gamesnotes.db.MainDb
import com.example.gamesnotes.entity.Note
import com.example.gamesnotes.entity.Point
import com.example.gamesnotes.other.Theme.putExtraTheme
import com.google.android.material.textfield.TextInputEditText
import java.util.ArrayList

class EditNoteActivity : AppCompatActivity() {
    private val adapter = PointAdapter()
    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var note: Note
    private var resPoints = ""
    private var editBool = false
    private lateinit var db: MainDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDb.getDb(this)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putExtraTheme(binding.themeView,binding.shadow)
        initObj()
        initPointAdapter()
    }
    private fun initPointAdapter(){
        binding.rcPoints.layoutManager = LinearLayoutManager(this)
        binding.rcPoints.adapter = adapter
    }
    private fun initObj(){
        var idTitle = ""
        if (intent.getStringExtra("id_title") != null) {
            idTitle = intent.getStringExtra("id_title") as String
        }
        note = Note(
            "", "", "", idTitle, "", R.string.note, R.drawable.note,
            getColor(R.color.white_almost), getColor(R.color.black),
            getColor(R.color.black_hint), getColor(R.color.white_almost_bottom), null
        )
        if (intent.getSerializableExtra("note") != null) {
            note = intent.getSerializableExtra("note") as Note
            binding.apply {
                nameNote.setText(note.name)
                curColor.setBackgroundColor(note.color)
                curType.setImageResource(note.tImg)
                desText.setText(note.description)
                timeText.setText(note.time)
            }
            var ar = note.points.split("**###*").toMutableList()
            ar.removeAt(ar.lastIndex)
            var ah = mutableListOf<String>()
            for(item in ar){
                var o = item.replace("\n● ","")
                o = o.replace("● ","")
                ah.add(o)
            }
            for(item in ah){
                adapter.addPoint(Point(item))
            }
            editBool = true
        }
    }

    //Кнопки "ОК" и "Отмена"
    fun funOk(view: View) {
        isClass(binding.rcPoints)
        note.name = binding.nameNote.text.toString()
        note.points = resPoints
        note.description = binding.desText.text.toString()
        note.time = binding.timeText.text.toString()
        val i = Intent()
        i.putExtra("note", note)
        if (!editBool) {
            Thread {
                db.getDao().insertNote(note)
                runOnUiThread {
                    setResult(RESULT_OK)
                    finish()
                }
            }.start()
        } else {
            Log.d("myLog", "Proishodit edit")
            Thread {
                db.getDao().editNote(
                    note.points,
                    note.time,
                    note.description,
                    note.idTitle,
                    note.name,
                    note.type,
                    note.tImg,
                    note.bottomColor,
                    note.color,
                    note.textColor,
                    note.id!!
                )
                runOnUiThread {
                    setResult(RESULT_OK)
                    finish()
                }
            }.start()
        }
    }

    private fun isClass(view: View, id: Int? = null){
        if(view is TextInputEditText && id != view.id){
            if(resPoints != "") {
                resPoints = resPoints + "\n● " + view.text  + "**###*"
            }
            else {
                resPoints = "● " + view.text + "**###*"
            }
        }
        else if(view is ViewGroup){
            for(i in 0 until view.childCount){
                isClass(view.getChildAt(i))
            }
        }
    }

    fun funCancel(view: View) {
        setResult(RESULT_CANCELED)
        finish()
    }

    //Работа с цветом
    fun funCurColor(view: View) {
        if (binding.cardColor.visibility == View.VISIBLE) {
            binding.cardColor.visibility = View.GONE
        } else {
            binding.cardColor.visibility = View.VISIBLE
            binding.cardType.visibility = View.GONE
        }
    }

    fun whiteColor(view: View) {
        setResultColor(
            getColor(R.color.white_almost_bottom),
            getColor(R.color.white_almost),
            getColor(R.color.black)
        )
    }

    fun blackColor(view: View) {
        setResultColor(
            getColor(R.color.black_almost_bottom),
            getColor(R.color.black_almost),
            getColor(R.color.white)
        )
    }

    fun redColor(view: View) {
        setResultColor(getColor(R.color.red_bottom), getColor(R.color.red), getColor(R.color.white))
    }

    fun blueColor(view: View) {
        setResultColor(
            getColor(R.color.blue_bottom),
            getColor(R.color.blue),
            getColor(R.color.white)
        )
    }

    fun greenColor(view: View) {
        setResultColor(
            getColor(R.color.green_bottom),
            getColor(R.color.green),
            getColor(R.color.black)
        )
    }

    fun purpleColor(view: View) {
        setResultColor(
            getColor(R.color.purple_bottom),
            getColor(R.color.purple),
            getColor(R.color.white)
        )
    }

    fun orangeColor(view: View) {
        setResultColor(
            getColor(R.color.orange_bottom),
            getColor(R.color.orange),
            getColor(R.color.black)
        )
    }

    fun yellowColor(view: View) {
        setResultColor(
            getColor(R.color.yellow_bottom),
            getColor(R.color.yellow),
            getColor(R.color.black)
        )
    }

    private fun setResultColor(colorBottom: Int, color: Int, textColor: Int) {
        if (textColor == getColor(R.color.black)) {
            note.textColorHint = getColor(R.color.black_hint)
        } else {
            note.textColorHint = getColor(R.color.white_hint)
        }
        note.bottomColor = colorBottom
        note.color = color
        note.textColor = textColor
        binding.curColor.setBackgroundColor(note.color)
    }

    //Работа с типом
    fun funCurType(view: View) {
        if (binding.cardType.visibility == View.VISIBLE) {
            binding.cardType.visibility = View.GONE
        } else {
            binding.cardType.visibility = View.VISIBLE
            binding.cardColor.visibility = View.GONE
        }
    }

    fun heroType(view: View) {
        setResultType(R.string.hero)
    }

    fun questType(view: View) {
        setResultType(R.string.quest)
    }

    fun noteType(view: View) {
        setResultType(R.string.note)
    }

    fun locationType(view: View) {
        setResultType(R.string.location)
    }

    fun lootType(view: View) {
        setResultType(R.string.loot)
    }

    fun craftType(view: View) {
        setResultType(R.string.craft)
    }

    private fun setResultType(type: Int) {
        val tImg = when (type) {
            R.string.hero -> R.drawable.hero
            R.string.quest -> R.drawable.quest
            R.string.note -> R.drawable.note
            R.string.loot -> R.drawable.loot
            R.string.location -> R.drawable.location
            R.string.craft -> R.drawable.craft
            else -> 0
        }
        binding.curType.setImageResource(tImg)
        note.type = type
        note.tImg = tImg
    }

    //Работа с points
    fun crPoint(view: View) {
        adapter.addPoint()
    }
}