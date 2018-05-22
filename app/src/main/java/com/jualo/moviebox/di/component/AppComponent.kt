package com.jualo.moviebox.di.component

import android.app.Application
import com.jualo.moviebox.MovieBoxApp
import com.jualo.moviebox.di.module.AppModule
import com.jualo.moviebox.di.module.NetworkModule
import com.jualo.moviebox.di.module.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AndroidInjectionModule::class,
                ActivityBuilder::class,
                AppModule::class,
                NetworkModule::class
        )
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }

    fun inject(app: MovieBoxApp)
}