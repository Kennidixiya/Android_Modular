package com.wuhk.training.kotlinEx.utils

import android.util.Log

/**
 * Created by wuhk on 2017/6/15.
 */

object LogUtil {
    var TAG = "AndroidTraining"

    fun d(content: String) {
        Log.e(TAG, content)
    }

    fun e(content: String) {
        Log.e(TAG, content)
    }


}
