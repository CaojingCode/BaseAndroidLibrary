package com.kotlin.library.activity

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.kotlin.baselibrary.BaseWebActivity
import com.kotlin.baselibrary.toIntent


/**
 * Created by Caojing on 2019/9/3.
 *  你不是一个人在战斗
 */
class WebViewActivity : BaseWebActivity() {
    override fun closeAllWebView() {
        ActivityUtils.finishActivity(WebViewActivity::class.java)
    }

    override fun jumWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        toIntent(intent)
    }

    override fun getUrl(): String {
        val url=intent.getStringExtra("url")
        if (url!=null){
            return url
        }else{
            throw SecurityException("url不能为空")
        }
    }

}