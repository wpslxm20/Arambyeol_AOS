package com.my_app.arambyeol.controller

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat

class NetworkChecker(private val context: Context) {
    fun isInternetAvailable(): Boolean {
        val connectivityManager = ContextCompat.getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // 여기에 다른 네트워크 유형을 확인하는 코드를 추가할 수 있습니다.
                else -> false
            }
        } else {
            // Build.VERSION.SDK_INT < M (API 23)의 경우
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
}
