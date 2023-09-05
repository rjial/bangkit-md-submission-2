package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.response.UsernameFollowersResponseItem
import com.rjial.githubprofile.model.response.UsernameFollowingResponseItem
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsernameFollowingViewModel: ViewModel() {
    private var _listFollowing: MutableLiveData<List<UsernameFollowingResponseItem>?> = MutableLiveData<List<UsernameFollowingResponseItem>?>()
    val listFollowing: LiveData<List<UsernameFollowingResponseItem>?> = _listFollowing
    private val apiService: SearchAPIInterface = ApiService.getService<SearchAPIInterface>()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun postFollowing(username: String)  {
        _isLoading.value = true
        apiService.getUsernameFollowing(username).enqueue(object:
            Callback<List<UsernameFollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<UsernameFollowingResponseItem>>,
                response: Response<List<UsernameFollowingResponseItem>>
            ) {
                val body = response.body()
                _isLoading.value = false
                if (response.isSuccessful && body != null) {
                    _listFollowing.value = body
                }
            }

            override fun onFailure(call: Call<List<UsernameFollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}