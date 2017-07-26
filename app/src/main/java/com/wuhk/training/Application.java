package com.wuhk.training;

import com.github.mzule.activityrouter.annotation.Modules;

/**
 * Created by starry on 2016/5/23.
 */
@Modules({"index" , "subapp1" , "subapp2"})
public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }
}
