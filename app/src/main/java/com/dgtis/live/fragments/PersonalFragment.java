package com.dgtis.live.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dgtis.live.activity.WalletActivity;
import com.dgtis.live.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by DELLL on 2018/2/6.
 */


@ContentView(R.layout.fragment_personal)
public class PersonalFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Event(R.id.l_wallet)
    private void Wallet(View view){
        Intent intent = new Intent(getActivity(), WalletActivity.class);
        startActivity(intent);
    }

}
