package com.rjial.githubprofile.model.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.db.FavoriteRepository
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity

class FavoriteViewModel(application: Application): ViewModel() {

    private val repository = FavoriteRepository(application)

    fun insert(favoriteEntity: UsernameFavoriteEntity) = repository.insertFav(favoriteEntity)
    fun update(favoriteEntity: UsernameFavoriteEntity) = repository.updateFav(favoriteEntity)
    fun delete(favoriteEntity: UsernameFavoriteEntity) = repository.deleteFav(favoriteEntity)
    fun getAll(): LiveData<List<UsernameFavoriteEntity>> = repository.getAllFav()

}