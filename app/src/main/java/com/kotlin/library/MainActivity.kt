package com.kotlin.library

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.util.QMUIResHelper
import com.qmuiteam.qmui.widget.QMUITabSegment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : QMUIActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun initTabs() {
        val normalColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_6)
        val selectColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_blue)
        mTabSegment.setDefaultNormalColor(normalColor)
        mTabSegment.setDefaultSelectedColor(selectColor)

        val indexPage = QMUITabSegment.Tab(
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_index_v2),
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_index_select_v2),
            "首页", false
        )
        val systemPage = QMUITabSegment.Tab(
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_information_v2),
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_information_select_v2),
            "体系", false
        )
        val projectPage = QMUITabSegment.Tab(
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_mess_v2),
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_mess_select_v2),
            "项目", false
        )
        val myPage = QMUITabSegment.Tab(
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_my_v2),
            ContextCompat.getDrawable(this, R.mipmap.img_bottom_my_select_v2),
            "我的", false
        )
        mTabSegment.addTab(indexPage).addTab(systemPage).addTab(projectPage).addTab(myPage)
    }


    fun initViewPages(){

    }

    override fun canDragBack(): Boolean {
        return false
    }
}

enum class Pager {
    INDEX, SYSTEM, PROJECT, MY;

    companion object {
        fun getPagerFromPositon(position: Int): Pager {
            return when (position) {
                0 -> INDEX
                1 -> SYSTEM
                2 -> PROJECT
                3 -> MY
                else -> INDEX
            }
        }
    }
}

