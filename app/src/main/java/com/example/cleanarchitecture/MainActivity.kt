package com.example.cleanarchitecture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.channels.consumesAll


@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnClick {
    private val TAG = "ankit"
     private val mainActivityViewModel:MainActivityViewModel by viewModels()
    @Inject
    lateinit var apiService:ApiService

    lateinit var list:ArrayList<ResponseItem>
    lateinit var adapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       list= ArrayList()
        adapter= RvAdapter(list,this,this)
        recyler_view.apply {
            adapter=this@MainActivity.adapter;
            layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        }

        mainActivityViewModel.getUserData();
        observeUser()
    }

    private fun observeUser() {
        mainActivityViewModel.user.observe(this, Observer {result->
            when (result) {
                is Result.Sucess -> {
                    list.addAll(result.data)
                    list.addAll(result.data)
                    list.addAll(result.data)
                    list.addAll(result.data)
                    adapter.notifyDataSetChanged()
                    Log.d(TAG, "observeUser: $result")
                }
                is Result.Error -> {
                    Log.d(TAG, "observeUser: $result")
                }
                Result.loading -> {
                    Log.d(TAG, "observeUser: loading $result")
                }
            }
        })
    }

    override fun itemClicked(position: Int) {
        val url = list[position].url
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


}