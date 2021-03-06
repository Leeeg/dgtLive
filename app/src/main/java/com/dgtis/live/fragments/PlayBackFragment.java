package com.dgtis.live.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dgtis.live.API;
import com.dgtis.live.Inners.GsonInner;
import com.dgtis.live.activity.VideoActivity;
import com.dgtis.live.adapters.RoomListAdapter;
import com.dgtis.live.common.ItemType;
import com.dgtis.live.model.RoomListItem;
import com.dgtis.live.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
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
    private String liveTitle, liveTime, liveCover;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                getRoomList();
                refreshLayout.setRefreshing(false);
            }
        });
        roomListAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                menuPopupWindow(view, i);
            }
        });
        roomListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                try {
                    String file = roomList.get(i).getRecFile();
                    JSONObject jsonObject = new JSONObject(file);
                    VideoActivity.startVideoActivity(getActivity(), jsonObject.getString("record_file_url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        getRoomList();
    }


    private void getRoomList(){
        roomList.clear();
        RequestParams params = new RequestParams(API.ROOT + API.BACKLIST);
        params.addQueryStringParameter("loginId","13621761774");
        params.addQueryStringParameter("rows","10");
        params.addQueryStringParameter("page","1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            //请求成功的回调方法
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("opState").equals("SUCCESS") && jsonObject.getJSONArray("opValue").length() > 0){
                        LogUtil.e(result);
                        for (int i = 0; i < jsonObject.getJSONArray("opValue").length(); i++) {
                            RoomListItem item = GsonInner.getGsonInstance().fromJson(jsonObject.getJSONArray("opValue").get(i).toString(), RoomListItem.class);
                            item.setItemType(ItemType.TYPE2);
                            roomList.add(item);
                        }
                    }else {
                        roomListAdapter.setEmptyView(View.inflate(getContext(), R.layout.empty, null));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    roomListAdapter.setEmptyView(View.inflate(getContext(), R.layout.empty, null));
                }
                roomListAdapter.notifyDataSetChanged();
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.d(ex.toString());
                roomListAdapter.setEmptyView(View.inflate(getContext(), R.layout.empty, null));
                roomListAdapter.notifyDataSetChanged();
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

    private void menuPopupWindow(View v, final int index) {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.layout_pop_menu, null);

        LinearLayout wx          = (LinearLayout) popView.findViewById(R.id.pop_wx);
        LinearLayout pyq         = (LinearLayout) popView.findViewById(R.id.pop_pyq);
        LinearLayout wb          = (LinearLayout) popView.findViewById(R.id.pop_wb);
        LinearLayout qq          = (LinearLayout) popView.findViewById(R.id.pop_qq);
        LinearLayout kj          = (LinearLayout) popView.findViewById(R.id.pop_kj);
        LinearLayout copy        = (LinearLayout) popView.findViewById(R.id.pop_copy);
        LinearLayout edit        = (LinearLayout) popView.findViewById(R.id.pop_edit);
        LinearLayout delete      = (LinearLayout) popView.findViewById(R.id.pop_delete);
        View empty               = popView.findViewById(R.id.pop_empty);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setAnimationStyle(R.style.PopBottom);
        popWindow.setFocusable(true);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.pop_wx:
                        Toast.makeText(getContext(), "微信分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pop_pyq:

                        Toast.makeText(getContext(), "朋友圈分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pop_wb:

                        Toast.makeText(getContext(), "微博分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pop_qq:

                        Toast.makeText(getContext(), "QQ分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pop_kj:

                        Toast.makeText(getContext(), "QQ空间分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pop_copy:
                        putTextIntoClip(getContext());
                        break;
                    case R.id.pop_edit:
                        editPopupWindow(v, index);
                        break;
                    case R.id.pop_delete:
                        RequestParams params = new RequestParams(API.ROOT + API.DELETEVIDEO);
                        params.addQueryStringParameter("videoId",roomList.get(index).getVideoId());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            //请求成功的回调方法
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                                roomListAdapter.notifyItemRemoved(index);
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                LogUtil.d(ex.toString());
                            }
                            //主动调用取消请求的回调方法
                            @Override
                            public void onCancelled(CancelledException cex) {
                            }
                            @Override
                            public void onFinished() {
                            }
                        });

                        break;
                    case R.id.pop_empty:


                        break;
                    default:
                        break;
                }
                popWindow.dismiss();

            }
        };
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        wx    .setOnClickListener(listener);
        pyq   .setOnClickListener(listener);
        wb    .setOnClickListener(listener);
        qq    .setOnClickListener(listener);
        kj    .setOnClickListener(listener);
        copy  .setOnClickListener(listener);
        edit  .setOnClickListener(listener);
        delete.setOnClickListener(listener);
        empty .setOnClickListener(listener);

        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }


    private void editPopupWindow(View v, final int index) {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.layout_pop_edit, null);

        TextView  cancel      = (TextView) popView.findViewById(R.id.pop_edit_cancel);
        Button    yes         = (Button) popView.findViewById(R.id.pop_edit_ok);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setAnimationStyle(R.style.PopBottom);
        popWindow.setFocusable(true);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.pop_edit_cancel:

                        break;
                    case R.id.pop_edit_ok:
                        RequestParams params = new RequestParams(API.ROOT + API.VIDEOEDIT);
                        params.addQueryStringParameter("videoId",roomList.get(index).getVideoId());
//                        params.addQueryStringParameter("videoName", );
//                        params.addQueryStringParameter("coverFile", );
//                        params.addQueryStringParameter("beapeakTime", );
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            //请求成功的回调方法
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                LogUtil.d(ex.toString());
                            }
                            //主动调用取消请求的回调方法
                            @Override
                            public void onCancelled(CancelledException cex) {
                            }
                            @Override
                            public void onFinished() {
                            }
                        });
                        break;
                    default:
                        break;
                }
                popWindow.dismiss();

            }
        };
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        cancel    .setOnClickListener(listener);
        yes       .setOnClickListener(listener);

        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }


    private void titlePopupWindow(View v, final int index) {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.layout_pop_edit, null);

        TextView  cancel      = (TextView) popView.findViewById(R.id.pop_edit_cancel);
        Button    yes         = (Button) popView.findViewById(R.id.pop_edit_ok);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setAnimationStyle(R.style.PopBottom);
        popWindow.setFocusable(true);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.pop_edit_cancel:

                        break;
                    case R.id.pop_edit_ok:

                        break;
                    default:
                        break;
                }
                popWindow.dismiss();

            }
        };
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

        cancel    .setOnClickListener(listener);
        yes       .setOnClickListener(listener);

        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    /**
     * copy
     * @param context
     */
    private void putTextIntoClip(Context context){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("text", "textCopy");
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(context, "已复制", Toast.LENGTH_SHORT).show();
    }
}
