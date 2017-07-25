package com.wuhk.subapp1;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wuhk.library.BaseActivity;

/**
 * Created by wuhk on 2017/7/17.
 */

public class SubApp1MainActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subapp1_main_activity);
    }
}
