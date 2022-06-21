package com.example.finalsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.finalsapplication.databinding.ActivityFoodBinding
import com.example.finalsapplication.databinding.ActivityMainBinding
import java.lang.Boolean.TRUE
import java.util.jar.Pack200.Packer.TRUE

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var itemView: LinearLayout
        val fragment = XmlFragment()
        val bundle = Bundle()

        val binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBtn.setOnClickListener {


                when (binding.rGroup.checkedRadioButtonId) {
                    R.id.xml -> bundle.putString("returnType", "xml")
                    else ->  itemView.setVisibility(View.VISIBLE)
                }
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_content, fragment)  // (어디에, 어떤 프래그먼트를)
                    .commit()


            }



    }
}

