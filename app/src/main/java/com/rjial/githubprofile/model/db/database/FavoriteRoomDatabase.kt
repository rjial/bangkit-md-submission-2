package com.rjial.githubprofile.model.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rjial.githubprofile.model.db.dao.FavoriteDao
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity

@Database(entities = [UsernameFavoriteEntity::class], version = 1)
abstract class FavoriteRoomDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): FavoriteRoomDatabase {
            return instance ?: synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteRoomDatabase::class.java,
                    "favorite_database"
                ).build()
            }
        }
    }
}