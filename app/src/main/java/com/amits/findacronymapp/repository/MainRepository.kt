package com.amits.findacronymapp.repository

import com.amits.findacronymapp.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAcronymMeanings(searchInput:String) = retrofitService.getAcronymMeanings(searchInput)

}