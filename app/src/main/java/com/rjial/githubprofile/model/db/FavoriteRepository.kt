package com.rjial.githubprofile.model.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.rjial.githubprofile.model.db.dao.FavoriteDao
import com.rjial.githubprofile.model.db.database.FavoriteRoomDatabase
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private var favDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getInstance(application)
        favDao = db.favoriteDao()
    }

    fun getAllFav(): LiveData<List<UsernameFavoriteEntity>> = favDao.getAllFavorite()

    fun getFavByGithubLogin(idLogin: String): LiveData<UsernameFavoriteEntity> = favDao.getFavByGithubLogin(idLogin)
    fun insertFav(fav: UsernameFavoriteEntity) {
        executorService.execute {
            favDao.insertFavorite(fav)
        }
    }
    fun updateFav(fav: UsernameFavoriteEntity) {
        executorService.execute {
            favDao.updateFavorite(fav)
        }
    }
    fun deleteFav(fav: UsernameFavoriteEntity) {
        executorService.execute {
            favDao.deleteFavorite(fav)
        }
    }
    fun deleteFavByLogin(login: String) {
        executorService.execute {
            favDao.deleteFavoriteByLogin(login)
        }
    }
}