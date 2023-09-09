package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rjial.githubprofile.datastore.SettingsDatastore
import kotlinx.coroutines.launch

class SettingViewModel(private val setPref: SettingsDatastore): ViewModel() {
    var isDarkMode: LiveData<Boolean> = setPref.getThemeSetting().asLiveData()

    fun setDarkMode(value: Boolean) {
        viewModelScope.launch {
            setPref.saveThemeSetting(value)
        }
    }

}