package com.example.cleanarchitecture

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET("/repositories")
    suspend fun getTrendData():Array<ResponseItem>

    @GET()
    suspend fun getTrendData2(@Url string: String="https://private-anon-a5e068860a-githubtrendingapi.apiary-mock.com/repositories"):Array<ResponseItem>


}