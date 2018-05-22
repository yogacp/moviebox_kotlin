package com.jualo.moviebox.ui.activity.home

import com.jualo.moviebox.api.NetworkServices
import com.jualo.moviebox.di.scope.ActivityScope
import com.jualo.moviebox.repository.MovieBoxRepository
import com.jualo.moviebox.ui.common.navigation.ActivityNavigation
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    @ActivityScope
    internal fun provideNavigation(homeActivity: HomeActivity) : ActivityNavigation {
        return ActivityNavigation(homeActivity)
    }

    @Provides
    @ActivityScope
    internal fun provideHomeActivity(homeActivity: HomeActivity): HomeContract.View {
        return homeActivity
    }

    @Provides
    @ActivityScope
    internal fun provideRandomUserRepository(mNetworkServices: NetworkServices): MovieBoxRepository {
        return MovieBoxRepository(mNetworkServices)
    }
}