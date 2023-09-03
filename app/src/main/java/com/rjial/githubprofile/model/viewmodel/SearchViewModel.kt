package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {
    private var _searchText: MutableLiveData<String> = MutableLiveData<String>()
    var searchText: LiveData<String> = _searchText

    fun searchProfile(value: String) {
        _searchText.value = value
    }
}