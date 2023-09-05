package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val _apiService: SearchAPIInterface = ApiService.getService<SearchAPIInterface>()
    private var _detail: MutableLiveData<DetailUsernameResponse?> = MutableLiveData<DetailUsernameResponse?>()
    val detail: LiveData<DetailUsernameResponse?> = _detail

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetail(username: String) {
        _isLoading.value = true
        _apiService.getDetailUsername(username).enqueue(object: Callback<DetailUsernameResponse> {
            override fun onResponse(
                call: Call<DetailUsernameResponse>,
                response: Response<DetailUsernameResponse>
            ) {
                val body = response.body()
                _isLoading.value = false
                if (response.isSuccessful && body != null) {
                    _detail.value = body
                }
            }

            override fun onFailure(call: Call<DetailUsernameResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })

    }
}