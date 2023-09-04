package com.rjial.githubprofile.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUsernameResponse(

	@field:SerializedName("gists_url")
	val gistsUrl: String,

	@field:SerializedName("repos_url")
	val reposUrl: String,

	@field:SerializedName("two_factor_authentication")
	val twoFactorAuthentication: Boolean,

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("twitter_username")
	val twitterUsername: String?,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("blog")
	val blog: String,

	@field:SerializedName("private_gists")
	val privateGists: Int,

	@field:SerializedName("total_private_repos")
	val totalPrivateRepos: Int,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean,

	@field:SerializedName("disk_usage")
	val diskUsage: Int,

	@field:SerializedName("collaborators")
	val collaborators: Int,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("owned_private_repos")
	val ownedPrivateRepos: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("gravatar_id")
	val gravatarId: String,

	@field:SerializedName("plan")
	val plan: Plan? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String,

	@field:SerializedName("hireable")
	val hireable: Boolean? = null,

	@field:SerializedName("starred_url")
	val starredUrl: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("public_gists")
	val publicGists: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("events_url")
	val eventsUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String
) : Parcelable

@Parcelize
data class Plan(

	@field:SerializedName("private_repos")
	val privateRepos: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("collaborators")
	val collaborators: Int,

	@field:SerializedName("space")
	val space: Int
) : Parcelable
