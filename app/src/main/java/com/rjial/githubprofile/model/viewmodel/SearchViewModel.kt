package com.rjial.githubprofile.model.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.response.ItemsItem
import com.rjial.githubprofile.model.response.SearchGithubResponse
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private var apiService: SearchAPIInterface = ApiService.getService<SearchAPIInterface>()

    private var _searchText: MutableLiveData<String> = MutableLiveData<String>()
    var searchText: LiveData<String> = _searchText

    private var _searchResult: MutableLiveData<List<ItemsItem>> = MutableLiveData<List<ItemsItem>>()
    var searchResult: LiveData<List<ItemsItem>> = _searchResult

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    fun searchProfile(value: String) {
        _isLoading.value = true
        _searchText.value = value
        apiService.getSearchResult(value).enqueue(object: Callback<SearchGithubResponse>{
            override fun onResponse(
                call: Call<SearchGithubResponse>,
                response: Response<SearchGithubResponse>
            ) {
                _isLoading.value = false
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    _searchResult.value = body.items
                } else {
                    Log.e("ERROR", response.message())
                }
            }

            override fun onFailure(call: Call<SearchGithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("ERROR", t.message!!)
            }

        })

    }
}