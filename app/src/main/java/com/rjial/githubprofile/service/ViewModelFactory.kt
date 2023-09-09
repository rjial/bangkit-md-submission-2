package com.rjial.githubprofile.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rjial.githubprofile.datastore.SettingsDatastore
import com.rjial.githubprofile.model.viewmodel.SettingViewModel

class ViewModelFactory(private val setPref: SettingsDatastore): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(setPref) as T
        }
        throw Exception("Unknown View Model : ${modelClass.name}")
    }
}