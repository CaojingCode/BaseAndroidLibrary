package com.kotlin.baselibrary

/**
 * Created by Caojing on 2019/8/21.
 *  你不是一个人在战斗
 */
object AppConfig {
    const val TAG = "BaseAndroid"
    const val timeOut=60
    const val baseUrl="https://www.wanandroid.com"
}

fun <T> MutableList<T>.addBy(t: T): MutableList<T> {
    this.add(t)
    return this
}