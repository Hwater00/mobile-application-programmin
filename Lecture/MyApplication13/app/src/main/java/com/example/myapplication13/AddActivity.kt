package com.example.myapplication13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.example.myapplication13.databinding.ActivityAddBinding
import com.example.myapplication13.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2-2인텐트로 전달된 값 받기
        val d1 = intent.getStringExtra("data1")
        val d2 = intent.getStringExtra("data2")

        //add.xml연결
       // binding.tv.text =(d1+d2)

        // 암시적 인텐트
        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_EDIT"
            startActivity(intent)
        }
        /*
        //순서 3add->main으로
        binding.button.setOnClickListener{
            intent.putExtra("test","world")
            setResult(RESULT_OK,intent)
            finish() //이제 액티비티 종료
        }*/

        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.button3 setOnclickListener{
            binding.addEditView.requestFocus()
            manager.showSoftInput(binding.addEditView, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.button4.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken, In)
        }



    }
    //순서5 옵션메뉴 사용
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add,menu) //메뉴로 뭘 이용할지
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //해당되는 메뉴 아이템 조건
        if(item.itemId == R.id.menu_add_save){
            //5-1사용자 입력으로
            intent.putExtra("result",binding.addEditView.text.toString())
            setResult(RESULT_OK,intent)
            finish()
            return true
        }
        return false
    }
}

