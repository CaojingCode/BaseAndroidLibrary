package com.kotlin.baselibrary.http

import android.net.ParseException
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import com.kotlin.baselibrary.bean.BaseInfo
import kotlinx.coroutines.Deferred
import org.json.JSONException
import retrofit2.HttpException
import java.io.NotSerializableException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 *
 * Created by Caojing on 2019/11/211734
 *
 */
class ApiException {

    suspend fun <T> exceptionManage(deferred: Deferred<T>): T? {
        return try {
            deferred.await()
        } catch (e: Throwable) {
            handleException(e)
            return null
        }

    }


    fun handleException(e: Throwable) {
        val ex: ApiException
        if (e is HttpException) {
            ToastUtils.showShort("服务异常")
        } else if (e is JsonParseException
            || e is JSONException
            || e is JsonSyntaxException
            || e is JsonSerializer<*>
            || e is NotSerializableException
            || e is ParseException
        ) {
            ToastUtils.showShort("解析错误")
        } else if (e is ClassCastException) {
            ToastUtils.showShort("类型转换错误")
        } else if (e is ConnectException) {
            ToastUtils.showShort("连接失败")
        } else if (e is javax.net.ssl.SSLHandshakeException) {
            ToastUtils.showShort("连证书验证失败")
        } else if (e is java.net.SocketTimeoutException) {
            ToastUtils.showShort("连接超时")
        } else if (e is UnknownHostException) {
            ToastUtils.showShort("网络异常")
        } else if (e is NullPointerException) {
            ToastUtils.showShort("网络异常")
        }
    }

    /**
     * 约定异常
     */
    object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = UNKNOWN + 1
        /**
         * 网络错误
         */
        val NETWORD_ERROR = PARSE_ERROR + 1
        /**
         * 协议出错
         */
        val HTTP_ERROR = NETWORD_ERROR + 1

        /**
         * 证书出错
         */
        val SSL_ERROR = HTTP_ERROR + 1

        /**
         * 连接超时
         */
        val TIMEOUT_ERROR = SSL_ERROR + 1

        /**
         * 调用错误
         */
        val INVOKE_ERROR = TIMEOUT_ERROR + 1
        /**
         * 类转换错误
         */
        val CAST_ERROR = INVOKE_ERROR + 1
        /**
         * 请求取消
         */
        val REQUEST_CANCEL = CAST_ERROR + 1
        /**
         * 未知主机错误
         */
        val UNKNOWNHOST_ERROR = REQUEST_CANCEL + 1

        /**
         * 空指针错误
         */
        val NULLPOINTER_EXCEPTION = UNKNOWNHOST_ERROR + 1
    }
}