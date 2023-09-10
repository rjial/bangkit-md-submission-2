package com.rjial.githubprofile.service

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rjial.githubprofile.model.db.FavoriteRepository
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel
import com.rjial.githubprofile.util.FavInjection

class FavViewModelFactory(private val repository: FavoriteRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FavViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): FavViewModelFactory {
            return instance ?: synchronized(this) {
                return instance ?: FavViewModelFactory(FavInjection.provideRepository(context))
            }
        }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw Exception("Unknown class : ${modelClass.name}")
    }
}