package com.dgtis.live.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgtis.live.API;
import com.dgtis.live.adapters.RoomListAdapter;
import com.dgtis.live.adapters.RoomListItem;
import com.dgtis.live.common.ItemType;
import com.dgtis.live.myapplication.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELLL on 2018/2/6.
 */


@ContentView(R.layout.layout_recycleview)
public class PlayBackFragment extends BaseFragment {

    @ViewInject(R.id.recycle_view)
    private RecyclerView recyclerView;

    @ViewInject(R.id.swipe_fresh)
    private SwipeRefreshLayout refreshLayout;

    private RoomListAdapter roomListAdapter;
    private List<RoomListItem> roomList = new ArrayList();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRoomList();
    }

    private void initRoomList() {
        for (int i = 0; i < 10; i++) {
            RoomListItem item = new RoomListItem();
            item.setItemType(ItemType.TYPE2);
            roomList.add(item);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        roomListAdapter = new RoomListAdapter(getActivity(), roomList);
        recyclerView.setAdapter(roomListAdapter);
        refreshLayout.setColorSchemeResources(
                R.color.color_red,
                R.color.color_gray,
                R.color.colorPrimary,
                R.color.color_black
        );
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });
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
