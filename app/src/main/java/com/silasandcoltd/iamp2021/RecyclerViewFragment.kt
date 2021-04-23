package com.silasandcoltd.iamp2021

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silasandcoltd.iamp2021.Model.PullUsersData
import com.silasandcoltd.iamp2021.Model.PulldataResponse
import com.silasandcoltd.iamp2021.api.singleton.Singleton
import com.silasandcoltd.iamp2021.databinding.FragmentRecyclerViewBinding
import com.silasandcoltd.iamp2021.recyclerview.DataRecyclerView
import com.silasandcoltd.iamp2021.recyclerview.UsersDataRecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerViewFragment : Fragment() {

    val adapter by lazy {
        DataRecyclerView()
    }

    val adapters by lazy {
        UsersDataRecyclerView()
    }

    lateinit var _bind:FragmentRecyclerViewBinding
    val bind get() = _bind

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentRecyclerViewBinding.inflate(layoutInflater)

        GlobalScope.launch(Dispatchers.IO) {
            Singleton.retrofitApi.getPost().enqueue(object : Callback<List<PulldataResponse>> {
                override fun onResponse(
                    call: Call<List<PulldataResponse>>,
                    response: Response<List<PulldataResponse>>
                ) {
                    var mResponse = response.body()

                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            mResponse.let {
                                /*Toast.makeText(
                                    this@RecyclerViewFragment,
                                    it.toString(),
                                    Toast.LENGTH_LONG
                                ).show()*/
                                val recyclerview = _bind.recyclerview
                                val layoutManager = LinearLayoutManager(requireActivity())
                                layoutManager.orientation = LinearLayoutManager.VERTICAL
                                recyclerview.layoutManager = layoutManager

                                val adapter = adapter
                                adapter.setData(it!!)
                                recyclerview.adapter = adapter
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<PulldataResponse>>, t: Throwable) {
                    Log.i("TAG", t.message.toString())
                }
            })

            Singleton.retrofitApi.getUsers().enqueue(object : Callback<List<PullUsersData>> {
                override fun onResponse(
                    call: Call<List<PullUsersData>>,
                    response: Response<List<PullUsersData>>
                ) {
                    var mResponse = response.body()

                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            mResponse.let {
                                /*Toast.makeText(
                                    this@RecyclerViewFragment,
                                    it.toString(),
                                    Toast.LENGTH_LONG
                                ).show()*/
                                val recyclerview = _bind.recyclerview
                                val layoutManager = LinearLayoutManager(requireActivity())
                                layoutManager.orientation = LinearLayoutManager.VERTICAL
                                recyclerview.layoutManager = layoutManager

                                val adapters = adapters
                                adapters.setData(it!!)
                                recyclerview.adapter = adapters
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<PullUsersData>>, t: Throwable) {
                    Log.i("TAG", t.message.toString())
                }
            })
        }

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "List"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           android.R.id.home ->{findNavController().navigate(R.id.signInFragment)}
        }
        return super.onOptionsItemSelected(item)
    }
}
