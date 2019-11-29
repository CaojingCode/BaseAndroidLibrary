package com.kotlin.baselibrary.bean

/**
 *
 * Created by Caojing on 2019/11/211812
 *
 */
data class BaseInfo<T>(
    var code: Int = 0,
    var message: String = "",
    var data: T
)