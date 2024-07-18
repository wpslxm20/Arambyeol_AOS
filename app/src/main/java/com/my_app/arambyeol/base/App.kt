package com.my_app.arambyeol.base

import android.app.Application
import com.my_app.arambyeol.chat.data.db.TokenSharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        token_prefs = TokenSharedPreferences(applicationContext)
    }
}