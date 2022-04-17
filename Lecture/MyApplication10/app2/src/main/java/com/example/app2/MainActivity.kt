package com.example.app2

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {

            val nofication : Uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)  //시스템이제공하는 알림 소리를 받는 변수
            val rington = RingtoneManager.getRingtone(applicationContext,nofication) //무엇에 대한 Ringtone을 만들건지 설정(application콘텐트,변수)
            rington.play() //출력
        }
        binding.btn2.setOnClickListener {
            val player: MediaPlayer = MediaPlayer.create(this,R.raw.funny_voices) //미디어mp3는 MediaPlayer 틀래스 사용
            player.start()

        }
        binding.btn3.setOnClickListener{
            // vibrator 객체를 담는 변수
            val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                //버전이 s31이상일때 아래 버전 사용
                val vibratorManager =
                    this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else{
                getSystemService(VIBRATOR_SERVICE) as Vibrator//하위버전 호환성을 위해서 사용
            }
            // vibrator을 이용한 진동
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE) )
            } else{
                vibrator.vibrate(500)
            }

        }
    }
}

/* 소리,진동 알림
1.소리에 대한 모든처리는  RingtoneManager에서 한다. : Uri= RingtoneManager.getDefaultUri(RingtoneManager.타입)
raw타입의 디렉토리를 res에 만들어서 음악파일을 저장한다,
MediaPlayer = MediaPlayer.create(this,R.raw.음원이름) //미디어mp3는 MediaPlayer 틀래스 사용

2.진동은 매니페스트 파일 <user-permission> 선언 필요
vibrate 함수를 사용- 버전 주의

3.상태바의 앱의 정보를 출력하는 notification

* */