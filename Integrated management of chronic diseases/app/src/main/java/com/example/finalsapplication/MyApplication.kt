package com.example.finalsapplication

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.kakao.sdk.common.KakaoSdk

class MyApplication: MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null
        //

        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        //
        fun checkAuth(): Boolean{
            var currentUser = auth.currentUser
            return currentUser?.let{
                email = currentUser.email
                currentUser.isEmailVerified  // 인증되었다면 true, 아니라면 false 리턴
            }?: let{
                false
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        // 카카오 sdk 초기화
        KakaoSdk.init(	this,"8113253b3319f2368527df8601a8705a")
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}