package com.wuhk.training.kotlinEx.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wuhk.training.App


/**
 * Created by wuhk on 2017/7/7.
 */
object ContextUtil {
    fun showSoftInput(view : View , isShow : Boolean){
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()

        val handler = Handler(Looper.getMainLooper())

        var imm : InputMethodManager = App.instance!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(isShow){
            handler.post {
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }else{
            imm.hideSoftInputFromWindow(view.windowToken , 0)
        }

    }
}