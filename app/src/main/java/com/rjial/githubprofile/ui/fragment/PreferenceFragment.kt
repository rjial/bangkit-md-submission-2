package com.rjial.githubprofile.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.rjial.githubprofile.R

class PreferenceFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference)
    }
}