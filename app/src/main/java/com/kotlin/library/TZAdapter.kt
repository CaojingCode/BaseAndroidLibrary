package com.kotlin.library

import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate

class TZAdapter : BaseItemDraggableAdapter<TZBean, BaseViewHolder>(null) {

    init {
        multiTypeDelegate = object : MultiTypeDelegate<TZBean>() {
            override fun getItemType(t: TZBean): Int {
                return t.type
            }

        }
        multiTypeDelegate.registerItemType(1, R.layout.item_text)
            .registerItemType(2, R.layout.item_image)
    }

    override fun convert(helper: BaseViewHolder, item: TZBean?) {
        when (helper.itemViewType) {
            1 -> {
                helper.setText(R.id.tvTittle, item?.text)
            }
            2 -> {
                helper.setImageResource(R.id.ivImage, R.mipmap.ic_launcher)

            }
        }
    }


}