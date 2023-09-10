package com.rjial.githubprofile.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsernameFavoriteModel(
    val login: String,
    val id: Int,
    val publicRepos: Int,
    val email: String? = null,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String? = null,
) : Parcelable
