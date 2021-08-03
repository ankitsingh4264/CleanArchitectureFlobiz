package com.example.cleanarchitecture

import javax.inject.Inject

class getUserUseCase  constructor(private  val repository:Repo) {
    suspend operator fun invoke():Result<User>{
        return repository.getData()
    }
}