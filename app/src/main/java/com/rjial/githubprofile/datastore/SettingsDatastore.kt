package com.rjial.githubprofile.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsDatastore private constructor(private val datastore: DataStore<Preferences>) {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return datastore.data.map {
            it[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        datastore.edit {
            it[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var instance: SettingsDatastore? = null

        fun getInstance(datastore: DataStore<Preferences>): SettingsDatastore {
            return instance ?: synchronized(this) {
                return instance ?: SettingsDatastore(datastore)
            }
        }
    }
}