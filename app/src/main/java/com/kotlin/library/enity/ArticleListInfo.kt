package com.kotlin.library.enity


import com.google.gson.annotations.SerializedName

data class ArticleListInfo(
    @SerializedName("data")
    var `data`: Data = Data(),
    @SerializedName("errorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMsg")
    var errorMsg: String = ""
) {
    data class Data(
        @SerializedName("curPage")
        var curPage: Int = 0,
        @SerializedName("datas")
        var datas: List<Data> = listOf(),
        @SerializedName("offset")
        var offset: Int = 0,
        @SerializedName("over")
        var over: Boolean = false,
        @SerializedName("pageCount")
        var pageCount: Int = 0,
        @SerializedName("size")
        var size: Int = 0,
        @SerializedName("total")
        var total: Int = 0
    ) {
        data class Data(
            @SerializedName("apkLink")
            var apkLink: String = "",
            @SerializedName("author")
            var author: String = "",
            @SerializedName("chapterId")
            var chapterId: Int = 0,
            @SerializedName("chapterName")
            var chapterName: String = "",
            @SerializedName("collect")
            var collect: Boolean = false,
            @SerializedName("courseId")
            var courseId: Int = 0,
            @SerializedName("desc")
            var desc: String = "",
            @SerializedName("envelopePic")
            var envelopePic: String = "",
            @SerializedName("fresh")
            var fresh: Boolean = false,
            @SerializedName("id")
            var id: Int = 0,
            @SerializedName("link")
            var link: String = "",
            @SerializedName("niceDate")
            var niceDate: String = "",
            @SerializedName("origin")
            var origin: String = "",
            @SerializedName("prefix")
            var prefix: String = "",
            @SerializedName("projectLink")
            var projectLink: String = "",
            @SerializedName("publishTime")
            var publishTime: Long = 0,
            @SerializedName("superChapterId")
            var superChapterId: Int = 0,
            @SerializedName("superChapterName")
            var superChapterName: String = "",
            @SerializedName("tags")
            var tags: List<Any> = listOf(),
            @SerializedName("title")
            var title: String = "",
            @SerializedName("type")
            var type: Int = 0,
            @SerializedName("userId")
            var userId: Int = 0,
            @SerializedName("visible")
            var visible: Int = 0,
            @SerializedName("zan")
            var zan: Int = 0
        )
    }
}