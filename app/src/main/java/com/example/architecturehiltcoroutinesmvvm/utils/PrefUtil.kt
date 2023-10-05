package com.example.architecturehiltcoroutinesmvvm.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PrefUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val LOGIN_DATA = "login_data"
        const val iUserId = "iUserId"
        const val PLAYBOOK_DATA = "playbook_data"
        const val AWAY_JURSEY = "AWAY_JURSEY"
        const val AWAY_JURSEY_COLOR = "AWAY_JURSEY_COLOR"
        const val AWAY_DRAWING_COLOR = "AWAY_DRAWING_COLOR"
        const val HOME_JURSEY = "HOME_JURSEY"
        const val HOME_JURSEY_COLOR = "HOME_JURSEY_COLOR"
        const val HOME_DRAWING_COLOR = "HOME_DRAWING_COLOR"
        const val FOLDER_COLOR = "FOLDER_COLOR"
        const val PLAY_COLOR = "PLAY_COLOR"
        const val FREE_DRAW_COLOR = "FREE_DRAW_COLOR"
        const val USER_ROLE_IN_TEAM="USER_ROLE_IN_TEAM"
    }

    fun putString(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    fun putInt(key: String, value: Int) = sharedPreferences.edit().putInt(key, value).apply()

    fun getString(key: String) = sharedPreferences.getString(key, null)

    fun getInt(key: String) = sharedPreferences.getInt(key, -1)

    fun remove(key: String) = sharedPreferences.edit().remove(key).apply()

    fun removeAll() = sharedPreferences.edit().clear().apply()
}