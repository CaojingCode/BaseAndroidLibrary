package com.kotlin.library.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.kotlin.baselibrary.BaseFragment
import com.kotlin.library.R
import com.kotlin.library.activity.DragActivity
import com.kotlin.library.http.ApiRepository
import kotlinx.android.synthetic.main.home_layout.view.*
import kotlinx.coroutines.*

/**
 * Created by Caojing on 2019/8/27.
 *  你不是一个人在战斗
 */
class IndexFragment : BaseFragment() {
    var repository: ApiRepository = ApiRepository()

    override fun layoutId(): Int {
        return R.layout.home_layout
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initView(view: View) {
        GlobalScope.launch {
            //等待3个异步接口请求完成后拿到返回值，延迟一秒再执行下一步操作
            val name = withContext(Dispatchers.Main) {
                val userInfo = repository.httpLogin("18672959660", "caojing901113")
                view.tv1.text = userInfo.toString()
                LogUtils.d("网络框架1$userInfo")
                val userInfo2 = repository.httpLogin("caojing", "123")
                view.tv2.text = userInfo2.toString()
                LogUtils.d("网络框架2$userInfo2")
                val userInfo3 = repository.httpLogin("caojing", "123")
                view.tv3.setText(userInfo3.toString())
                LogUtils.d("网络框架3$userInfo3")
                view.tv3.requestFocus(3)
                return@withContext "${userInfo.toString()}\n${userInfo2.toString()}\n" + userInfo3.toString()
            }
            delay(1000)
            LogUtils.d("网络框架$name")
        }
        view.actionJump.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    DragActivity::class.java
                )
            )
        }
    }


    /**
     * 是否禁止滑动返回
     */
    override fun canDragBack(): Boolean {
        return false
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}