package com.rjial.githubprofile.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjial.githubprofile.databinding.ItemProfilesBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.ui.DetailProfileActivity

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
        fun bind(fav: UsernameFavoriteEntity) {
            if (fav.name != null) {
                binding.tvNameProfile.text = fav.name
                binding.tvUsernameProfile.text = fav.login
            } else {
                binding.tvNameProfile.text = fav.login
            }
            binding.root.setOnClickListener {
                try {
                    val intent = Intent(it.context, DetailProfileActivity::class.java)
                    intent.putExtra(DetailProfileActivity.DETAIL_PROFILE, fav.login)
                    it.context.startActivity(intent)
                } catch(e: Exception) {
                    Log.e("PARCEL_ERROR", e.message!!)
                    e.printStackTrace()
                }
            }

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