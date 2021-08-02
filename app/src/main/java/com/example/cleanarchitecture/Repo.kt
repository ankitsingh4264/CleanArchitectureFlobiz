package com.example.cleanarchitecture

interface Repo {
    suspend fun getData():Result<User>
}