package com.example.finalsapplication

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.finalsapplication.databinding.ActivityMyPageBinding
import java.lang.Exception

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_my_page)

        val binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            try{
                val calRatio=calculateInSampleSize(
                    it.data!!.data!!, 150, 150
                )
                val option = BitmapFactory.Options()
                //option.inSampleSize=4
                var inputStream=contentResolver.openInputStream(it.data!!.data!!)
                val bitmap= BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream=null

                bitmap?.let {
                    binding.userIdImg.setImageBitmap(bitmap)
                }?: let{
                    Log.d("mobileApp", "bitmap null")
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.changeprofile.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type="image/*"
            requestGalleryLauncher.launch(intent)
        }


        binding.gotosetting.setOnClickListener{


            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}