package com.rjial.githubprofile.service

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel

class FavViewModelFactory(private val application: Application): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FavViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavViewModelFactory {
            return instance ?: synchronized(this) {
                return instance ?: FavViewModelFactory(application)
            }
        }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application) as T
        }
        throw Exception("Unknown class : ${modelClass.name}")
    }
}