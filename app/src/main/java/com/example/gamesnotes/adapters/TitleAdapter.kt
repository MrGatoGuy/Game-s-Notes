package com.example.gamesnotes.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesnotes.R
import com.example.gamesnotes.databinding.PartBinding
import com.example.gamesnotes.entity.Title

class TitleAdapter: RecyclerView.Adapter<TitleAdapter.TitleHolder>() {
    private var list = ArrayList<Title>()
    class TitleHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = PartBinding.bind(item)
        fun bind(title: Title) {
            binding.nameTitle.text = title.name
            binding.nameTitle.setTextColor(title.textColor)
            binding.nameTitle.setHintTextColor(title.textColor)
            binding.card.setCardBackgroundColor(title.color)
            binding.nameTitle.id = title.id!!
            binding.delete.id = title.id!!
            binding.edit.id = title.id!!
        }
    }

    fun getT(id: Int): Title {
        var t = Title("",Color.parseColor("#E6E6E6"),Color.parseColor("#FF000000"),(1..1000).random())
        for(item in list){
            if(item.id == id){
                t = item
            }
        }
        return t
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.part, parent, false)
        return TitleHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TitleHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateTitles(l: ArrayList<Title>){
        list = l
        notifyDataSetChanged()
    }
}