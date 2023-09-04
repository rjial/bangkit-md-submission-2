package com.rjial.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivityDetailProfileBinding
import com.rjial.githubprofile.ui.adapter.DetailStateAdapter

class DetailProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    }
}