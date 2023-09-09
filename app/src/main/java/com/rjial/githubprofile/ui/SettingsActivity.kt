package com.rjial.githubprofile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.ActivitySettingsBinding
import com.rjial.githubprofile.ui.fragment.PreferenceFragment

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.lytSettingContainer, PreferenceFragment())
            .commit()
    }
}