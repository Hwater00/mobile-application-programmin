package com.example.myapplication8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import com.example.myapplication8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //전역변수로 시간 기억 변수 선언 puaseTime
    var pauseTime =0L //시간은 s단위가 아닌 mls단위하서 1초= 1000
    var initTime =0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.start.setOnClickListener {
            binding.chronometer.start()
            //지금 chronometer 기준 시간 = 시스템시간.활성화시간, reset에서도 사용
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            //버튼 제어-활성화 처리 . isEnabled = true
            //버튼 제어-비활성화 처리 . isEnabled =false
            binding.start.isEnabled =false
            binding.stop.isEnabled=true
            binding.reset.isEnabled=true
        }
        binding.stop.setOnClickListener{
            binding.chronometer.stop()
            // stop버튼 누른 시간 기억하기 - paiseTime 변수 선언하고 binding.chronometer.base - SystemClock.elapsedRealtime()사용. 기준시간- 활성화 시간
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.start.isEnabled =true
            binding.stop.isEnabled=false
            binding.reset.isEnabled=true
        }
        binding.reset.setOnClickListener{
            binding.chronometer.stop()
            // 처음부터 시작하기 위해서 pauseTime이란 변수 초기화
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.start.isEnabled =true
            binding.stop.isEnabled=true
            binding.reset.isEnabled=false
        }
    }
  //  override fun onCreate 밖에 키 이벤트작성
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            //현재 시스템 시간: System.currentTimeMillis()
            if(System.currentTimeMillis()-initTime>3000){ //3초 이상 시간으로 눌렀
                //메시지 출력하기 - Toast.makeText(첫 번째 인자 this,두번재 인자 "보여줄 메세지",세번째 인자 길이).show()를 통해 토스트 메시지 보이기
                Toast.makeText(this,"보여줄 메세지",Toast.LENGTH_LONG).show()
                initTime =System.currentTimeMillis() // 첫 번째 눌린 시간을 저장해둠, 나중에 눌린 시간이랑 비교할 때 필요
                return true // return true를 통해 백키를 눌러도 토스트만 보이고 프로그램 종료, 이전 등이 되지 않게 막음
            }
        }
        return super.onKeyDown(keyCode, event) //리폴트 값이 false이다.
    }
}

/*
*  1.Gradle Scripts/ bulid.gradle에
* buildFeatures{
        viewBinding=true 뷰 바인딩 설정
    }
   2.setContentView(R.layout.activity_main)을 주석 처리
   3.1번에서 액티비티 메인에 대해서 뷰 바인딩을 트루로 설정했디에 val binding = ActivityMainBinding.inflate(layoutInflater)
   * 엑티비티에 있는 그 레이아웃을 가져온다.
   4.binding.뷰 id형식
   * setOnClickListener는  binding.뷰id.setOnClickListener{}
   * binding.뷰id.isEnabled= false 비활성화
*
* */

/*키 이벤트
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    } 오버라이딩 함수 안에 if(keyCode == KeyEvent.KEYCODE_BACK){   }을 넣어 백키가 눌렸는지 확인
* */

/*메시지 출력하기
Toast.makeText(첫 번째 인자 this,두번재 인자 "보여줄 메세지",세번째 인자 길이).show()를 통해 토스트 메시지 보이기
Toast.makeText(this,"보여줄 메세지",Toast.LENGTH_LONG).show()
* */