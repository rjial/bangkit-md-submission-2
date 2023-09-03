package com.rjial.githubprofile.service

import com.rjial.githubprofile.model.response.SearchGithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPIInterface {
    @GET("search/users")
    fun getSearchResult(@Query("q") keyword: String): Call<SearchGithubResponse>
}