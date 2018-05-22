package com.jualo.moviebox.ui.activity.detailmovies

import com.jualo.moviebox.api.NetworkServices
import com.jualo.moviebox.di.scope.ActivityScope
import com.jualo.moviebox.repository.MovieBoxRepository
import com.jualo.moviebox.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class DetailMoviesModule {
    @Provides
    @ActivityScope
    internal fun provideNavigation(detailMoviesActivity: DetailMoviesActivity) : ActivityNavigation {
        return ActivityNavigation(detailMoviesActivity)
    }

    @Provides
    @ActivityScope
    internal fun provideHomeActivity(detailMoviesActivity: DetailMoviesActivity): DetailMoviesContract.View {
        return detailMoviesActivity
    }

    @Provides
    @ActivityScope
    internal fun provideRandomUserRepository(mNetworkServices: NetworkServices): MovieBoxRepository {
        return MovieBoxRepository(mNetworkServices)
    }
}