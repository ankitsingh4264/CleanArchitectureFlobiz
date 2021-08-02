package com.example.cleanarchitecture

class getUserUseCase (private  val repository:Repo) {
    suspend fun invoke():Result<User>{
        return repository.getData()
    }
}