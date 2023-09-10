package com.rjial.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivityDetailProfileBinding
import com.rjial.githubprofile.model.db.entity.UsernameFavoriteEntity
import com.rjial.githubprofile.model.viewmodel.DetailViewModel
import com.rjial.githubprofile.model.viewmodel.FavoriteViewModel
import com.rjial.githubprofile.service.FavViewModelFactory
import com.rjial.githubprofile.ui.adapter.DetailStateAdapter

class DetailProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileBinding
    private lateinit var detailViewModel: DetailViewModel
    private val favoriteViewModel: FavoriteViewModel by viewModels<FavoriteViewModel> {
        FavViewModelFactory.getInstance(application)
    }
    private var isFav: Boolean = false
    private var favoriteEntity: UsernameFavoriteEntity? = null

    companion object {
        const val DETAIL_PROFILE = "detail_profile"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        setContentView(binding.root)

        val extra = intent.getStringExtra(DETAIL_PROFILE)
        if (extra == null) finish()
        supportActionBar?.hide()
        detailViewModel.getDetail(requireNotNull(extra))
        detailViewModel.isLoading.observe(this) {
            when(it) {
                true -> {
                    binding.pbDetail.visibility = View.VISIBLE

                    binding.tvDetailNameProfile.visibility = View.INVISIBLE
                    binding.imgDetailPhotoProfile.visibility = View.INVISIBLE
                    binding.tvDetailUserNameProfile.visibility = View.INVISIBLE
                    binding.tvDetailDescProfile.visibility = View.INVISIBLE
                    binding.fabFavorite.visibility = View.INVISIBLE
                }
                false -> {
                    binding.pbDetail.visibility = View.INVISIBLE

                    binding.tvDetailNameProfile.visibility = View.VISIBLE
                    binding.imgDetailPhotoProfile.visibility = View.VISIBLE
                    binding.tvDetailUserNameProfile.visibility = View.VISIBLE
                    binding.tvDetailDescProfile.visibility = View.VISIBLE
                    binding.fabFavorite.visibility = View.VISIBLE
                    binding.fabFavorite.setOnClickListener {
                        if (isFav) {
                            favoriteViewModel.deleteByLogin(extra)
                        } else {
                            Log.d("FAV_ENTITY", favoriteEntity.toString())
                            favoriteViewModel.insert(favoriteEntity!!)
                        }
                    }
                }
            }
        }
        detailViewModel.detail.observe(this) {
            if (it != null) {
                favoriteEntity = UsernameFavoriteEntity(null, it.login, it.avatarUrl, it.name)
                Log.d("FAV_ENTITY", favoriteEntity.toString())
                val detailStateAdapter = DetailStateAdapter(this, it)
                binding.vpDetail.adapter = detailStateAdapter
                TabLayoutMediator(binding.tabs, binding.vpDetail) { tab, position ->
                    tab.text = when(position) {
                        0 -> "Followers"
                        1 -> "Following"
                        else -> ""
                    }
                }.attach()
                Glide.with(this)
                    .load(it.avatarUrl)
                    .into(binding.imgDetailPhotoProfile)
                if(it.name  != null) {
                    binding.tvDetailNameProfile.text = it.name
                    binding.tvDetailUserNameProfile.text = it.login
                }else {
                    binding.tvDetailNameProfile.text = it.login
                }
                requireNotNull(it).apply {
                    binding.tvDetailDescProfile.text = "${this.publicRepos} repos - ${this.followers} Followers - ${this.following} Following"
                }
            }
        }
        favoriteViewModel.getFavByGithubLogin(extra).observe(this) {
            when(it != null) {
                true -> {
                    isFav = true
                    binding.fabFavorite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_24))
                }
                false -> {
                    isFav = false
                    binding.fabFavorite.setImageDrawable(getDrawable(R.drawable.baseline_favorite_border_24))
                }
            }
        }


    }
}