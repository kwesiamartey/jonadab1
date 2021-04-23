package com.silasandcoltd.iamp2021.api.singleton

import com.silasandcoltd.iamp2021.api.Api
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Singleton {

    val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
    }
    val retrofitApi:Api by lazy {
        retrofit.build().create(Api::class.java)
    }

}