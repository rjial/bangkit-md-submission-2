package com.rjial.githubprofile.model.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UsernameFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "login")
    var login: String,
    @ColumnInfo(name = "id_github")
    var idGithub: Int,
    @ColumnInfo(name = "public_repos")
    var publicRepos: Int,
    @ColumnInfo(name = "email")
    var email: String? = null,
    @ColumnInfo(name = "followers")
    var followers: Int,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String,
    @ColumnInfo(name = "following")
    var following: Int,
    @ColumnInfo(name = "name")
    var name: String,
) : Parcelable
