package com.kotlin.baselibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.view.View.GONE
import android.view.View.VISIBLE
import com.blankj.utilcode.util.LogUtils
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by Caojing on 2019/9/4.
 *  你不是一个人在战斗
 */
abstract class BaseWebActivity : BaseActivity() {

    lateinit var webSetting: WebSettings

    var mIsPageLoading: Boolean = false //标识网页是否重定向

    var mIsPageError: Boolean = false //标识网页是否出错

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        setRightVisible()
        setWebSetting()
        llWebView.loadUrl(getUrl())
        llWebView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                //如果该链接发生了重定向，回调shouldOverrideUrlLoading会在回调onPageFinished之前。
                if (mIsPageLoading) {
                    //表示回调为重定向。
                    return false
                }
                if (mIsPageError)
                    return false
                jumWebView(p1.toString())
                return true
            }

            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                super.onPageStarted(p0, p1, p2)
                mIsPageLoading = true
                mIsPageError = false
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
                mIsPageLoading = false
            }

            override fun onReceivedError(p0: WebView?, p1: Int, p2: String?, p3: String?) {
                mIsPageError = true
            }

        }
        llWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                setTittleText(p1.toString())
            }

            override fun onProgressChanged(p0: WebView?, p1: Int) {
                if (p1 == 100) {
                    progressBar.visibility = GONE
                } else {
                    if (progressBar.visibility == GONE)
                        progressBar.visibility = VISIBLE
                    progressBar.progress = p1
                }
            }

        }

    }

    private fun setWebSetting() {
        webSetting = llWebView.settings
        webSetting.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSetting.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSetting.displayZoomControls = true //隐藏原生的缩放控件
        webSetting.blockNetworkImage = false//解决图片不显示
        webSetting.loadsImagesAutomatically = true //支持自动加载图片
        webSetting.defaultTextEncodingName = "utf-8"//设置编码格式
    }

    abstract fun getUrl(): String

    abstract fun jumWebView(url: String)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()
        llWebView.onResume()
        llWebView.settings.javaScriptEnabled = true
    }

    override fun onPause() {
        super.onPause()
        llWebView.onPause()
        llWebView.settings.lightTouchEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        llWebView.destroy()

    }

    override fun backAction() {
        if (llWebView.canGoBack()) {
            llWebView.goBack()
        } else {
            finish()
        }
    }
}