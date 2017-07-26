package com.wuhk.subapp1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;
import com.wuhk.library.BaseActivity;

/**
 * Created by wuhk on 2017/7/17.
 */
@Router("subapp1Main")
public class SubApp1MainActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subapp1_main_activity);

        Button goSubApp2Btn = (Button)findViewById(R.id.goSubApp2Btn);
        goSubApp2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routers.open(SubApp1MainActivity.this  , "wuhk://subapp2Main");
            }
        });
    }
}
