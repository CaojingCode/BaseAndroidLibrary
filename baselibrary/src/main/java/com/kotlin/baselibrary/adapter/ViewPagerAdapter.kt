package com.kotlin.baselibrary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Caojing on 2019/8/26.
 *  你不是一个人在战斗
 */
class ViewPagerAdapter(fm: FragmentManager?, fragments: MutableList<Fragment> = mutableListOf()) :
    FragmentPagerAdapter(fm) {

    private var fragments: List<Fragment> = fragments

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}