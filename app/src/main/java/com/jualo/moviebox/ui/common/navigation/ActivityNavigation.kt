package com.jualo.moviebox.ui.common.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import com.jualo.moviebox.ui.activity.detailmovies.DetailMoviesActivity
import com.jualo.moviebox.ui.activity.detailmovies.DetailMoviesActivity.Companion.TAG_MOVIES_ID
import com.jualo.moviebox.ui.activity.home.HomeActivity
import javax.inject.Inject

class ActivityNavigation @Inject constructor(val activity: AppCompatActivity) {

    /**
     * Intent to Home Page
     * */
    fun navigateToHomePage() {
        activity.startActivity(newIntent(activity, HomeActivity::class.java))
    }

    /**
     * Intent to Detail Page
     * */
    fun navigateToDetailPage(moviesId: String) {
        val detailActivity = newIntent(activity, DetailMoviesActivity::class.java)
        detailActivity.apply {
            putExtra(TAG_MOVIES_ID, moviesId)
        }
        activity.startActivity(detailActivity)
    }

    /**
     * Intent Common Function
     * Handling new intent
     * */

    fun <T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}