package com.rjial.githubprofile.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rjial.githubprofile.databinding.ItemProfilesBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.ui.DetailProfileActivity

class FavoriteListAdapter: RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {
    private var listFav: java.util.ArrayList<UsernameFavoriteEntity> = java.util.ArrayList<UsernameFavoriteEntity>()

    fun updateList(newlistFav: java.util.ArrayList<UsernameFavoriteEntity>) {
        val diffResult = DiffUtil.calculateDiff(object: DiffUtil.Callback() {
            val oldlistFav = this@FavoriteListAdapter.listFav
            override fun getOldListSize(): Int = oldlistFav.size

            override fun getNewListSize(): Int = newlistFav.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldlistFav[oldItemPosition].id!! == newlistFav[newItemPosition].id!!
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldlistFav[oldItemPosition]
                val newItem = newlistFav[newItemPosition]
                return oldItem.login == newItem.login && oldItem.name == newItem.name && oldItem.avatarUrl == newItem.avatarUrl
            }

        })
        this.listFav.clear()
        this.listFav.addAll(newlistFav)
        diffResult.dispatchUpdatesTo(this)
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

    override fun getItemCount(): Int = listFav.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fav = listFav[position]
        holder.bind(fav)
    }

}