package com.kotlin.library.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import com.blankj.utilcode.util.LogUtils
import com.kotlin.library.R
import com.kotlin.library.activity.WebViewActivity
import com.kotlin.library.adapters.GlideImageLoader
import com.kotlin.library.enity.ArticleListInfo
import com.kotlin.library.enity.BannerInfo
import com.kotlin.library.http.IndexHttp
import com.kotlin.library.http.IndexHttpCallBack
import com.qmuiteam.qmui.arch.QMUIFragment
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
class IndexFragment : QMUIFragment(), IndexHttpCallBack {
    lateinit var homeLayout: View
    private val indexHttp = IndexHttp()

    override fun onCreateView(): View {
        homeLayout = LayoutInflater.from(activity).inflate(R.layout.home_layout, null)
        initView()
        return homeLayout
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        indexHttp.setIndexHttpCallBack(this).httpImageLoading().httpIndexArticle()
        homeLayout.banner.setImageLoader(GlideImageLoader())

//        activity?.startActivity(Intent(activity, WebViewActivity::class.java))

        Observable.create<Document> {
            val doc = Jsoup.connect("https://www.jianshu.com/").get()
            it.onNext(doc)
            it.onComplete()
        }.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val elements = it.select("div#list-container ul li")
                for (element in elements) {
                    LogUtils.d(
                        "element_tittle=${element.select("div.content a").text()}\n" +
                                "content=${element.select("div.content p").text()}\n" +
                                "简书钻=${element.select("div.content div.meta span.jsd-meta").text()}\n" +
                                "作者=${element.select("div.content div.meta a").text()}\n" +
                                "图片=https:${element.select("a.wrap-img img").attr("src")}"
                    )
                }
            }


    }

    /**
     * 广告图回调
     */
    override fun updateBanner(bannerInfo: BannerInfo) {
        homeLayout.banner.setImages(bannerInfo.data).start()
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