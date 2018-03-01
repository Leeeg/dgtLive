package com.dgtis.live.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgtis.live.fragments.HomeFragment;
import com.dgtis.live.fragments.PersonalFragment;
import com.dgtis.live.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by DELLL on 2018/1/30.
 */

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private PersonalFragment personalFragment;

    @ViewInject(R.id.bottom_live_icon)
    private ImageView liveImg;

    @ViewInject(R.id.bottom_live_text)
    private TextView liveTxt;

    @ViewInject(R.id.bottom_personal_img)
    private ImageView personalImg;

    @ViewInject(R.id.bottom_personal_text)
    private TextView personalTxt;

    @Event({R.id.bottom_live_icon, R.id.bottom_live_text})
    private void onLiveClick(View view){
        switchFragment(0);
    }

    @Event({R.id.bottom_personal_img, R.id.bottom_personal_text})
    private void onPersonalClick(View view){
        switchFragment(1);
    }

    @Event(R.id.bottom_play_img)
    private void playLive(View view){
        Intent intent = new Intent(HomeActivity.this, LiveActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchFragment(0);
        String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "需要访问手机存储权限！", 10086, perms);
        }
    }

    /**
     * switch the fragment accordting to id
     * @param i id
     */
    private void switchFragment(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (0 == i){
            liveImg.setSelected(true);
            liveTxt.setSelected(true);
            personalImg.setSelected(false);
            personalTxt.setSelected(false);
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                transaction.add(R.id.fragment_container, homeFragment);
            }else {
                transaction.show(homeFragment);
            }
        }else {
            liveImg.setSelected(false);
            liveTxt.setSelected(false);
            personalImg.setSelected(true);
            personalTxt.setSelected(true);
            if (personalFragment == null) {
                personalFragment = new PersonalFragment();
                transaction.add(R.id.fragment_container, personalFragment);
            }else {
                transaction.show(personalFragment);
            }
        }
        transaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if(personalFragment!=null){
            transaction.hide(personalFragment);
        }
    }

}
