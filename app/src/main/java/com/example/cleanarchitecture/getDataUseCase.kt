package com.example.cleanarchitecture

import javax.inject.Inject

class getDataUseCase @Inject constructor(private  val repository:Repo) {
    suspend operator fun invoke():Result< Array<ResponseItem>>{

        return repository.getData()
    }
}