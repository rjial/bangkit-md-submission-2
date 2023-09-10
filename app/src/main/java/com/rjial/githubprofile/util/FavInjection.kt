package com.rjial.githubprofile.util

import android.content.Context
import com.rjial.githubprofile.model.db.FavoriteRepository
import com.rjial.githubprofile.model.db.database.FavoriteRoomDatabase

object FavInjection {
    fun provideRepository(context: Context): FavoriteRepository {
        val db = FavoriteRoomDatabase.getInstance(context)
        val dao = db.favoriteDao()
        return FavoriteRepository.getInstance(dao)
    }
}