package com.dgtis.live.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dgtis.live.SystemCache;
import com.dgtis.live.activity.WalletActivity;
import com.dgtis.live.imageVIew.CircleImageView;
import com.dgtis.live.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by DELLL on 2018/2/6.
 */


@ContentView(R.layout.fragment_personal)
public class PersonalFragment extends BaseFragment {

    @ViewInject(R.id.personal_nick)
    private TextView nickTxt;

    @ViewInject(R.id.personal_phone)
    private TextView phoneTxt;

    @ViewInject(R.id.personal_icon)
    private CircleImageView circleImageView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        if (null != SystemCache.getPersonal().getUserName() && !SystemCache.getPersonal().getUserName().isEmpty()){
            nickTxt.setText(SystemCache.getPersonal().getUserName());
        }
        if (null != SystemCache.getPersonal().getUserPhone() && !SystemCache.getPersonal().getUserPhone().isEmpty()){
            phoneTxt.setText("账号： " + SystemCache.getPersonal().getUserPhone());
        }
        if (null != SystemCache.getPersonal().getHeadImage() && !SystemCache.getPersonal().getHeadImage().isEmpty()){
            Glide.with(getActivity()).load("http://dgt.dgtis.com/oneportal/" + SystemCache.getPersonal().getHeadImage())
                    .into(circleImageView);
        }
    }

    @Event(R.id.l_wallet)
    private void Wallet(View view){
        Intent intent = new Intent(getActivity(), WalletActivity.class);
        startActivity(intent);
    }

}
