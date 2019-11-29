package com.kotlin.library.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.library.R
import com.kotlin.library.enity.MenuInfo

/**
 *
 * Created by Caojing on 2019/9/29.
 *  你不是一个人在战斗
 */
class MenuAdapter : BaseQuickAdapter<MenuInfo, BaseViewHolder>(R.layout.item_menu) {
    override fun convert(helper: BaseViewHolder, item: MenuInfo) {
        helper.setImageResource(R.id.ivMenu, item.imageId)
        helper.setText(R.id.tvMenu, item.name)
    }
}