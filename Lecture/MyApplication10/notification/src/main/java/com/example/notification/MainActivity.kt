package com.example.notification
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener{
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager //notification 매니저 가져오기
            val builder : NotificationCompat.Builder
            //버전 호환성
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val ch_id ="one-chanal"
                val channel = NotificationChannel(ch_id,"채널1",NotificationManager.IMPORTANCE_DEFAULT) //NotificationManager.중요도에 따라 매우 hight, default,low
                //채널 속성 변경
                channel.description = "My Channel One 소개"  //채널의 설명 문자열
                channel.setShowBadge(true)  //홈 화면의 아이콘에 배지 아이콘 출력 여부 // 확인하지 않은 알림의 개수 표시
                channel.enableLights(true) //불빛 표시 여부
                channel.lightColor = Color.RED // 불빛이 표시 시 불빛의 색상 설정
                channel.enableVibration(true) //진동을 울릴지 여부
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200) //진동을 울린다면 진동의 패턴 ,long형태의 int 값(100, 200), (100, 200) -> 두개의 쌍으로 이루어짐.
                //(진동 x 시간, 진동 o 시간): msec 0.1초, 0.2 초

                //소리 재생 RingtomMager
                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) //소리의 식별값 얻기
                val audio_attr = AudioAttributes.Builder() // AudioAttributes이용 ,채널의 두번째 파라미터
                     //소리 속성 설정
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // 기계음 타입
                    .setUsage(AudioAttributes.USAGE_ALARM) // 어떤 용도로 사용할 것인지 - 현재는 알람
                    .build()
                channel.setSound(uri, audio_attr)//* 알림음 설정 //소리에 대한 처리는 다 RingtonManager가 다 처리

                manager.createNotificationChannel(channel) //체널 등록
                builder = NotificationCompat.Builder(this,ch_id)
            }
            else{//25버전 이하
                builder = NotificationCompat.Builder(this)
            }
            //Notification 알림 객체 builder속성
            builder.setSmallIcon(R.drawable.small)//스몰 아이콘
            builder.setWhen(System.currentTimeMillis()) //발생 시각
            builder.setContentTitle("알림") //제목
            builder.setContentText("모바일 앱 프로그래밍 중간고사") //내용-텍스트 출력
            //내용-이미지 출력

            //인텐트
            val replyIntent = Intent(this,ReplyReceiver::class.java) //생성한Brodcast Reciver.kt
            val replyPendingIntent = PendingIntent.getBroadcast(this,30,replyIntent,PendingIntent.FLAG_MUTABLE) //tBroadcast 인텐트 등록
            builder.setContentIntent(replyPendingIntent) //builder의 등록

            //원격 입력
            val remoteInput = RemoteInput.Builder("key_text_replay").run{ //원격 입력도 액션의 한 종류, RemoteInput에 사용자 입력을 받는 정보를 설정한 후 액션에 추가
                setLabel("답장") //만들 형태
                build()  // RemoteInput을 직접 상단에 import를 해서 androidx 사용
                //액션에 addRemoteInput(remoteInput).build로 수정
            }


            //액션등록
            builder.addAction(
                NotificationCompat.Action.Builder(
                    //인자 3개. 아이콘, 제목, 인텐트
                    android.R.drawable.stat_notify_more,
                    "답장",
                    replyPendingIntent //이벤트 처리가 목적이므로 인텐트 등록w
                ).addRemoteInput(remoteInput).build() //remoteInput으로 입력받지 않을땐 그냥 build만 사용
            )
            //액션등록
            builder.addAction(
                NotificationCompat.Action.Builder(
                    //인자 3개. 아이콘, 제목, 인텐트
                    android.R.drawable.stat_notify_more,
                    "Action",
                    replyPendingIntent //이벤트 처리가 목적이므로 인텐트 등록w
                ).addRemoteInput(remoteInput).build() //remoteInput으로 입력받지 않을땐 그냥 build만 사용
            )


            manager.notify(11,builder.build()) //알림 생성//몇 번째인지 필요함= 임의의 숫자
            // 알림 취소manager.cancel(11)

        }
    }
}

/*notification 설명
제일 먼저 Brodcast Reciver 파일->뉴->other->brodcast Reciver
매니저(manager)와 빌더(builder) 필요함
버전 설정 if,else,  manager.notify(11,builder.build())

 val channel = NotificationChannel(ch_id,"채널1",NotificationManager.IMPORTANCE_DEFAULT) //채널 만들기
 manager.createNotificationChannel(ch_id) //체널 등록
//소리 설정

알림 객체 설정하는데  아이콘,시각,제목,내용

알림 띄우기- 시스템의 간접 호출
3개의 액션 builder.addAction()함수를 사용

사용자의 입력 받기 RemoteInput
빨간색 오류는 RemoteInput을 직접 상단에 import를 해서 androidx 사용
val remoteInput = RemoteInput.Builder("key_text_replay").run{ //원격 입력도 액션의 한 종류, RemoteInput에 사용자 입력을 받는 정보를 설정한 후 액션에 추가
 setLabel("답장") //만들 형태
build()  }  // RemoteInput을 직접 상단에 import를 해서 androidx 사용
->액션에 addRemoteInput(remoteInput).build로 수정
->PendingIntent.FLAG_IMMUTABLE을 FLAG_MUTABLE로 수정 변경할 수 있기 때문에
-> Reciver.kt파일도 수정
* */

/*인텐트 준비
*  val replyIntent = Intent(this,ReplyReceiver::class.java)
*  val repplyPendingIntent = PendingIntent.getBroadcast(this,30,PendingIntent.FLAG_IMMUTABLE) MUTABLE은 변경,IMMUTABLE은 변경x
* builder 등록
* */