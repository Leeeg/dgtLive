package com.dgtis.live.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgtis.live.common.ItemType;
import com.dgtis.live.model.RoomListItem;
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

        baseViewHolder.setOnClickListener(R.id.item_img_more, new OnItemChildClickListener());

        baseViewHolder.setText(R.id.title_room, roomListItem.getVideoName());
        Glide.with(context).load(roomListItem.getCover()).asBitmap().into((ImageView) baseViewHolder.getView(R.id.image_cover));
        Glide.with(context).load(roomListItem.getCover()).asBitmap().into((ImageView) baseViewHolder.getView(R.id.image_cover));
        if (baseViewHolder.getItemViewType() == ItemType.TYPE2){
            baseViewHolder.setText(R.id.time_room, roomListItem.getAddTime());
        }

    }

}
