package com.kotlin.baselibrary


/**
 * Created by Caojing on 2019/8/21.
 *  你不是一个人在战斗
 */
object AppConfig {
    const val TAG = "BaseAndroid"
    const val timeOut = 60
    const val baseUrl = "https://www.wanandroid.com"
}

fun <T> MutableList<T>.addBy(t: T): MutableList<T> {
    this.add(t)
    return this
}

//自定义扩展属性，
var <T> MutableList<T>.lastData: T
    //获取集合中最后一个对象
    get() = this[this.size - 1]
    //设置集合中最后一个对象的值
    set(value) {
        this[this.size - 1] = value
    }


fun String.tolenght(): Int {
    return length
}
