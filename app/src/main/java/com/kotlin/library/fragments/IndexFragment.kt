package com.kotlin.library.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import com.blankj.utilcode.util.LogUtils
import com.kotlin.baselibrary.BaseFragment
import com.kotlin.baselibrary.toIntent
import com.kotlin.library.R
import com.kotlin.library.activity.WebViewActivity
import com.kotlin.library.adapters.GlideImageLoader
import com.kotlin.library.enity.ArticleListInfo
import com.kotlin.library.enity.BannerInfo
import com.kotlin.library.http.IndexHttp
import com.kotlin.library.http.IndexHttpCallBack
import com.qmuiteam.qmui.arch.QMUIFragment
import com.youth.banner.listener.OnBannerClickListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_layout.view.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by Caojing on 2019/8/27.
 *  你不是一个人在战斗
 */
class IndexFragment : BaseFragment(), IndexHttpCallBack {
    lateinit var homeLayout: View
    private val indexHttp = IndexHttp()
    var images= mutableListOf<BannerInfo.Data>()

    override fun layoutId(): Int {
        return R.layout.home_layout
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView(view: View) {
        homeLayout = view
        indexHttp.setIndexHttpCallBack(this).httpImageLoading().httpIndexArticle()
        homeLayout.banner.setImageLoader(GlideImageLoader())
        homeLayout.banner.setOnBannerListener {
            val intent=Intent(activity,WebViewActivity::class.java)
            intent.putExtra("url",images[it].url)
            toIntent(intent)
        }
    }

    /**
     * 广告图回调
     */
    override fun updateBanner(bannerInfo: BannerInfo) {
        images= bannerInfo.data
        homeLayout.banner.setImages(images).start()
    }

    /**
     * 首页文章列表
     */
    override fun updateArticleList(articleListInfo: ArticleListInfo) {

    }

    /**
     * 是否禁止滑动返回
     */
    override fun canDragBack(): Boolean {
        return false
    }

    override fun onStart() {
        super.onStart()
        homeLayout.banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        homeLayout.banner.stopAutoPlay()
    }
}