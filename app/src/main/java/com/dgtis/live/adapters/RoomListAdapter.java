package com.dgtis.live.adapters;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgtis.live.myapplication.R;

import java.util.List;

/**
 * Created by DELLL on 2018/1/31.
 */

public class RoomListAdapter extends BaseQuickAdapter<RoomListItem> {

    private Context context;

    public RoomListAdapter(Context context, List data) {
        super(R.layout.item_liveroom, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RoomListItem roomListItem) {

    }
}
