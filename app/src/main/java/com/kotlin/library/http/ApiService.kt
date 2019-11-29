package com.kotlin.library.http

import com.kotlin.library.enity.UserInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * Created by Caojing on 2019/11/211501
 *
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"

    }

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String): Deferred<UserInfo>

}