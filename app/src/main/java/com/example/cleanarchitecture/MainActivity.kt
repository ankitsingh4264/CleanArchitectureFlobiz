package com.example.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private val TAG = "ankit"
    lateinit var mainActivityViewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getUserData();
        observeUser();

    }

    private fun observeUser() {
        mainActivityViewModel.user.observe(this, Observer {result->
            when (result) {
                is Result.Sucess -> {
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


}