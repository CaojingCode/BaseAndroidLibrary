package com.kotlin.library.http

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.kotlin.library.enity.BaseInfo
import com.zhouyou.http.callback.SimpleCallBack
import com.zhouyou.http.exception.ApiException

/**
 * Created by Caojing on 2019/9/2.
 *  你不是一个人在战斗
 */
abstract class BaseCallBack<T> : SimpleCallBack<T>() {

    override fun onSuccess(t: T) {
        if (t is String) {
            val fromJson = GsonUtils.fromJson(t, BaseInfo::class.java)
            if (fromJson.code == 0) {
                onTwoSuccess(t)
            } else {
                ToastUtils.showShort(fromJson.msg)
                onError(fromJson)
            }
        }
    }

    override fun onError(e: ApiException?) {
        if (e != null) {
            ToastUtils.showShort(e.message)
        }
    }


    abstract fun onTwoSuccess(result: String)

    abstract fun onError(baseInfo: BaseInfo)

}