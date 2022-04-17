package com.example.notification

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ReplyReceiver : BroadcastReceiver() {
    /*
    *
    * */
    override fun onReceive(context: Context, intent: Intent) {
        //원격 입력 시 사용자가 입력한 문자열인 텍스트를 전달받음
        //전달받는 것은 main에서 remoteInput을 만들때 resultKey로 사용한
        val replyTxT = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_replay")
        Log.d("app","$replyTxT")


        //메인에서 선언했던 manger을 메인이 아니기에 context를 붙여서  context.getSystemService
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager //notification 매니저 가져오기
        manager.cancel(11)
    }
}

