package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.finalsapplication.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            // 로그인: 별도의 액티비티를 만들어 처리
            val intent = Intent(this, AuthActivity::class.java)
            if(binding.btnLogin.text.equals("로그인")) // 로그아웃 상태
                intent.putExtra("data", "logout")
            else if(binding.btnLogin.text.equals("로그아웃")) // 로그인 상태
                intent.putExtra("data", "login")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        if(MyApplication.checkAuth() || MyApplication.email != null){  // 검증된 이메일인가
            // 로그인 상태
            binding.btnLogin.text = "로그아웃"
            binding.authTv.text = "${MyApplication.email}님 반갑습니다."
            binding.authTv.textSize = 16F

        }
        else{
            // 로그아웃 상태
            binding.btnLogin.text = "로그인"
            binding.authTv.text = "만성질환관리 쳬계적으로 도와드릴게요. "
            binding.authTv.textSize = 24F

        }
    }

}