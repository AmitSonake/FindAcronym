package com.amits.findacronymapp.api

import com.amits.findacronymapp.models.Acronym
import com.amits.findacronymapp.utility.ApiConstant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService{
    @GET("dictionary.py")
    suspend fun getAcronymMeanings(@Query("sf") sf :String) : Response<List<Acronym>>
    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}