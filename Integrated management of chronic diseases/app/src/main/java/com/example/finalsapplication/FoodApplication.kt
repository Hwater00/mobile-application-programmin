package com.example.finalsapplication

import android.app.Application
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit

class FoodApplication : Application() {
    companion object{
        var networkServiceXml : NetworkServiceXml

        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val retrofitXml : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()
        init{
            networkServiceXml = retrofitXml.create(NetworkServiceXml::class.java)
        }


    }
}