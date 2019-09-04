package com.kotlin.baselibrary

/**
 * Created by Caojing on 2019/9/4.
 *  你不是一个人在战斗
 */
abstract class BaseWebActivity : BaseActivity() {

    override fun layoutId(): Int {
        return 0
    }

    override fun initView() {

    }

    abstract fun getUrl(): String

}