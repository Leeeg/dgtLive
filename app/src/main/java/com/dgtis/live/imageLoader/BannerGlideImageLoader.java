package com.dgtis.live.imageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dgtis.live.myapplication.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Lee on 2017/4/17.
 */

public class BannerGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
