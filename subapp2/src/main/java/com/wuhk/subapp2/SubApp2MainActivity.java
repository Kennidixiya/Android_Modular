package com.wuhk.subapp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;
import com.wuhk.library.BaseActivity;

/**
 * Created by wuhk on 2017/7/25.
 */

@Router("subapp2Main")
public class SubApp2MainActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subapp2_main_activity);

        Button goSubApp1Btn = (Button)findViewById(R.id.goSubApp1Btn);
        goSubApp1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routers.open(SubApp2MainActivity.this , "wuhk://subapp1Main");
            }
        });
    }
}
