package com.rjial.githubprofile.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjial.githubprofile.databinding.ActivityFavoriteBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel
import com.rjial.githubprofile.service.FavViewModelFactory
import com.rjial.githubprofile.ui.adapter.FavoriteListAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels<FavoriteViewModel> {
        FavViewModelFactory.getInstance(application)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = FavoriteListAdapter()
        binding.rvFavorite.layoutManager = layoutManager
        binding.rvFavorite.addItemDecoration(dividerItemDecoration)
        binding.rvFavorite.adapter = adapter
        viewModel.getAll().observe(this) {users ->
            val items = arrayListOf<UsernameFavoriteEntity>()
            users.map {
                val item = UsernameFavoriteEntity(it.id, it.login, it.avatarUrl, it.name)
                items.add(item)
            }
            adapter.updateList(items)
            adapter.notifyDataSetChanged()
        }

    }
}