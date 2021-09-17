package com.example.cleanarchitecture

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    val BASE_URL="https://private-anon-43c1a80f6d-githubtrendingapi.apiary-mock.com/"

    @Provides
    fun provideRepo(repoImpl: RepoImpl) : Repo{
        return repoImpl
    }

    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    fun provideOkHttpClient(@ApplicationContext context:Context):OkHttpClient{
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + (30*60).toLong()).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 7).toLong()).build()
                chain.proceed(request)
            }
            .build()

        return okHttpClient
    }
    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}