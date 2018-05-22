package com.jualo.moviebox.helper

import android.content.Context
import android.net.ConnectivityManager

class Helper {
    /**
     * Checking internet connection
     * @Param context
     * @Return boolean
     */

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}