package com.example.myapplication13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//암시적 호출
class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
    }
}