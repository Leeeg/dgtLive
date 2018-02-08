package com.dgtis.live.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dgtis.live.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by DELLL on 2018/2/7.
 */

@ContentView(R.layout.activity_wallet)
public class WalletActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(R.id.tab_top_back)
    private void Close(View view){
        finish();
    }
}
