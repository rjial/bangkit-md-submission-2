package com.rjial.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rjial.githubprofile.databinding.ActivityMainBinding
import com.rjial.githubprofile.model.viewmodel.SearchViewModel
import com.rjial.githubprofile.ui.adapter.SearchGithubAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchViewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvProfiles.layoutManager = layoutManager
        val decorDividerItem = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvProfiles.addItemDecoration(decorDividerItem)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView
            .editText
            .setOnEditorActionListener { v, actionId, event ->
                binding.searchBar.text = binding.searchView.text
                binding.searchView.hide()
                searchViewModel.searchProfile(v.text.toString())
                false
            }
        searchViewModel.searchResult.observe(this) {
            if(it != null) {
                binding.rvProfiles.adapter= SearchGithubAdapter(it)
            }
        }
        searchViewModel.isLoading.observe(this) {
                when(it) {
                    true -> {
                        binding.pbSearch.visibility = View.VISIBLE
                        binding.rvProfiles.visibility = View.INVISIBLE
                    }
                    false -> {
                        binding.pbSearch.visibility = View.INVISIBLE
                        binding.rvProfiles.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.pbSearch.visibility = View.INVISIBLE
                        binding.rvProfiles.visibility = View.INVISIBLE
                    }
                }
        }
    }
}