package com.example.finalsapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.finalsapplication.databinding.ActivityHealthBinding
import kotlin.math.pow

class HealthActivity : AppCompatActivity(), SensorEventListener {
    lateinit var binding : ActivityHealthBinding
    lateinit var sensorManager: SensorManager
    var stepCountSensor : Sensor? = null
    lateinit var mContext : Context

    val ALL_PERMISSION_OK = 100

    private var mBinding: ActivityHealthBinding?= null
    private val sbinding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHealthBinding.inflate(layoutInflater)
        binding = ActivityHealthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this@HealthActivity
        sensorManager = mContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepCountSensor == null){
            Toast.makeText(mContext, "No Step Detect Sensor", Toast.LENGTH_SHORT).show()
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), ALL_PERMISSION_OK)
                }
            }
        }
        // 생성된 뷰 액티비티에 표시시
        setContentView(sbinding.root)

        sbinding.btnBmi.setOnClickListener {
            intent = Intent(this, BmiActivity::class.java)
            startActivity(intent)
        }
        //getIntExtra: 메소드를 이용해서 데이터들을 전송
        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        //bmi 계산
        val bmi = weight/ (height / 100.0).pow(2.0)

        //각 bmi 값 마다 result 값 범위로 정해주기
        val resultText = when {
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        //binding으로 각 xml요소들 연결하여 activity에서 사용
        val resultValueTextView = sbinding.bmiResultTextView
        val resultStringTextView = sbinding.resultTextView

        //정해진 요소들 text로 각각의 위치에 보여주기
        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            ALL_PERMISSION_OK -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "승인 O", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(mContext, "승인 X", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor == stepCountSensor){
            binding.tvStepCount.text = "Step Count : ${event?.values?.get(0)}"
        }
    }
}