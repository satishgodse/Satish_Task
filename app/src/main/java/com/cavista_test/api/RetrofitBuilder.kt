package com.cavista_test.api

import com.cavista_test.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL_TEST = "https://api.imgur.com/3/gallery/"
    private const val BASE_URL = "https://api.imgur.com/3/gallery/"

    private fun getRetrofit(): Retrofit {
        if(BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().writeTimeout(5L, TimeUnit.MINUTES)
                    .readTimeout(5L, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL_TEST)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        } else {
            val client = OkHttpClient.Builder().writeTimeout(5L, TimeUnit.MINUTES)
                    .readTimeout(5L, TimeUnit.MINUTES)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}