package com.example.cleanarchitecture

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoImpl @Inject constructor(
  private val apiService: ApiService
) : Repo {
    override suspend fun getData(): Result<Array<ResponseItem>> {

        return try {

                val data = apiService.getTrendData2()
                Log.d("ankit", "getData: $data")


            return   Result.Sucess(data)


        }catch(exception:Exception) {
            Result.Error(exception.message!!)
        }
    }





}