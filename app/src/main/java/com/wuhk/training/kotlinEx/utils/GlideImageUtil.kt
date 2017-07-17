package com.wuhk.training.kotlinEx.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

/**
 * Glide图片加载工具类
 * Created by wuhk on 2016/10/20.
 */
object GlideImageUtil {

    /**
     * Glide设置图片

     * @param imageView
     * *
     * @param url
     * *
     * @param defaultResId
     */
    fun glideImage(context: Context, imageView: ImageView, url: String, defaultResId: Int) {
        Glide
                .with(context)
                .load(url).asBitmap()
                .centerCrop()
                .placeholder(defaultResId)
                .error(defaultResId)
                .into(imageView)
    }


    fun glideImageViewTarget(context : Context , imageView : ImageView, url: String , defaultResId: Int) {
        imageView.setImageResource(defaultResId)
        val target = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                imageView.setImageBitmap(resource)
            }
        }

        Glide
                .with(context) // safer!
                .load(url)
                .asBitmap()
                .placeholder(defaultResId)
                .into(target)
    }
}
