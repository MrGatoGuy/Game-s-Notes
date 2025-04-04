package com.example.gamesnotes.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.ActivityEditTitleBinding
import com.example.gamesnotes.db.MainDb
import com.example.gamesnotes.entity.Title
import com.example.gamesnotes.other.Theme.putExtraTheme

class EditTitleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTitleBinding
    private lateinit var db: MainDb
    private lateinit var title: Title
    private var editBool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MainDb.getDb(this)
        binding = ActivityEditTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putExtraTheme(binding.themeView,binding.shadow)
        initObj()
    }

    private fun initObj(){
        title = Title("",resources.getColor(R.color.white_almost),resources.getColor(R.color.black),null)
        if(intent.getSerializableExtra("title") != null){
            title = intent.getSerializableExtra("title") as Title
            binding.nameTitle.setText(title.name)
            binding.curColor.setBackgroundColor(title.color)
            editBool = true
        }
    }

    //Кнопки "ОК" и "Отмена"
    fun funOk(view: View) {
        title.name = binding.nameTitle.text.toString()
        val i = Intent()
        i.putExtra("title",title)
        if(!editBool) {
            Thread{
                db.getDao().insertTitle(title)
                runOnUiThread{
                    setResult(RESULT_OK)
                    finish()
                }
            }.start()
        }
        else{
            Thread{
                db.getDao().editTitle(title.name,title.color,title.textColor,title.id!!)
                runOnUiThread{
                    setResult(RESULT_OK)
                    finish()
                }
            }.start()
        }
    }

    fun funCancel(view: View) {
        setResult(RESULT_CANCELED)
        finish()
    }
    //Работа с цветом
    fun funCurColor(view: View){
        if(binding.cardColor.visibility == View.VISIBLE){
            binding.cardColor.visibility = View.GONE
        } else{
            binding.cardColor.visibility = View.VISIBLE
        }
    }
    fun whiteColor(view: View) {
        title.color = resources.getColor(R.color.white_almost)
        title.textColor = resources.getColor(R.color.black)
        setResultColor()
    }
    fun blackColor(view: View){
        title.color = resources.getColor(R.color.black_almost)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun redColor(view: View){
        title.color = resources.getColor(R.color.red)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun blueColor(view: View){
        title.color = resources.getColor(R.color.blue)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun greenColor(view: View){
        title.color = resources.getColor(R.color.green)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun purpleColor(view: View){
        title.color = resources.getColor(R.color.purple)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun orangeColor(view: View){
        title.color = resources.getColor(R.color.orange)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }
    fun yellowColor(view: View){
        title.color = resources.getColor(R.color.yellow)
        title.textColor = resources.getColor(R.color.white)
        setResultColor()
    }

    fun setResultColor() {
        binding.curColor.setBackgroundColor(title.color)
    }
}