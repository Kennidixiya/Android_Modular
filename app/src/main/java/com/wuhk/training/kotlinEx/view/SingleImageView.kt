package com.wuhk.training.kotlinEx.view

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.wuhk.training.R

/**
 * Created by wuhk on 2017/7/5.
 */
class SingleImageView : ImageView {
    companion object {
        val IMAGE_MODE_NORMAL: Int = 0
        val IMAGE_MODE_SINGLE: Int = 1
        val IMAGE_MODE_WIDTH_EQUALS_HEIGHT = 2
    }

    var suitableSize : Int = 0

    var imageMode: Int = IMAGE_MODE_NORMAL


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        suitableSize = context.resources.getDimension(R.dimen.dimen_180).toInt()
    }

    override fun setImageBitmap(bm: Bitmap?) {
        bm ?: return

        val bitmapWidth = bm.width
        val bitmapHeight = bm.height

        if (imageMode == IMAGE_MODE_SINGLE) {
            fixImageModeSingle(bitmapWidth, bitmapHeight)
        } else if (imageMode == IMAGE_MODE_WIDTH_EQUALS_HEIGHT) {
            fixImageModeEquals()
        }
        super.setImageBitmap(bm)
    }

    /**单图模式
     *
     */
    fun fixImageModeSingle(bitmapWidth: Int, bitmapHeight: Int) {
        scaleType = ScaleType.CENTER_CROP
        var lp: ViewGroup.LayoutParams = layoutParams;
        if (bitmapWidth > bitmapHeight) {
            if ((bitmapWidth / bitmapHeight) > 3) {
                //变态比例，宽远远大于高
                lp.width = suitableSize
                lp.height = suitableSize / 3
                layoutParams = lp
            } else {
                var scale : Float = bitmapWidth.toFloat() / bitmapHeight.toFloat()
                lp.width = suitableSize
                lp.height = (suitableSize / scale).toInt()
                layoutParams = lp
            }
        } else {
            if ((bitmapHeight / bitmapWidth) > 3) {
                //高远远大于宽
                lp.width = suitableSize / 3
                lp.height = suitableSize
                layoutParams = lp
            } else {
                var scale : Float = bitmapHeight.toFloat() / bitmapWidth.toFloat()
                lp.width = (suitableSize / scale).toInt()
                lp.height = suitableSize
                layoutParams = lp
            }
        }
    }

    /**宽高相等模式
     *
     */
    fun fixImageModeEquals() {
        scaleType = ScaleType.CENTER_CROP
        var lp: ViewGroup.LayoutParams = layoutParams
        lp.width = suitableSize
        lp.height = suitableSize
        layoutParams = lp
    }


}
