package com.jualo.moviebox.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class ActivityNavigation @Inject constructor(val activity: AppCompatActivity) {

    /**
     * Intent to Home Page
     * */
//    fun navigateToHomePage() {
//        activity.startActivity(newIntent(activity, HomeActivity::class.java))
//    }

    /**
     * Intent Common Function
     * Handling new intent
     * */

    fun <T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}