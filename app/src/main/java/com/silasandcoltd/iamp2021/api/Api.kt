package com.silasandcoltd.iamp2021.api


import com.silasandcoltd.iamp2021.Model.PullUsersData
import com.silasandcoltd.iamp2021.Model.PulldataResponse
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("posts")
    fun getPost() : Call<List<PulldataResponse>>

    @GET("users")
    fun getUsers() : Call<List<PullUsersData>>
}