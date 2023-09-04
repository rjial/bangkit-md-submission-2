package com.rjial.githubprofile.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjial.githubprofile.databinding.ItemProfilesBinding
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.model.response.ItemsItem
import com.rjial.githubprofile.model.response.SearchGithubResponse
import com.rjial.githubprofile.model.response.UsernameFollowingResponse
import com.rjial.githubprofile.model.response.UsernameFollowingResponseItem
import com.rjial.githubprofile.service.ApiService
import com.rjial.githubprofile.service.SearchAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchGithubAdapter(val listProfile: List<ItemsItem>): RecyclerView.Adapter<SearchGithubAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProfilesBinding): RecyclerView.ViewHolder(binding.root) {
        private val apiService = ApiService.getService<SearchAPIInterface>()
        fun applyProfile(result: ItemsItem) {
            apiService.getDetailUsername(result.login)
                .enqueue(object: Callback<DetailUsernameResponse> {
                    override fun onResponse(
                        call: Call<DetailUsernameResponse>,
                        response: Response<DetailUsernameResponse>
                    ) {
                        val body = response.body()
                        if (response.isSuccessful && body != null) {
                            binding.tvNameProfile.text = if (body.name != null) "${body.name} (${body.login})" else "${body.login}"
                            binding.tvUsernameProfile.text = "${body.publicRepos} Public Repos - ${body.followers} Followers - ${body.following} Following"
                        } else {
                            binding.tvNameProfile.text = result.login
                            binding.tvUsernameProfile.text = "Failed fetch detail"
                        }
                    }

                    override fun onFailure(call: Call<DetailUsernameResponse>, t: Throwable) {
                        binding.tvUsernameProfile.text = "Failed fetch detail"
                    }

                })
            Glide.with(binding.root.context)
                .load(result.avatarUrl)
                .into(binding.imgPhotoProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProfile.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.applyProfile(listProfile[position])
    }
}