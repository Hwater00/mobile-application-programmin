package com.example.finalsapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServiceXml {
    @GET("1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1")
    fun getXmlList(
        @Query("desc_kor") name:String,
        @Query("pageNo") page:Int,
        @Query("numOfRows") pageSize:Int,
        @Query("bgn_year") bgn:Int,
        @Query("animal_plant") plant:String?,
        @Query("serviceKey") apiKey:String?,
        @Query("type") type:String?,
    ) : Call<responseInfo>
}