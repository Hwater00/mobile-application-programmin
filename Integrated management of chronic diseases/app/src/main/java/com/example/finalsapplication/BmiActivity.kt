package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalsapplication.databinding.ActivityBmiBinding
import com.example.finalsapplication.databinding.ActivityMainBinding

class BmiActivity : AppCompatActivity() {
    //바인딩 객체 선언
    private var mBinding: ActivityBmiBinding?= null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩 초기화
        mBinding = ActivityBmiBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val heightEditText = binding.heightEditText
        val weightEditText = binding.weightEditText
        val resultButton = binding.resultButton

        resultButton.setOnClickListener {
            // 키나 몸무게가 입력되지 않았으면 토스트메시지
            if(heightEditText.text.isEmpty() || weightEditText.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //intent: 여러 화면 간의 이동을 할 수 있도록 해주는 것
            //각각의 edittext에 작성한 값 int로 넘겨주기
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()

            val intent = Intent(this, HealthActivity::class.java)

            //다른 액티비티(resultActivity)로 데이터 전달
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)

            //다른 액티비티 시작 -> 액티비티 이동
            startActivity(intent)
        }
    }
}