package com.kotlin.baselibrary

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.model.HttpHeaders

/**
 * Created by Caojing on 2019/8/21.
 *  你不是一个人在战斗
 */
abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        EasyHttp.init(this)
        EasyHttp.getInstance().setBaseUrl("https://www.wanandroid.com")
            .debug(AppConfig.TAG, BuildConfig.DEBUG)
            .setReadTimeOut((AppConfig.timeOut * 1000).toLong())
            .setWriteTimeOut((AppConfig.timeOut * 1000).toLong())
            .setConnectTimeout((AppConfig.timeOut * 1000).toLong())
            .addCommonHeaders(getHttpHeaders())
    }

    abstract fun getHttpHeaders(): HttpHeaders


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        CrashUtils.init { crashInfo, e ->
            LogUtils.d("崩溃异常信息\n$crashInfo")
        }
    }
}