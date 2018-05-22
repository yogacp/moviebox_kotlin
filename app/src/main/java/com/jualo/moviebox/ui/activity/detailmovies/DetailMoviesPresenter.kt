package com.jualo.moviebox.ui.activity.detailmovies

import android.content.Context
import com.jualo.moviebox.api.responses.BaseApiResponse
import com.jualo.moviebox.helper.Helper
import com.jualo.moviebox.repository.MovieBoxRepository
import com.jualo.moviebox.ui.activity.home.HomeActivity
import com.jualo.moviebox.vo.api.DetailsMovie
import com.jualo.moviebox.vo.api.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class DetailMoviesPresenter @Inject constructor(
        val mHelper : Helper,
        val mContext : Context,
        val mRepository: MovieBoxRepository
) : DetailMoviesContract.UserActionListener {

    lateinit var mView: DetailMoviesContract.View
    var mDisposable = CompositeDisposable()

    override fun getDetailMovie(id: String) {
        if(mHelper.isNetworkConnected(mContext)) {
            loadDetailMovie(id)
        } else {
            mView.showErrorResult("Not Connected to Internet")
        }
    }

    override fun loadDetailMovie(id: String) {
        mDisposable.add(mRepository.getDetailsMovies(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<DetailsMovie>(){
                    override fun onNext(response: DetailsMovie?) {
                        if(response != null) {
                            mView.setupData(response)
                        } else {
                            mView.showErrorResult("Error while loading the data")
                        }
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        mView.showErrorResult("Not Connected to Internet")
                    }

                }))
    }
}