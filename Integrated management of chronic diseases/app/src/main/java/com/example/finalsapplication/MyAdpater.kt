package com.example.finalsapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalsapplication.databinding.ItemApproximatelyBinding

class MyViewHolder(val binding: ItemApproximatelyBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemApproximatelyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val binding = (holder as MyViewHolder).binding
        binding.itemApproximately.text = datas!![position]
    }
}