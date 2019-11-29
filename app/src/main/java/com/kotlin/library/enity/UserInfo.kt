package com.kotlin.library.enity

import com.google.gson.annotations.SerializedName


/**
 * Created by Caojing on 2019/9/2.
 *  你不是一个人在战斗
 */
data class UserInfo(
    @SerializedName("data")
    var `data`: Data = Data(),
    @SerializedName("errorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMsg")
    var errorMsg: String = ""
) {
    data class Data(
        @SerializedName("admin")
        var admin: Boolean = false,
        @SerializedName("chapterTops")
        var chapterTops: List<Any> = listOf(),
        @SerializedName("collectIds")
        var collectIds: List<Int> = listOf(),
        @SerializedName("email")
        var email: String = "",
        @SerializedName("icon")
        var icon: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("nickname")
        var nickname: String = "",
        @SerializedName("password")
        var password: String = "",
        @SerializedName("publicName")
        var publicName: String = "",
        @SerializedName("token")
        var token: String = "",
        @SerializedName("type")
        var type: Int = 0,
        @SerializedName("username")
        var username: String = ""
    )
}



