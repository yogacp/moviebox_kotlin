package com.jualo.moviebox.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.jualo.moviebox.helper.Helper
import com.jualo.moviebox.utils.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun gson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    internal fun provideHelper(): Helper {
        return Helper()
    }

    @Provides
    @Singleton
    internal fun rxBus(): RxBus {
        return RxBus()
    }
}