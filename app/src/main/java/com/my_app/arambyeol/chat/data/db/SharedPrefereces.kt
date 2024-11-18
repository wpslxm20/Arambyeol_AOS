package com.my_app.arambyeol.chat.data.db

import android.content.Context
import android.content.SharedPreferences

class TokenSharedPreferences(context: Context) {
    private val prefsFilename = "token_prefs"
    private val key_accessToken = "accessToken"
    private val key_refreshToken = "refreshToken"
    private val key_deviceUID = "deviceUID"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename,0)

    var accessToken: String?
        get() = prefs.getString(key_accessToken,"")
        set(value) = prefs.edit().putString(key_accessToken,value).apply()
    var refreshToken: String?
        get() = prefs.getString(key_refreshToken,"")
        set(value) = prefs.edit().putString(key_refreshToken,value).apply()

    var deviceUID: String?
        get() = prefs.getString(key_deviceUID,"")
        set(value) = prefs.edit().putString(key_deviceUID,value).apply()
}