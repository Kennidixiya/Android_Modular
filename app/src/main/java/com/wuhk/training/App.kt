package com.wuhk.training

import android.app.Application

/**
 * Created by wuhk on 2017/6/15.
 */

class App : Application(){
    companion object {
        var instance : App? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
