package com.silasandcoltd.iamp2021.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.silasandcoltd.iamp2021.Model.PulldataResponse

class PulledDiffUtil(
    val oldPulldataResponse: List<PulldataResponse>,
    val newPulldataResponse: List<PulldataResponse>
) :DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPulldataResponse.size
    }

    override fun getNewListSize(): Int {
        return newPulldataResponse.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPulldataResponse[oldItemPosition] === newPulldataResponse[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPulldataResponse[oldItemPosition].userId === newPulldataResponse[newItemPosition].userId
                && oldPulldataResponse[oldItemPosition].id === newPulldataResponse[newItemPosition].id
                && oldPulldataResponse[oldItemPosition].title === newPulldataResponse[newItemPosition].title
                   oldPulldataResponse[oldItemPosition].body === newPulldataResponse[newItemPosition].body
    }

}