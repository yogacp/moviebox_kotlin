package com.jualo.moviebox.ui.activity.detailmovies

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.jualo.moviebox.R
import com.jualo.moviebox.ui.common.BaseActivity
import com.jualo.moviebox.utils.AppsConstant
import com.jualo.moviebox.vo.api.DetailsMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class DetailMoviesActivity: BaseActivity(), DetailMoviesContract.View {

    @Inject
    lateinit var mPresenter: DetailMoviesPresenter

    companion object {
        val TAG_MOVIES_ID = "movies_id"
    }

    var mMoviesId = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_movie
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
        initalizeData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun initalizeData() {
        mPresenter.mView = this

        val bundle = intent.extras
        if (bundle != null) {
            if (bundle.containsKey(TAG_MOVIES_ID)) {
                mMoviesId = bundle.getString(TAG_MOVIES_ID)
                mPresenter.getDetailMovie(mMoviesId)
            }
        }
    }

    override fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setupData(result: DetailsMovie?) {
        result?.let {
            collapsingToolbar.title = it.title.capitalize()
            collapsingToolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
            loadImageToImageView(AppsConstant.BASE_IMAGE.URL_IMAGE_BACKDROP + "/w1280/" + it.backdropPath,imageMovie)
            detailMovieReleaseDate.setText(it.releaseDate)
            detailMovieCountAverage.setText("${it.voteAverage} / 10")
            detailMovieTitle.setText(it.title.capitalize())
            detailMovieDescription.setText(it.overview)

            var genres = ""
            for(genre in it.genres) {
                genres += "- " + genre.name + "\n"
            }

            detailMovieGenre.setText(genres)
        }
    }

    override fun loadImageToImageView(mImagesUrl: String, imgView: ImageView) {
        Picasso.get()
                .load(Uri.parse(mImagesUrl))
                .placeholder(R.drawable.progressbar)
                .fit()
                .centerInside()
                .into(imgView)
    }

    override fun showErrorResult(message: String) {
        toast(message)
    }
}