package com.rjial.githubprofile.service

import android.util.Log
import io.github.cdimascio.dotenv.dotenv
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        inline fun <reified T> getService(): T {
            val dotenv = dotenv {
                directory = "/assets"
                filename = "env"
            }
            val authInterceptor = Interceptor {
                val req = it.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer ${dotenv["GHP_TOKEN"] ?: ""}")
                    .build()
                it.proceed(requestHeaders)
            }
            val interceptor = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptor)
                .build()

            return retrofit.create(T::class.java)
        }
    }
}