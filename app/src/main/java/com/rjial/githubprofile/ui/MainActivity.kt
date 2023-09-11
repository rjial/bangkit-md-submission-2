package com.rjial.githubprofile.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivityMainBinding
import com.rjial.githubprofile.model.viewmodel.SearchViewModel
import com.rjial.githubprofile.ui.adapter.SearchGithubAdapter
import com.rjial.githubprofile.util.EspressoIdlingResouce

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchViewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val setPref = SettingsDatastore.getInstance(application.datastore)
//        val setViewModel = ViewModelProvider(this, ViewModelFactory(setPref))[SettingViewModel::class.java]
//
//        setViewModel.isDarkMode.observe(this) {
//            when(it) {
//                true -> {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                }
//                false -> {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                }
//            }
//        }
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
                if (!EspressoIdlingResouce.idlingresource.isIdleNow) {
                    EspressoIdlingResouce.decrement()
                }
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
        binding.searchBar.inflateMenu(R.menu.main_menu)
        binding.searchBar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_favorite -> {
                    val favIntent = Intent(this@MainActivity, FavoriteActivity::class.java)
                    startActivity(favIntent)
                }
                R.id.menu_settings -> {
                    val setIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                    startActivity(setIntent)
                }
            }
            true
        }
    }
}