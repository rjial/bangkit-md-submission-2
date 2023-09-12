package com.rjial.githubprofile.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjial.githubprofile.databinding.ActivityFavoriteBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel
import com.rjial.githubprofile.service.FavViewModelFactory
import com.rjial.githubprofile.ui.adapter.FavoriteListAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favObserver: Observer<List<UsernameFavoriteEntity>> = Observer {
        val arrayFav = ArrayList<UsernameFavoriteEntity>()
        arrayFav.clear()
        arrayFav.addAll(it)
        (binding.rvFavorite.adapter as FavoriteListAdapter).updateList(arrayFav)
    }
    private lateinit var favoriteViewModel: FavoriteViewModel
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
        val favFactory = FavViewModelFactory.getInstance(this.application)
        favoriteViewModel = ViewModelProvider(this@FavoriteActivity, favFactory)[FavoriteViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        doFavObserve(favoriteViewModel.getAll())
    }
    private fun doFavObserve(favLiveData: LiveData<List<UsernameFavoriteEntity>>) {
        if(favLiveData.hasActiveObservers()) {
            favLiveData.removeObserver(favObserver)
        }
        favLiveData.observe(this, favObserver)
    }
}