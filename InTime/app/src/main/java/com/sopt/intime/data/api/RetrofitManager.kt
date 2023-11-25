package com.sopt.intime.data.api


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.intime.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitManager {
    const val BASE_URL = BuildConfig.BASE_URL

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    inline fun <reified T> create(): T =
        retrofit.create<T>(T::class.java)
}

object RetrofitServicePool {
    val todoService = RetrofitManager.create<TodoAPI>()
}