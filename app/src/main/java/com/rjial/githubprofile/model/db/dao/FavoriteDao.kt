package com.rjial.githubprofile.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(fav: UsernameFavoriteEntity)

    @Update()
    fun updateFavorite(fav: UsernameFavoriteEntity)

    @Delete()
    fun deleteFavorite(fav: UsernameFavoriteEntity)

    @Query("SELECT * from usernamefavoriteentity ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<UsernameFavoriteEntity>>

    @Query("SELECT * from usernamefavoriteentity WHERE id_github = :idGithub")
    fun getFavByGithubId(idGithub: Int): LiveData<UsernameFavoriteEntity>
}