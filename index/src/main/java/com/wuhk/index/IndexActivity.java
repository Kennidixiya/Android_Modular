package com.wuhk.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.router.Routers;
import com.wuhk.library.BaseActivity;

/**
 * Created by wuhk on 2017/7/26.
 */

public class IndexActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);
        Button subapp1Btn = (Button)findViewById(R.id.subapp1Btn);
        subapp1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routers.open(IndexActivity.this, "wuhk://subapp1Main");

            }
        });
        Button subapp2Btn = (Button)findViewById(R.id.subapp2Btn);
        subapp2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routers.open(IndexActivity.this, "wuhk://subapp2Main");

            }
        });
    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.subapp1Btn:
//                break;
//            case R.id.subapp2Btn:
//                break;
//            default:
//                break;
//        }
//    }
}
