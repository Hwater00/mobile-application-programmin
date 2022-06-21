package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalsapplication.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApproximatelyRecy.setOnClickListener {
            intent = Intent(this, ApproximatelyActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnHealth.setOnClickListener {
            intent = Intent(this, HealthActivity::class.java)
            startActivity(intent)
        }
        binding.btnSet.setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
        binding.btnFood.setOnClickListener {
            intent = Intent(this, PictureActivity::class.java)
            startActivity(intent)
        }
    }
}