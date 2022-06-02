package com.example.myapplication //패키지명

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // AppCompatActivity() 를 상속받는  MainActivity
    override fun onCreate(savedInstanceState: Bundle?) { //override 상속받아 재 작성, Bundle?은 null값 허용
        //lateinit 초기화 미루기  var 변수명: 변수타입
        lateinit var button1 :Button
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)  //제일 먼저 실행, 리소스 등록된 xml R.layout. 으로 연결

        //textview 타입의 변수 선언  = findViewById(R.id) R.java에 등록된 id로 연곃하여 가져오기 - xml를 .kt 연결 findViewById()
        val tv1 :TextView = findViewById(R.id.textview1)
        //뷰 표시 여부 설정 -visibility 변수.visibility = 최상위클래스인 View.설정
        tv1.visibility = View.INVISIBLE

        //뷰 바인딩이란,  액티비티에서 findViewById를 사용하지 않고 자동으로 바인딩 자동으로 만들어지는 클래스 이름은 레이아웃 xml파일명 (단 대문자로 )을 따른다.
        /*
        * Gradle Scripts의 build.gradle에  buildFeatures {
        viewBinding= true
    }을 추가
        ActivityMainBinding는 activity를 바인딩해서 xml에 접속하고 .inflate(layoutInflater) 함수를 사용 layoutInflater를 클래스 객체
        setContentView(binding.root)로 루트 객체로
        *
        * binding.id로 쉽게 작성할 수 있음
         * */

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textview1
        binding.button.visibility = View.INVISIBLE


    //디버그 용 Log("tag는 검색", "msg는 로그 결과 메시지")- Logcat
        Log.d("mycheck","안드로이드 시작")
    }
}