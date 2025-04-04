package com.example.gamesnotes.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.ActivityNoteBinding
import com.example.gamesnotes.databinding.NotePartBinding
import com.example.gamesnotes.entity.Note
import kotlinx.coroutines.withContext

class NoteAdapter(context: Context) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var mContext = context
    private var list = ArrayList<Note>()

    class NoteHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = NotePartBinding.bind(item)
        fun bind(note: Note, mContext: Context) = with(binding) {
            Log.d("MyLog",note.toString())
            if (note.points != "" && note.points != null) {
                val ar = note.points.split("**###*").toTypedArray()
                var s = ""
                for (item in ar) {
                    s += item
                }
                pointsItself.text = s
                points.setTextColor(note.textColor)
                pointsItself.setTextColor(note.textColor)
                points.visibility = View.VISIBLE
                pointsItself.visibility = View.VISIBLE
                border4.visibility = View.VISIBLE
            } else {
                points.visibility = View.GONE
                pointsItself.visibility = View.GONE
                border4.visibility = View.GONE
            }
            if (note.description != "" && note.description != null) {
                Log.d("MyLog","descr dobav")
                descriptionItself.text = note.description
                Log.d("MyLog",descriptionItself.text.toString())
                description.setTextColor(note.textColor)
                descriptionItself.setTextColor(note.textColor)
                description.visibility = View.VISIBLE
                descriptionItself.visibility = View.VISIBLE
                border2.visibility = View.VISIBLE
            } else {
                description.visibility = View.GONE
                descriptionItself.visibility = View.GONE
                border2.visibility = View.GONE
            }
            if (note.time != "" && note.time != null) {
                timeItself.text = note.time
                time.setTextColor(note.textColor)
                timeItself.setTextColor(note.textColor)
                time.visibility = View.VISIBLE
                timeItself.visibility = View.VISIBLE
                border3.visibility = View.VISIBLE
            } else {
                time.visibility = View.GONE
                timeItself.visibility = View.GONE
                border3.visibility = View.GONE
            }
            //Тип заметки
            typeImg.setImageResource(note.tImg)
            typeNote.setText(note.type)
            typeNote.setTextColor(note.textColor)
            border.setBackgroundColor(note.textColor)
            border2.setBackgroundColor(note.textColor)
            border3.setBackgroundColor(note.textColor)
            border4.setBackgroundColor(note.textColor)
            //основа
            bottomCard.setCardBackgroundColor(note.bottomColor)
            nameNote.text = note.name
            nameNote.setTextColor(note.textColor)
            nameNote.setHintTextColor(note.textColor)
            card.setCardBackgroundColor(note.color)
            delete.id = note.id!!
            edit.id = note.id!!
            expand.id = note.id!!
            if (note.rotate) {
                expand.setImageResource(R.drawable.roll_reverse)
                bottomCard.visibility = View.VISIBLE
            } else {
                expand.setImageResource(R.drawable.roll)
                bottomCard.visibility = View.GONE
            }
        }
    }

    fun getN(id: Int): Note? {
        var n: Note
        for (item in list) {
            if (item.id == id) {
                n = item
                return n
            }
        }
        return null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_part, parent, false)
        return NoteHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(list[position], mContext)
    }

    fun updateNotes(l: ArrayList<Note>) {
        list = l
        notifyDataSetChanged()
    }
}