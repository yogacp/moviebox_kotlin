package com.jualo.moviebox.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.jualo.moviebox.R
import org.jetbrains.anko.toast

class SplashScreenActivity: AppCompatActivity() {
    val LOG_TAG = "SplashScreen"
    val SPLASH_TIMEOUT = 1000 // 1 seconds timeout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        runSplashScreen(SPLASH_TIMEOUT.toLong())
    }

    fun runSplashScreen(timeout: Long) {
        Handler().postDelayed({
            kotlin.run {
                toast("Redirect to homepage")
            }
        }, timeout)
    }
}