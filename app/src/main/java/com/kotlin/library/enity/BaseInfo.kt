package com.kotlin.library.enity

import com.google.gson.annotations.SerializedName

/**
 * Created by Caojing on 2019/9/2.
 *  你不是一个人在战斗
 */
data class BaseInfo(
    @SerializedName("errorCode")
    var code: Int = -1,
    @SerializedName("errorMsg")
    var msg: String = ""
)

