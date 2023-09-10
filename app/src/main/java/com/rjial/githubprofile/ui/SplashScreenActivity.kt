package com.rjial.githubprofile.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.rjial.githubprofile.R
import com.rjial.githubprofile.datastore.SettingsDatastore
import com.rjial.githubprofile.datastore.datastore
import com.rjial.githubprofile.model.viewmodel.SettingViewModel
import com.rjial.githubprofile.service.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val setPref = SettingsDatastore.getInstance(application.datastore)
        val setViewModel = ViewModelProvider(this, ViewModelFactory(setPref))[SettingViewModel::class.java]

        setViewModel.isDarkMode.observe(this) {
            when(it) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        setTheme(R.style.Theme_GithubProfile_AppSplash)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val activityScope = CoroutineScope(Dispatchers.Main)

        activityScope.launch {

            delay(3000)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}