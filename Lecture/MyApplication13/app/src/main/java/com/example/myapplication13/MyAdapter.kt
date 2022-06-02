package com.example.myapplication13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication13.databinding.ItemRecyclerviewBinding

//뷰 홀더와
class MyViewHolder(val binding: ItemRecyclerviewBinding) :RecyclerView.ViewHolder(binding.root)
//아답터
class MyAdapter (val dates: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){ //리사이클러뷰 상속 ,어답터뷰홀터 케스트
    override fun getItemCount(): Int {
        //dates의 크기 리턴
        return dates?.size ?: 0 //널일때는 0을 리턴
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    //뷰홀더로 레이아웃xml가져오기
    = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //뷰 홀더와 데이터를 바인딩
        val binding =(holder as MyViewHolder).binding
        binding.itemTodo.text = dates!![position]
    }
}

