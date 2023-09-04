package com.rjial.githubprofile.ui

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivityDetailProfileBinding
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.ui.adapter.DetailStateAdapter

class DetailProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileBinding

    companion object {
        const val DETAIL_PROFILE = "detail_profile"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DETAIL_PROFILE, DetailUsernameResponse::class.java)
        } else {
            intent.getParcelableExtra<DetailUsernameResponse>(DETAIL_PROFILE)
        }
        if (extra == null) finish()
        supportActionBar?.title = "Detail User"
        val detailStateAdapter = DetailStateAdapter(this)
        binding.vpDetail.adapter = detailStateAdapter
        TabLayoutMediator(binding.tabs, binding.vpDetail) { tab, position ->
            tab.text = when(position) {
                0 -> "Followers"
                1 -> "Following"
                else -> ""
            }
        }.attach()
        Glide.with(this)
            .load(extra?.avatarUrl)
            .into(binding.imgDetailPhotoProfile)
        if(extra?.name  != null) {
            binding.tvDetailNameProfile.text = extra?.name
            binding.tvDetailUserNameProfile.text = extra?.login
        }else {
            binding.tvDetailNameProfile.text = extra?.login
        }
        requireNotNull(extra).apply {
            binding.tvDetailDescProfile.text = "${this.publicRepos} repos - ${this.followers} Followers - ${this.following} Following"
        }

    }
}