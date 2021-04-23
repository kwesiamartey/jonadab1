package com.silasandcoltd.iamp2021.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.silasandcoltd.iamp2021.Model.PullUsersData
import com.silasandcoltd.iamp2021.Model.PulldataResponse

class PulledUsersDiffUtil(
    val oldPullUsersData: List<PullUsersData>,
    val newPullUsersData: List<PullUsersData>
) :DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPullUsersData.size
    }

    override fun getNewListSize(): Int {
        return newPullUsersData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPullUsersData[oldItemPosition] === newPullUsersData[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPullUsersData[oldItemPosition].userId === newPullUsersData[newItemPosition].userId
                && oldPullUsersData[oldItemPosition].id === newPullUsersData[newItemPosition].id
                && oldPullUsersData[oldItemPosition].title === newPullUsersData[newItemPosition].title
                   oldPullUsersData[oldItemPosition].body === newPullUsersData[newItemPosition].body
    }

}