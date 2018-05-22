package com.jualo.moviebox.repository

import com.jualo.moviebox.BuildConfig
import com.jualo.moviebox.api.NetworkServices
import com.jualo.moviebox.api.responses.BaseApiResponse
import com.jualo.moviebox.vo.api.Result
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieBoxRepository @Inject constructor(var mNetworkServices: NetworkServices) {

    /**
     * Get Popular Movies
     */
    fun getPopularMovies(page: String): Flowable<BaseApiResponse<Result>> {
        return mNetworkServices.getPopularMovies(page, BuildConfig.MOVIEDB_APIKEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}