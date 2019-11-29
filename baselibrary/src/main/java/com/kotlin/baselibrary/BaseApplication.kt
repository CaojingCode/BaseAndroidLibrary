package com.kotlin.baselibrary

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.tencent.smtt.sdk.QbSdk

/**
 * Created by Caojing on 2019/8/21.
 *  你不是一个人在战斗
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        QMUISwipeBackActivityManager.init(this)
        CrashUtils.init { crashInfo, e ->
            LogUtils.d("崩溃异常信息\n$crashInfo")
        }

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        val cb = object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
            }

            override fun onViewInitFinished(p0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is $p0")
            }


        }
        //x5内核初始化接口
        QbSdk.initX5Environment(this, cb)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}