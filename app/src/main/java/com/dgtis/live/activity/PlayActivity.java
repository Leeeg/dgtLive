package com.dgtis.live.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dgtis.live.API;
import com.dgtis.live.myapplication.R;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * Created by DELLL on 2018/1/4.
 */

public class PlayActivity extends Activity implements ITXLivePlayListener {

//    private final String flvUrl = "http://19105.liveplay.myqcloud.com/live/19105_4a806bf493.flv";
    private String flvUrl;
    private TXCloudVideoView mView;
    private TXLivePlayer mLivePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);

        //mPlayerView即step1中添加的界面view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);

        //创建player对象
        mLivePlayer = new TXLivePlayer(this);

        mLivePlayer.setPlayListener(this);

        //关键player对象与界面view
        mLivePlayer.setPlayerView(mView);

        flvUrl = getIntent().getStringExtra("liveCode");
        if (null != flvUrl){
            String url = API.LIVE_ROOT + flvUrl + ".flv";
            mLivePlayer.startPlay(url, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐FLV
        }else {
            Toast.makeText(this, "地址有误!", Toast.LENGTH_SHORT).show();
        }
    }

    public static void startPlayActivity(Activity activity, String liveCode){
        Intent intent = new Intent(activity, PlayActivity.class);
        intent.putExtra("liveCode", liveCode);
        activity.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        mView.onDestroy();
        mLivePlayer.setPlayListener(null);
    }

    @Override
    public void onPlayEvent(int i, Bundle bundle) {
        Toast.makeText(this, "playEvent code : " + i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }
}
