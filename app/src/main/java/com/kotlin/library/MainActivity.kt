package com.kotlin.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import kotlinx.android.synthetic.main.activity_main.*
import xcrash.XCrash
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    val adapter = TZAdapter()
    private val itemDragAndSwipeCallback = object : ItemDragAndSwipeCallback(adapter) {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            //这里可以限制某些特定的item不支持长按拖拽，
            if (viewHolder.itemViewType == 1) {
                //如果是文字类型
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0)
            } else {
                if (viewHolder.adapterPosition == 9) {
                    //这里只是一个实例，限制第9个item不支持拖拽，目前写死，可以根据业务逻辑来自行处理
                    return ItemTouchHelper.Callback.makeMovementFlags(0, 0)
                }
            }
            return super.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            source: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            //这里限制拖动结束后，不容许交换的item位置，目前写死，可以根据业务逻辑来自行处理
            if (target.adapterPosition == 9) {
                return false
            }
            return super.onMove(recyclerView, source, target)
        }
    }

    private val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rlDrag.layoutManager = GridLayoutManager(this, 3)
        rlDrag.adapter = adapter
        adapter.enableDragItem(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rlDrag)

        buttonAction.setOnClickListener {
            ScreenUtils.screenShot(this)
        }

        adapter.setOnItemDragListener(object : OnItemDragListener {
            override fun onItemDragMoving(
                source: RecyclerView.ViewHolder?,
                from: Int,
                target: RecyclerView.ViewHolder?,
                to: Int
            ) {
            }

            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            }

        })


        adapter.setOnItemLongClickListener { adapter2, view, position ->

            if (adapter.data[position].type == 2) {

            }
            return@setOnItemLongClickListener false
        }

        adapter.setSpanSizeLookup { gridLayoutManager, position ->
            var type = adapter.data[position].type
            when (type) {
                1 -> return@setSpanSizeLookup 3
                else -> return@setSpanSizeLookup 1
            }

        }
        updateData()
    }


    /**
     * recleView本地数据
     */
    fun updateData() {

        for (i in 0..100) {
            var bean = TZBean()
            when (i) {
                0, 10, 20, 30, 40, 50, 60, 70, 80, 90 -> {
                    bean = TZBean(1, "我是测试数据图片类型很长的额$i\n第二行", "", 1)

                }
                else -> {
                    bean = TZBean(
                        2,
                        "",
                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565495530817&di=b8d3324493c0a557f7ed280605570695&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D3570798179%2C2355932790%26fm%3D214%26gp%3D0.jpg",
                        3
                    )
                }
            }
            adapter.addData(bean)
        }
    }
}

