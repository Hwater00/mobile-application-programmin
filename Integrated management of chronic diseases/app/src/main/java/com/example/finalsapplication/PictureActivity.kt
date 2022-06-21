package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalsapplication.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    lateinit var binding: ActivityPictureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)

        binding.buttonItem.setOnClickListener {
            intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
        }
        /* 이미지 버튼 */
        binding.addFab.setOnClickListener {
            if(MyApplication.checkAuth()){
                startActivity(Intent(this, PixAddActivity::class.java))
            }
            else{
                Toast.makeText(this, "인증을 진행해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth() || MyApplication.email != null){  // 검증된 이메일인가
            // 로그인 상태
            //binding.btnLogin.text = "로그아웃"
            //binding.authTv.text = "${MyApplication.email}님 반갑습니다."
            //binding.authTv.textSize = 16F

            binding.mainRecyclerView.visibility = View.VISIBLE // 이미지 리사이클러뷰
            makeRecyclerView()
        }
        else{
            // 로그아웃 상태
            //binding.btnLogin.text = "로그인"
            //binding.authTv.text = "덕성 모바일"
            //binding.authTv.textSize = 24F

            binding.mainRecyclerView.visibility = View.GONE // 이미지 리사이클러뷰
        }
    }

    private fun makeRecyclerView() {
        MyApplication.db.collection("news")  // AddActivity에서 news에 저장하기로 함
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<itemPixData>()
                for(document in result){
                    val item = document.toObject(itemPixData::class.java)
                    item.docId = document.id
                    itemList.add(item)
                }
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.mainRecyclerView.adapter = PixAdapter(this, itemList)
            }
            .addOnFailureListener {
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }

    }

}