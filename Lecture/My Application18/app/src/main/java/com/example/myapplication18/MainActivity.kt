package com.example.myapplication18

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import com.example.myapplication18.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //permission 사용자 허가
        checkPermissions()
        //폰 정보
        binding.tv.text = getPhoneInfo()

        //연결 정보-어떤 상태로 연결, 왜 연결되지 않는데
        binding.tv.text = binding.tv.text.toString() +getConnectivity()

    }

    private fun checkPermissions() {
        val REQUEST_CODE = 1001
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_PHONE_NUMBERS), //Manifest android로 선택
                REQUEST_CODE
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_PHONE_NUMBERS), REQUEST_CODE)
        }
    }

    private fun getPhoneInfo(): String {
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_NUMBERS) ==PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED){
            val manager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val countryIso = manager.networkCountryIso
            val operatorName = manager.networkOperatorName
            val phoneNum = manager.line1Number
            return "countryIso: $countryIso \n  operatorName: $operatorName \n phomeNum: $phoneNum"
        }

        return ""
    }

    private fun getConnectivity() : String{
        val manager= getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val nw = manager.activeNetwork
            if(nw == null ) return "activeNetwork = null"
            val actNw = manager.getNetworkCapabilities(nw)
            if(actNw == null ) return "activeNetwork - Capablities = null"
            if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return "cellular available"
            else if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return  "wifi available"
            else return "available"
        }else{
            if(manager.activeNetworkInfo!!.isConnected) return "연결됬음"
            return "연결 안 됬어"
        }
        return ""
    }
}

