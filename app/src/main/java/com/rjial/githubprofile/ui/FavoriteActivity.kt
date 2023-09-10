package com.rjial.githubprofile.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjial.githubprofile.databinding.ActivityFavoriteBinding
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel
import com.rjial.githubprofile.service.FavViewModelFactory
import com.rjial.githubprofile.ui.adapter.FavoriteListAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels<FavoriteViewModel> {
        FavViewModelFactory.getInstance(this)
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
        viewModel.getAll().observe(this) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            binding.rvFavorite.adapter = adapter
        }

    }
}