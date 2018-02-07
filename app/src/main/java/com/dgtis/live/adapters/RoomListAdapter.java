package com.dgtis.live.adapters;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgtis.live.common.ItemType;
import com.dgtis.live.myapplication.R;

import java.util.List;

/**
 * Created by DELLL on 2018/1/31.
 */

public class RoomListAdapter extends BaseMultiItemQuickAdapter<RoomListItem> {

    private Context context;

    public RoomListAdapter(Context context, List data) {
        super(data);
        this.context = context;

        addItemType(ItemType.TYPE1, R.layout.item_liveroom);
        addItemType(ItemType.TYPE2, R.layout.item_playback);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RoomListItem roomListItem) {

    }
}
