package com.rjial.githubprofile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjial.githubprofile.databinding.ItemProfilesBinding
import com.rjial.githubprofile.model.response.ItemsItem
import com.rjial.githubprofile.model.response.SearchGithubResponse

class SearchGithubAdapter(val listProfile: List<ItemsItem>): RecyclerView.Adapter<SearchGithubAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemProfilesBinding): RecyclerView.ViewHolder(binding.root) {
        fun applyProfile(result: ItemsItem) {
            binding.tvNameProfile.text = result.login
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