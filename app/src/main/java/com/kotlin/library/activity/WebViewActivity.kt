package com.kotlin.library.activity

import android.content.Intent
import android.view.MotionEvent
import com.blankj.utilcode.util.ActivityUtils
import com.kotlin.baselibrary.BaseWebActivity
import com.kotlin.baselibrary.toIntent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler


/**
 * Created by Caojing on 2019/9/3.
 *  你不是一个人在战斗
 */
class WebViewActivity : BaseWebActivity() {
    override fun closeAllWebView() {
        ActivityUtils.finishActivity(WebViewActivity::class.java)
    }

    override fun initView() {
        super.initView()
    }

    override fun jumWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        toIntent(intent)
    }

    override fun getUrl(): String {
        val url = intent.getStringExtra("url")
        if (url != null) {
            return url
        } else {
            throw SecurityException("url不能为空")
        }
    }

}