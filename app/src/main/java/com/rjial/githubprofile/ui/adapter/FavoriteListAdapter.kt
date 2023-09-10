package com.rjial.githubprofile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjial.githubprofile.databinding.ItemProfilesBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteListAdapter: ListAdapter<UsernameFavoriteEntity, FavoriteListAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<UsernameFavoriteEntity>() {
            override fun areItemsTheSame(
                oldItem: UsernameFavoriteEntity,
                newItem: UsernameFavoriteEntity
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: UsernameFavoriteEntity,
                newItem: UsernameFavoriteEntity
            ): Boolean = oldItem == newItem

        }
    }
    class ViewHolder(val binding: ItemProfilesBinding): RecyclerView.ViewHolder(binding.root) {
        private val apiService = ApiService.getService<SearchAPIInterface>()
        fun bind(fav: UsernameFavoriteEntity) {
            binding.tvNameProfile.text = fav.login

            apiService.getDetailUsername(fav.login)
                .enqueue(object: Callback<DetailUsernameResponse> {
                    override fun onResponse(
                        call: Call<DetailUsernameResponse>,
                        response: Response<DetailUsernameResponse>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            if (body.name != null) {
                                binding.tvNameProfile.text = body.name
                                binding.tvUsernameProfile.text = body.login
                            } else {
                                binding.tvNameProfile.text = body.login
                            }

                        } else {
                            binding.tvNameProfile.text = fav.login
                            binding.tvUsernameProfile.text = "Failed fetch detail"
                        }
                    }

                    override fun onFailure(call: Call<DetailUsernameResponse>, t: Throwable) {
                        binding.tvUsernameProfile.text = "Failed fetch detail"
                    }

                })
            Glide.with(binding.root.context)
                .load(fav.avatarUrl)
                .into(binding.imgPhotoProfile)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fav = getItem(position)
        holder.bind(fav)
    }

}