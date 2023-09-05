package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.response.UsernameFollowersResponseItem
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsernameFollowersViewModel: ViewModel() {
    private var _listFollowers: MutableLiveData<List<UsernameFollowersResponseItem>?> = MutableLiveData<List<UsernameFollowersResponseItem>?>()
    val listFollowers: LiveData<List<UsernameFollowersResponseItem>?> = _listFollowers
    private val apiService: SearchAPIInterface = ApiService.getService<SearchAPIInterface>()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun postFollowers(username: String)  {
        _isLoading.value = true
        apiService.getUsernameFollowers(username).enqueue(object: Callback<List<UsernameFollowersResponseItem>>  {
            override fun onResponse(
                call: Call<List<UsernameFollowersResponseItem>>,
                response: Response<List<UsernameFollowersResponseItem>>
            ) {
                val body = response.body()
                _isLoading.value = false
                if (response.isSuccessful && body != null) {
                    _listFollowers.value = body
                }
            }

            override fun onFailure(call: Call<List<UsernameFollowersResponseItem>>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}