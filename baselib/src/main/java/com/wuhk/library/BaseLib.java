package com.wuhk.library;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by wuhk on 2017/7/24.
 */

public abstract class BaseLib {
    public static Context application;

    public static void init(Context context){
        if (null == context){
            return;
        }
        if (context instanceof Activity){
            application = context.getApplicationContext();
        }else if (context instanceof Application){
            application = context;
        }

    }

    public static Context getApplicationContext() {
        return application;
    }
}
