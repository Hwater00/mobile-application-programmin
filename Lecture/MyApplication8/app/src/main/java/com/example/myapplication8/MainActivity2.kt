package com.example.myapplication8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
    //터치 이벤트
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("mobile","${event.x},${event.y}")
            }                                 //Log.d("","${event.x},${event.y}을 통해 앱x,y좌표"/ ${event.rawX},${event.rawY}스마트폰 x,y)
            MotionEvent.ACTION_UP -> {
                Log.d("mobile","${event.rawX},${event.rawY}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("mobile","")
            }
        }
        return super.onTouchEvent(event)
    }
    //키 이벤트
    //키 다운
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_0 -> {
                Log.d("","")}
            KeyEvent.KEYCODE_BACK -> {
                Log.d("","")}

            //볼륨 업
            KeyEvent.KEYCODE_VOLUME_UP->{
                Log.d("","")}
        }
        return super.onKeyDown(keyCode, event)
    }
    // 키 업
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    override fun onBackPressed() {
        Log.d("","")
        //-뒤로가기- override fun onBackPressed() {} 또는 KeyEvent.KEYCODE_BACK -> {Log.d("","")}으로 뒤로 가기 가능

    }

}

/*
1. 터치 이벤트
*  override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
            Log.d("","${event.x},${event.y}을 통해 앱x,y좌표"/ ${event.rawX},${event.rawY}스마트폰 x,y)

            }
            MotionEvent.ACTION_UP -> {}
            MotionEvent.ACTION_MOVE -> {}
        }
        return super.onTouchEvent(event)
    }
2.키 이벤트
override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_ 키 이벤트 키고드를 통해 어떤 키가 눌렸는지-> {Log.d("","")}
        }
        return super.onKeyDown(keyCode, event)
    }

 -뒤로가기- override fun onBackPressed() {} 또는 KeyEvent.KEYCODE_BACK -> {Log.d("","")}으로 뒤로 가기 가능

3.뷰 이벤트
뷰 이벤트 처리는 이벤트 소스인 발생 객체와 이벤트 핸들러로 역할 핸들러.On이벤트 Listener() 형태로 이둘을 리스너로 연결
리스너는 set이벤트핸들러()형태
SAM방법을 사용하여

* */