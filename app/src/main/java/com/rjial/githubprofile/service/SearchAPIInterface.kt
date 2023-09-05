package com.rjial.githubprofile.service

import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.model.response.SearchGithubResponse
import com.rjial.githubprofile.model.response.UsernameFollowersResponseItem
import com.rjial.githubprofile.model.response.UsernameFollowingResponse
import com.rjial.githubprofile.model.response.UsernameFollowingResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchAPIInterface {
    @GET("search/users")
    fun getSearchResult(@Query("q") keyword: String): Call<SearchGithubResponse>

    @GET("users/{username}/following")
    fun getUsernameFollowing(@Path("username") username: String): Call<List<UsernameFollowingResponseItem>>

    @GET("users/{username}/followers")
    fun getUsernameFollowers(@Path("username") username: String): Call<List<UsernameFollowersResponseItem>>

    @GET("users/{username}")
    fun getDetailUsername(@Path("username") username: String): Call<DetailUsernameResponse>
}