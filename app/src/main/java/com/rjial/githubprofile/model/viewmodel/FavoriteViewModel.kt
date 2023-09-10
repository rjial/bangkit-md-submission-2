package com.rjial.githubprofile.model.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rjial.githubprofile.model.db.FavoriteRepository
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(application: Application): ViewModel() {

    private val repository = FavoriteRepository(application)

    fun insert(favoriteEntity: UsernameFavoriteEntity) = repository.insertFav(favoriteEntity)
    fun update(favoriteEntity: UsernameFavoriteEntity) = repository.updateFav(favoriteEntity)
    fun delete(favoriteEntity: UsernameFavoriteEntity) = repository.deleteFav(favoriteEntity)
    fun deleteByLogin(login: String) = repository.deleteFavByLogin(login)
    fun getAll(): LiveData<List<UsernameFavoriteEntity>> = repository.getAllFav()

    fun getFavByGithubLogin(loginGithub: String): LiveData<UsernameFavoriteEntity> = repository.getFavByGithubLogin(loginGithub)


}