package com.example.finalsapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.finalsapplication.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val d1 = intent.getStringExtra("data1")
        val d2 = intent.getStringExtra("data2")

        binding.addtv.text = (d1+d2)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add_save) {
            intent.putExtra("result", binding.addEditView.text.toString())
            setResult(RESULT_OK, intent)
            finish()
            return true
        }
        return false
    }
}