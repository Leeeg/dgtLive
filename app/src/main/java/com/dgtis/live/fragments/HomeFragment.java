package com.dgtis.live.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.dgtis.live.adapters.BaseFragmentAdapter;
import com.dgtis.live.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELLL on 2018/2/6.
 */


@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    private List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentAdapter baseAdapter;

    @ViewInject(R.id.tab_layout)
    private XTabLayout tabLayout;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("直播预约");
        titles.add("精彩回顾");
        fragments.add(new AppointmentFragment());
        fragments.add(new PlayBackFragment());
        tabLayout.addTab(tabLayout.newTab().setText("直播预约"));
        tabLayout.addTab(tabLayout.newTab().setText("精彩回顾"));
        baseAdapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(baseAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(baseAdapter);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

}
