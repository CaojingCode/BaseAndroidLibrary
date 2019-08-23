package com.kotlin.library

import com.kotlin.baselibrary.BaseApplication
import com.zhouyou.http.model.HttpHeaders

/**
 * Created by Caojing on 2019/8/22.
 *  你不是一个人在战斗
 */
class MyCustomApplication : BaseApplication() {
    override fun getHttpHeaders(): HttpHeaders {
        return HttpHeaders()
    }

    override fun onCreate() {
        super.onCreate()

    }



}