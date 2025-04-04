package com.example.gamesnotes.adapters

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.PointBinding
import com.example.gamesnotes.entity.Point
import kotlin.random.Random

class PointAdapter: RecyclerView.Adapter<PointAdapter.PointHolder>() {
    private var list = ArrayList<Point>()
    class PointHolder(item: View): RecyclerView.ViewHolder(item) {
        var textWatcher: TextWatcher? = null
        val binding = PointBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.point, parent, false)
        return PointHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PointHolder, position: Int) {
        var item = list[position]
        holder.binding.ed.setText(item.text)
        holder.binding.delete.setOnClickListener{
            delPoint(position)
        }
        holder.textWatcher?.let { holder.binding.ed.removeTextChangedListener(it) }
        holder.textWatcher = object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                item.text = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        holder.binding.ed.addTextChangedListener(holder.textWatcher)
//        holder.binding.ed.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                item.text = s.toString()
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })
        holder.binding.ed.setSelection(holder.binding.ed.text?.length!!)
    }

    fun delPoint(pos: Int){
        list.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, list.size)
    }

    fun addPoint(p: Point? = null){
        if(p != null){
            list.add(p)
        } else{
            list.add(Point(""))
        }
        notifyItemInserted(list.size - 1)
    }
}