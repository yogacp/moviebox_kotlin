package com.jualo.moviebox.ui.activity.home

import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.jualo.moviebox.R
import com.jualo.moviebox.adapters.setUp
import com.jualo.moviebox.helper.Helper
import com.jualo.moviebox.ui.common.BaseActivity
import com.jualo.moviebox.utils.AppsConstant
import com.jualo.moviebox.utils.InfiniteScrollListener
import com.jualo.moviebox.vo.api.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_movie_list.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mPresenter: HomePresenter

    companion object {
        val REFRESH_DATA = "refresh_data"
        val LOAD_MORE_DATA = "load_more_data"
    }

    var reqPage = 1
    var mMovieList = ArrayList<Result>()

    lateinit var mLayoutManager : RecyclerView.LayoutManager
    lateinit var mScrollViewListener : InfiniteScrollListener

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUI()
        loadMovieList()
        setupListener()
    }

    override fun onBackPressed() {
        showExitPopup()
    }

    override fun loadMovieList() {
        clearMovieList()
        mPresenter.getMovieList(reqPage.toString(),REFRESH_DATA)
    }

    override fun clearMovieList() {
        mMovieList.clear()
    }

    override fun setMovieList(resultList: List<Result>, page: String) {
        for(result in resultList) {
            mMovieList.add(result)
        }
    }

    override fun setAdapter() {
        rvUserList.addOnScrollListener(mScrollViewListener)
        rvUserList.setUp(
                mMovieList,
                R.layout.item_movie_list,
                {
                    loadImageToImageView(AppsConstant.BASE_IMAGE.URL_IMAGE + it.posterPath,imgMovies)
                    tvMoviesTitle.text = it.title.capitalize()
                    tvMoviesReleaseDate.text = "Release Date: ${it.releaseDate}"
                },
                {
                    mActivityNavigation.navigateToDetailPage(it.id.toString())
                },
                mLayoutManager
        )
    }

    override fun loadImageToImageView(mImagesUrl: String, imgView: ImageView) {
        Picasso.get()
                .load(Uri.parse(mImagesUrl))
                .placeholder(R.drawable.progressbar)
                .fit()
                .centerInside()
                .into(imgView)
    }

    override fun setupUI() {
        homeSwipeLayout.setOnRefreshListener(this)
        mLayoutManager = GridLayoutManager(applicationContext, 2)
        llOfflineView.setOnClickListener {
            mPresenter.getMovieList(reqPage.toString(), LOAD_MORE_DATA)
        }
    }

    override fun setupListener() {
        mScrollViewListener = object : InfiniteScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                reqPage = page + 1
                mPresenter.getMovieList(reqPage.toString(), LOAD_MORE_DATA)

                val cursize = rvUserList.adapter.itemCount
                view.post(object : Runnable{
                    override fun run() {
                        rvUserList.adapter.notifyItemRangeInserted(cursize, mMovieList.size - 1)
                    }
                })
            }
        }
    }

    override fun showEmptyResult() {
        rlEmptyUserList.visibility = View.VISIBLE
    }

    override fun showErrorResult(message: String) {
        rlErrorUserList.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        avLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideEmptyResult() {
        rlEmptyUserList.visibility = View.GONE
    }

    override fun hideErrorResult() {
        rlErrorUserList.visibility = View.GONE
    }

    override fun hideProgressBar() {
        avLoadingIndicator.visibility = View.GONE
    }

    override fun showOfflineView() {
        toast("Not Connected to Internet")
        llOfflineView.visibility = View.VISIBLE
    }

    override fun hideOfflineView() {
        llOfflineView.visibility = View.GONE
    }

    override fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    override fun showExitPopup() {
        val dialogSource = "2"
        val btnPositive = "Yes"
        val btnNegative = "Cancel"

        mHelper.showPopupDialog(this,
                "Confirmation",
                "Do you want to close this application?",
                dialogSource,
                btnPositive,
                btnNegative,
                object : Helper.CallbackDialog {
                    override fun onButtonPositiveClicked() {
                        exitApp()
                    }

                    override fun onButtonNegativeClicked() {

                    }

                })
    }

    override fun exitApp() {
        finishAffinity()
        System.exit(0)
    }

    override fun onRefresh() {
        homeSwipeLayout.isRefreshing = false
        loadMovieList()
    }
}