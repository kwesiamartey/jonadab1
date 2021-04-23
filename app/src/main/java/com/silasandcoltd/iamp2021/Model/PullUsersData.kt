package com.silasandcoltd.iamp2021.Model

import com.google.gson.annotations.SerializedName

data class PullUsersData(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String
)
