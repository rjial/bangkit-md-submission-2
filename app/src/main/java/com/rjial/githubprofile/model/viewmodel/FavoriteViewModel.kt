package com.rjial.githubprofile.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.db.FavoriteRepository
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity

class FavoriteViewModel(private val repository: FavoriteRepository): ViewModel() {


    fun insert(favoriteEntity: UsernameFavoriteEntity) = repository.insertFav(favoriteEntity)
    fun update(favoriteEntity: UsernameFavoriteEntity) = repository.updateFav(favoriteEntity)
    fun delete(favoriteEntity: UsernameFavoriteEntity) = repository.deleteFav(favoriteEntity)
    fun deleteByLogin(login: String) = repository.deleteFavByLogin(login)
    fun getAll(): LiveData<List<UsernameFavoriteEntity>> = repository.getAllFav()

    fun getFavByGithubLogin(loginGithub: String): LiveData<UsernameFavoriteEntity> = repository.getFavByGithubLogin(loginGithub)


}