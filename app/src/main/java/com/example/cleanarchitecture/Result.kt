package com.example.cleanarchitecture

sealed class Result <out T> {
   data class Sucess< out T>(val data:T) : Result<T>()
    data class Error(val message:String) :Result<Nothing>()
    object loading : Result<Nothing>()

}