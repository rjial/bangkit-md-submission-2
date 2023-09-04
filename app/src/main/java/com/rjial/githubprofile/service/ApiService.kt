package com.rjial.githubprofile.service

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
                    .addHeader("Authorization", "token ${dotenv["GHP_TOKEN"] ?: ""}")
                    .build()
                it.proceed(requestHeaders)
            }
            val logClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val authClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(authClient)
                .client(logClient)
                .build()

            return retrofit.create(T::class.java)
        }
    }
}