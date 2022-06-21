package com.example.finalsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalsapplication.databinding.ItemPixBinding

class PixViewHolder(val binding: ItemPixBinding): RecyclerView.ViewHolder(binding.root)
class PixAdapter (val context: Context, val itemList: MutableList<itemPixData>): RecyclerView.Adapter<PixViewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PixViewHolder(ItemPixBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: PixViewHolder, position: Int) {
        val data = itemList.get(position)
        holder.binding.run{
            itemEmailView.text = data.email
            itemDateView.text = data.date
            itemContentView.text = data.content
        }

        val imageRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
        imageRef.downloadUrl.addOnCompleteListener { task ->
            if(task.isSuccessful){
                Glide.with(context)
                    .load(task.result)
                    .into(holder.binding.itemImageView)
            }
        }
    }
}