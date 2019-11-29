package com.kotlin.library.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kotlin.baselibrary.http.ApiException
import com.kotlin.library.enity.UserInfo
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by Caojing on 2019/11/211534
 *
 */
class ApiRepository {

     var apiException:ApiException=ApiException()

    companion object {
        val retrofit: ApiService = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //添加对Deffered的支持
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
            .create(ApiService::class.java)
    }


    //登录
    suspend fun httpLogin(userName: String, password: String): UserInfo? {
        //withContext挂起代码块，到运行结束
        return apiException.exceptionManage(
            withContext(Dispatchers.IO) {
                retrofit.login(userName, password)
            })
    }

}