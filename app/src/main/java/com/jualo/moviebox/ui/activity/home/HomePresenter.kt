package com.jualo.moviebox.ui.activity.home

import android.content.Context
import com.google.gson.Gson
import com.jualo.moviebox.api.responses.BaseApiResponse
import com.jualo.moviebox.helper.Helper
import com.jualo.moviebox.repository.MovieBoxRepository
import com.jualo.moviebox.ui.activity.home.HomeActivity.Companion.REFRESH_DATA
import com.jualo.moviebox.vo.api.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class HomePresenter @Inject constructor(
        val mHelper : Helper,
        val mContext : Context,
        val mRepository: MovieBoxRepository
) : HomeContract.UserActionListener {

    lateinit var mView: HomeContract.View
    var mDisposable = CompositeDisposable()

    override fun getMovieList(reqPage: String, state: String) {
        mView.showProgressBar()
        mView.hideEmptyResult()
        mView.hideErrorResult()
        mView.hideOfflineView()

        if(mHelper.isNetworkConnected(mContext)) {
            loadMovieList(reqPage, state)
        } else {
            mView.hideProgressBar()
            mView.hideEmptyResult()
            if(state.equals(REFRESH_DATA)) {
                mView.showErrorResult("Not Connected to Internet")
            } else {
                mView.showOfflineView()
            }
        }
    }

    override fun loadMovieList(reqPage: String, state: String) {
        mDisposable.add(mRepository.getPopularMovies(reqPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<BaseApiResponse<Result>>(){
                    override fun onNext(response: BaseApiResponse<Result>?) {
                        mView.hideProgressBar()

                        if(response != null && response.results.size > 0) {
                            mView.setMovieList(response.results, reqPage)
                            if(state.equals(REFRESH_DATA)) {
                                mView.setAdapter()
                            }
                        } else {
                            mView.showEmptyResult()
                        }
                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable?) {
                        mView.hideProgressBar()
                        mView.hideEmptyResult()
                        if(state.equals(REFRESH_DATA)) {
                            mView.showErrorResult("Not Connected to Internet")
                        } else {
                            mView.showOfflineView()
                        }
                    }

                }))
    }
}