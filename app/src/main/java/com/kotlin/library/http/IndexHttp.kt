package com.kotlin.library.http

import com.blankj.utilcode.util.GsonUtils
import com.kotlin.library.enity.ArticleListInfo
import com.kotlin.library.enity.BannerInfo
import com.kotlin.library.enity.BaseInfo
import com.zhouyou.http.EasyHttp

/**
 * Created by Caojing on 2019/9/2.
 *  你不是一个人在战斗
 */
class IndexHttp {

    var callBack: IndexHttpCallBack? = null

    /**
     * 广告图请求
     */
    fun httpImageLoading(): IndexHttp {
        EasyHttp.get("/banner/json").execute(object : BaseCallBack<String>() {
            override fun onTwoSuccess(result: String) {
                val bannerInfo = GsonUtils.fromJson(result, BannerInfo::class.java)
                callBack?.updateBanner(bannerInfo)
            }

            override fun onError(baseInfo: BaseInfo) {

            }

        })
        return this
    }

    /**
     * 首页文章
     */
    fun httpIndexArticle(page: Int = 0) {
        EasyHttp.get("/article/list/$page/json").execute(object : BaseCallBack<String>() {
            override fun onTwoSuccess(result: String) {
                val articleListInfo = GsonUtils.fromJson(result, ArticleListInfo::class.java)
                callBack?.updateArticleList(articleListInfo)
            }

            override fun onError(baseInfo: BaseInfo) {
            }

        })
    }


    fun setIndexHttpCallBack(callBack: IndexHttpCallBack): IndexHttp {
        this.callBack = callBack
        return this
    }
}

interface IndexHttpCallBack {
    fun updateBanner(bannerInfo: BannerInfo)
    fun updateArticleList(articleListInfo: ArticleListInfo)
}