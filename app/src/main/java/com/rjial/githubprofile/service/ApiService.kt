package com.rjial.githubprofile.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        inline fun <reified T> getService(): T {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(T::class.java)
        }
    }
}