package com.example.cleanarchitecture

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
  private val getDataUseCase: getDataUseCase) : ViewModel() {

    val user= MutableLiveData<Result<Array<ResponseItem>>>()
    fun getUserData(){
        user.postValue(Result.loading)


        viewModelScope.launch {
            val result = getDataUseCase()
            user.postValue(result)

     }

    }

}