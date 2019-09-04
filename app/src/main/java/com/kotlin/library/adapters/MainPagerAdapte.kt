package com.kotlin.library.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Caojing on 2019/8/28.
 *  你不是一个人在战斗
 */
class MainPagerAdapte(fm: FragmentManager, fagments: MutableList<Fragment>) :
    FragmentPagerAdapter(fm) {

    private var fagments = mutableListOf<Fragment>()

    init {
        this.fagments = fagments
    }

    override fun getItem(position: Int): Fragment {
        return fagments[position]
    }

    override fun getCount(): Int {
        return fagments.size
    }


}