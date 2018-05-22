package com.jualo.moviebox.di.module.builder

import com.jualo.moviebox.di.scope.ActivityScope
import com.jualo.moviebox.ui.activity.detailmovies.DetailMoviesActivity
import com.jualo.moviebox.ui.activity.detailmovies.DetailMoviesModule
import com.jualo.moviebox.ui.activity.home.HomeActivity
import com.jualo.moviebox.ui.activity.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    internal abstract fun bindHomeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(DetailMoviesModule::class))
    internal abstract fun bindDetailsMovieActivity(): DetailMoviesActivity
}