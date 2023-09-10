package com.rjial.githubprofile.model.db

import androidx.lifecycle.LiveData
import com.rjial.githubprofile.model.db.dao.FavoriteDao
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(private val favDao: FavoriteDao) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

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

    companion object {
        @Volatile
        private var instance: FavoriteRepository? = null

        fun getInstance(
            favDao: FavoriteDao
        ): FavoriteRepository {
            return instance ?: synchronized(this) {
                return instance ?: FavoriteRepository(favDao)
            }
        }
    }
}