package com.silasandcoltd.iamp2021.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.silasandcoltd.iamp2021.Model.PullUsersData
import com.silasandcoltd.iamp2021.Model.PulldataResponse
import com.silasandcoltd.iamp2021.databinding.ActivityListViewBinding
import com.silasandcoltd.iamp2021.diffUtils.PulledDiffUtil
import com.silasandcoltd.iamp2021.diffUtils.PulledUsersDiffUtil

class UsersDataRecyclerView : RecyclerView.Adapter<UsersDataRecyclerView.MyViewHolder>() {

    var dataList = emptyList<PullUsersData>()

    inner class MyViewHolder(val binding : ActivityListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ActivityListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hold = dataList[position]
        holder.binding.title.text = hold.title
        holder.binding.body.text = hold.body
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    fun setData(pullUsersData: List<PullUsersData>){
        val pulledDiffUtil = PulledUsersDiffUtil(dataList, pullUsersData)
        val pulledDataDiffUtilResult = DiffUtil.calculateDiff(pulledDiffUtil)
        dataList = pullUsersData
        pulledDataDiffUtilResult.dispatchUpdatesTo(this)
    }
}