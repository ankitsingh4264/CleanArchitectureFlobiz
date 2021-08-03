package com.example.cleanarchitecture

import kotlinx.coroutines.delay
import javax.inject.Inject

class RepoImpl @Inject constructor() : Repo {
    override suspend fun getData(): Result<User> {

        return try {

            val data = getUser();


            Result.Sucess(data);

        }catch(exception:Exception) {
            Result.Error(exception.message!!)
        }
    }




    private suspend fun getUser():User{
        //used to get fake error while fetching data

//        getUserGoterror()
        delay(3000);
        return User("7","ankit")
    }
    private  suspend fun getUserGoterror():Exception{
        delay(2000)
        throw Exception("ss")
    }
}