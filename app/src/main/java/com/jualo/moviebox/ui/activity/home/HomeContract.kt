package com.jualo.moviebox.ui.activity.home

import android.widget.ImageView
import com.jualo.moviebox.vo.api.Result

interface HomeContract {
    interface View {
        fun loadMovieList()
        fun clearMovieList()
        fun setMovieList(resultList: List<Result>, page: String)
        fun setAdapter()
        fun loadImageToImageView(mImagesUrl: String, imgView: ImageView)
        fun setupUI()
        fun setupListener()
        fun showProgressBar()
        fun hideProgressBar()
        fun showEmptyResult()
        fun hideEmptyResult()
        fun showOfflineView()
        fun hideOfflineView()
        fun showErrorResult(message: String)
        fun hideErrorResult()
        fun dpToPx(dp: Int): Int
        fun showExitPopup()
        fun exitApp()
    }

    interface UserActionListener {
        fun getMovieList(reqPage: String, state: String)
        fun loadMovieList(reqPage: String, state: String)
    }
}