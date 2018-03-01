package com.dgtis.live.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dgtis.live.API;
import com.dgtis.live.SystemCache;
import com.dgtis.live.myapplication.R;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.tencent.rtmp.TXLiveConstants.ENCODE_VIDEO_AUTO;

/**
 * Created by DELLL on 2018/2/8.
 */

@ContentView(R.layout.activity_live)
public class LiveActivity extends BaseActivity implements ITXLivePushListener {

    @ViewInject(R.id.fl_live_set)
    private FrameLayout fl_live_set;

    @ViewInject(R.id.video_view)
    private TXCloudVideoView mCaptureView;


    private TXLivePushConfig mLivePushConfig;
    private TXLivePusher mLivePusher;
    private String rtmpUrl = "123";

    private int             customModeType = 0;
    private boolean         mFrontCamera = true;
    private int             mBeautyLevel = 5;
    private int             mWhiteningLevel = 3;
    private int             mRuddyLevel = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Event(R.id.live_start_bt)
    private void createLive(View view){
        Toast.makeText(this, "创建直播", Toast.LENGTH_SHORT).show();

        RequestParams params = new RequestParams(API.ROOT + API.PLAYLIVE);
        params.addQueryStringParameter("loginId", "13816542203");
        params.addQueryStringParameter("videoName", "123");
        params.addQueryStringParameter("videoIntroduce", "123");
        params.addQueryStringParameter("coverFile", "123");
        params.addQueryStringParameter("bespeakTime", "123");
        params.addQueryStringParameter("startTime", "");
        params.addQueryStringParameter("videoId", "123");
        params.addQueryStringParameter("videoMode", "0");
        params.addQueryStringParameter("videoMoney", "1");
        params.addQueryStringParameter("videoPwd", "2");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Toast.makeText(LiveActivity.this, "createLive success", Toast.LENGTH_SHORT).show();

                mLivePusher = new TXLivePusher(LiveActivity.this);
                mLivePushConfig = new TXLivePushConfig();
                mCaptureView.setVisibility(View.VISIBLE);

                mLivePushConfig.setHardwareAcceleration(ENCODE_VIDEO_AUTO);
                mLivePushConfig.setCustomModeType(customModeType);
                mLivePusher.setPushListener(LiveActivity.this);
                mLivePushConfig.setPauseImg(300,5);
                mLivePushConfig.setPauseFlag(TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO | TXLiveConstants.PAUSE_FLAG_PAUSE_AUDIO);
                mLivePushConfig.setFrontCamera(mFrontCamera);
                mLivePushConfig.setBeautyFilter(mBeautyLevel, mWhiteningLevel, mRuddyLevel);
                mLivePusher.setConfig(mLivePushConfig);
                if (checkPublishPermission()){
                    mLivePusher.startCameraPreview(mCaptureView);
                }else {
                    Toast.makeText(LiveActivity.this, "缺少必要权限", Toast.LENGTH_SHORT).show();
                }
                fl_live_set.setVisibility(View.GONE);
                mCaptureView.setVisibility(View.VISIBLE);
                mLivePusher.startPusher(rtmpUrl.trim());
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(LiveActivity.this, "createLive error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(LiveActivity.this, "createLive cancel", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                Toast.makeText(LiveActivity.this, "createLive finish", Toast.LENGTH_SHORT).show();
            }
        });
//        upLoadCoverImg();

    }


    /**
     * 上传图片
     */
    private void upLoadCoverImg(){

        File file = new File(SystemCache.RNFOLDER);
        RequestParams params = new RequestParams(API.ROOT + API.UPLOAD);
        params.setMultipart(true);
        params.addBodyParameter("fileArr",file);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(LiveActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(LiveActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(LiveActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                Toast.makeText(LiveActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 选择封面
     */
    private void chosePhoto(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 0);
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

    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this,
                        permissions.toArray(new String[0]),
                        100);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onPushEvent(int i, Bundle bundle) {

    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }
}
