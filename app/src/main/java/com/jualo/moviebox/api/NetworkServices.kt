package com.jualo.moviebox.api

import com.jualo.moviebox.api.responses.BaseApiResponse
import com.jualo.moviebox.vo.api.Result
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {
    @GET("movie/popular?language=en-US")
    fun getPopularMovies(@Query("page") page: String, @Query("api_key") api_key: String): Flowable<BaseApiResponse<Result>>

}