package com.rjial.githubprofile.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceClickListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.rjial.githubprofile.R
import com.rjial.githubprofile.datastore.SettingsDatastore
import com.rjial.githubprofile.datastore.datastore
import com.rjial.githubprofile.model.viewmodel.SettingViewModel
import com.rjial.githubprofile.service.ViewModelFactory

class PreferenceFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference)
        val darkCheck = findPreference<SwitchPreference>(getString(R.string.key_dark_mode)) as SwitchPreference
        val setPref = SettingsDatastore.getInstance(requireActivity().datastore)
        val setViewModel = ViewModelProvider(this, ViewModelFactory(setPref))[SettingViewModel::class.java]

        setViewModel.isDarkMode.observe(this) {
            when(it) {
                true -> {
                    darkCheck?.isChecked = true
                }
                false -> {
                    darkCheck?.isChecked = false
                }
            }
        }
        darkCheck.setOnPreferenceClickListener {
            when(darkCheck.isChecked) {
                true -> {
                    setViewModel.setDarkMode(true)
                    return@setOnPreferenceClickListener darkCheck.isChecked
                }
                false -> {
                    setViewModel.setDarkMode(false)
                    return@setOnPreferenceClickListener darkCheck.isChecked
                }
            }
        }

    }
}