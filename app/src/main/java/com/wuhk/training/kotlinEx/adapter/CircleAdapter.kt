package com.wuhk.training.kotlinEx.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.wuhk.training.kotlinEx.entity.BaseEntity
import com.wuhk.training.kotlinEx.entity.CircleContentEntity
import com.wuhk.training.kotlinEx.entity.CircleHeadEntity
import com.wuhk.training.kotlinEx.view.CircleContentView
import com.wuhk.training.kotlinEx.view.CircleHeadView

/**
 * Created by wuhk on 2017/7/6.
 */
class CircleAdapter(val context : Context, val items : MutableList<BaseEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TYPE_HEAD = 1
    val TYPE_CONTENT = 2

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEAD){
            val  circleHeadView : CircleHeadView = CircleHeadView(context)
            return TopViewHolder(circleHeadView)
        }else{
            val circleContentView : CircleContentView = CircleContentView(context)
            return ContentViewHolder(circleContentView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val entity = items[position]
        if (entity is CircleHeadEntity){
            (holder!!.itemView as CircleHeadView).bindData(entity)
        }else if (entity is CircleContentEntity){
            (holder!!.itemView as CircleContentView).bindData(entity , position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return TYPE_HEAD
        }else{
            return TYPE_CONTENT
        }
    }

    class TopViewHolder(view : View) : RecyclerView.ViewHolder(view)

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view)
}