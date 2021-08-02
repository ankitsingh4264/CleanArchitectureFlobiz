package com.example.cleanarchitecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.Result.Sucess
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivityViewModel : ViewModel() {
    val user= MutableLiveData<Result<User>>()
//    val error= MutableLiveData<String>();

    fun getUserData(){
        user.postValue(Result.loading)
        val repoimpl=RepoImpl()
        viewModelScope.launch {
       val result = getUserUseCase(repoimpl).invoke()
         user.postValue(result)

     }

    }

}