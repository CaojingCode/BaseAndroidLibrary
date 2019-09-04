package com.kotlin.library.fragments

import android.view.LayoutInflater
import android.view.View
import com.kotlin.library.R
import com.qmuiteam.qmui.arch.QMUIFragment

/**
 * Created by Caojing on 2019/8/27.
 *  你不是一个人在战斗
 */
class SecondFragment : QMUIFragment() {
    override fun onCreateView(): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.second_layout, null)
        return view
    }

    override fun canDragBack(): Boolean {
        return false
    }
}