package com.jualo.moviebox.ui.activity.detailmovies

import android.widget.ImageView
import com.jualo.moviebox.vo.api.DetailsMovie

interface DetailMoviesContract {
    interface View {
        fun initalizeData()
        fun setupUI()
        fun showErrorResult(message: String)
        fun setupData(result: DetailsMovie?)
        fun loadImageToImageView(mImagesUrl: String, imgView: ImageView)
    }

    interface UserActionListener {
        fun getDetailMovie(id: String)
        fun loadDetailMovie(id: String)
    }
}