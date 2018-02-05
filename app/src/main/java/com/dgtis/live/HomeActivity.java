package com.dgtis.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dgtis.live.adapters.RoomListAdapter;
import com.dgtis.live.adapters.RoomListItem;
import com.dgtis.live.imageLoader.BannerGlideImageLoader;
import com.dgtis.live.myapplication.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by DELLL on 2018/1/30.
 */

@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @ViewInject(R.id.banner)
    private Banner banner;

    @ViewInject(R.id.recycle_room_list)
    private RecyclerView recyclerView;

    private RoomListAdapter roomListAdapter;
    private List<String> bannerList = new ArrayList();
    private List<RoomListItem> roomList = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(this, true);

        initBanner();
        initRoomList();
    }

    private void initRoomList() {
        for (int i = 0; i < 10; i++) {
            roomList.add(new RoomListItem());
        }
        Toast.makeText(this, roomList.size()+"", Toast.LENGTH_SHORT).show();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        roomListAdapter = new RoomListAdapter(this, roomList);
        recyclerView.setAdapter(roomListAdapter);
    }

    private void initBanner() {
        bannerList.add("file:///android_asset/cat1.jpg");
        bannerList.add("file:///android_asset/cat2.jpg");
        bannerList.add("file:///android_asset/cat3.jpg");
        bannerList.add("file:///android_asset/cat4.jpg");
        banner.setVisibility(View.VISIBLE);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置图片集合
        banner.setImages(bannerList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void getRoomList(){
        RequestParams params = new RequestParams(API.ROOT + API.LOGIN);
        params.addQueryStringParameter("id","1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            //请求成功的回调方法
            @Override
            public void onSuccess(String result) {
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
}
