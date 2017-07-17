package com.wuhk.training.kotlinEx.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.wuhk.training.R
import com.wuhk.training.kotlinEx.entity.CircleHeadEntity
import com.wuhk.training.kotlinEx.utils.GlideImageUtil
import kotlinx.android.synthetic.main.view_cirlce_head.view.*

/**
 * 圈子头部View
 * Created by wuhk on 2017/7/6.
 */
class CircleHeadView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_cirlce_head, this)
    }

    fun bindData(headEntity: CircleHeadEntity) {
        nameTv.text = headEntity.userName
        GlideImageUtil.glideImage(context, avatarIv as ImageView, headEntity.headUrl.toString(), R.mipmap.default_avatar)
        GlideImageUtil.glideImage(context, headBgIv as ImageView, headEntity.headBgUrl.toString(), R.mipmap.default_img)
    }


}