package com.example.cleanarchitecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(private val repoImpl: RepoImpl) : ViewModel() {
    val user= MutableLiveData<Result<User>>()
//    val error= MutableLiveData<String>();

    fun getUserData(){
        user.postValue(Result.loading)


        viewModelScope.launch {
            val result = getUserUseCase(repoImpl).invoke()
            user.postValue(result)

     }

    }

}