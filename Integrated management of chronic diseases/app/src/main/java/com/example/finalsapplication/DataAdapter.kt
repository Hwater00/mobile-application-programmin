package com.example.finalsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalsapplication.databinding.ItemFoodBinding

class XmlViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root)

class DataAdapter (val context: Context, val datas: MutableList<myItem>?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return XmlViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as XmlViewHolder).binding
        val model = datas!![position]
        binding.name.text= model.DESC_KOR
        binding.nutr1.text=model.NUTR_CONT1
        binding.nutr2.text=model.NUTR_CONT2
    }
}