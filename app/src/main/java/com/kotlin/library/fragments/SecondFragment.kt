package com.kotlin.library.fragments

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.kotlin.baselibrary.BaseFragment
import com.kotlin.library.R
import com.kotlin.library.adapters.MenuAdapter
import com.kotlin.library.enity.MenuInfo
import kotlinx.android.synthetic.main.second_layout.view.*

/**
 * Created by Caojing on 2019/8/27.
 *  你不是一个人在战斗
 */
class SecondFragment : BaseFragment() {

    var arrayName = arrayListOf("我的", "你的", "他的", "谁的", "我的", "你的", "他的", "谁的", "我的", "你的","我的", "你的", "他的", "谁的", "我的", "你的", "他的", "谁的", "我的", "你的")
    var lineNum = 2
    var row = 3

    override fun initView(view: View) {
        view.rlMenu.layoutManager = GridLayoutManager(activity, 2, RecyclerView.HORIZONTAL, false)
        val adapter = MenuAdapter()
        view.rlMenu.adapter = adapter
        adapter.setNewData(getMenuList())
        view.blv.post {
            view.blv.setMoveDistance(
                0f,
                ((lineNum * row * view.blv.width) / arrayName.size).toFloat()
            )
        }

        view.rlMenu.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                view.blv.setMoveDistance(
                    view.rlMenu.computeHorizontalScrollOffset() * view.blv.endX / ScreenUtils.getScreenWidth(),
                    ((lineNum * row * view.blv.width) / arrayName.size).toFloat()
                )
            }
        })
    }


    private fun getMenuList(): MutableList<MenuInfo> {
        val menus = mutableListOf<MenuInfo>()
        for (name in arrayName) {
            val menu = MenuInfo(name)
            menus.add(menu)
        }
        return menus
    }

    override fun layoutId(): Int {
        return R.layout.second_layout
    }


    override fun canDragBack(): Boolean {
        return false
    }


}