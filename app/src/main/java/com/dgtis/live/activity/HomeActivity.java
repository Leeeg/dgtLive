package com.dgtis.live.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgtis.live.API;
import com.dgtis.live.SystemCache;
import com.dgtis.live.fragments.HomeFragment;
import com.dgtis.live.fragments.PersonalFragment;
import com.dgtis.live.myapplication.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by DELLL on 2018/1/30.
 */

@ContentView(R.layout.activity_main)
public class HomeActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private PersonalFragment personalFragment;

    @ViewInject(R.id.bottom_play_img)
    private ImageView liveImg;

    @ViewInject(R.id.bottom_live_text)
    private TextView liveTxt;

    @ViewInject(R.id.bottom_personal_img)
    private ImageView personalImg;

    @ViewInject(R.id.bottom_personal_text)
    private TextView personalTxt;

    @Event({R.id.bottom_live_img, R.id.bottom_live_text})
    private void onLiveClick(View view){
        switchFragment(0);
    }

    @Event({R.id.bottom_personal_img, R.id.bottom_personal_text})
    private void onPersonalClick(View view){
        switchFragment(1);
    }

    @Event(R.id.bottom_play_img)
    private void playLive(View view){
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        intent.putExtra("crop", true);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 0);


        File file = new File(SystemCache.RNFOLDER);
//        params.addBodyParameter("fileArr", file);
        RequestParams params = new RequestParams(API.ROOT + API.UPLOAD);
        params.setMultipart(true);
        params.addBodyParameter("fileArr",file);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(HomeActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(HomeActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                Toast.makeText(HomeActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //picturePath就是图片在储存卡所在的位置
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

        }
    }
}
