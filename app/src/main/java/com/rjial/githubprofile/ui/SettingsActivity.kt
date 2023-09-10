package com.rjial.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivitySettingsBinding
import com.rjial.githubprofile.datastore.SettingsDatastore
import com.rjial.githubprofile.datastore.datastore
import com.rjial.githubprofile.model.viewmodel.SettingViewModel
import com.rjial.githubprofile.service.ViewModelFactory
import com.rjial.githubprofile.ui.fragment.PreferenceFragment

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val setPref = SettingsDatastore.getInstance(application.datastore)
        val setViewModel = ViewModelProvider(this, ViewModelFactory(setPref))[SettingViewModel::class.java]

        setViewModel.isDarkMode.observe(this) {
            setViewModel.setDarkMode(it)
            AppCompatDelegate.setDefaultNightMode(when(it) {
                true -> AppCompatDelegate.MODE_NIGHT_YES
                false -> AppCompatDelegate.MODE_NIGHT_NO
            })
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.lytSettingContainer, PreferenceFragment())
            .commit()
    }
}