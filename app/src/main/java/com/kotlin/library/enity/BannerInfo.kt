package com.kotlin.library.enity


import com.google.gson.annotations.SerializedName

data class BannerInfo(
    @SerializedName("data")
    var `data`: MutableList<Data> = mutableListOf(),
    @SerializedName("errorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMsg")
    var errorMsg: String = ""
) {
    data class Data(
        @SerializedName("desc")
        var desc: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("imagePath")
        var imagePath: String = "",
        @SerializedName("isVisible")
        var isVisible: Int = 0,
        @SerializedName("order")
        var order: Int = 0,
        @SerializedName("title")
        var title: String = "",
        @SerializedName("type")
        var type: Int = 0,
        @SerializedName("url")
        var url: String = ""
    )
}