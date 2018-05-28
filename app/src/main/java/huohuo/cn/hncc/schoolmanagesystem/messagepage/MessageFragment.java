package huohuo.cn.hncc.schoolmanagesystem.messagepage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/2.
 */

public class MessageFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mList_fragment;
    private List<String> mList_tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        initServiceData();
        initView(view);
        initData();
        return view;
    }


    private void initServiceData() {

    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_messageMain);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_messageMain);
    }

    private void initData() {
        mList_fragment = new ArrayList<>();
        mList_fragment.add(new MessageChildFragment());
        mList_fragment.add(new FriendChildFragment());
        mList_tab = new ArrayList<>();
        mList_tab.add("消息");
        mList_tab.add("好友");
        int i = 0;
        while (i<mList_tab.size()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mList_tab.get(i)));
            i++;
        }

        MessageAdapter mAdapter = new MessageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    class MessageAdapter extends FragmentPagerAdapter {
        public MessageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList_fragment.get(position);
        }

        @Override
        public int getCount() {
            return mList_fragment.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mList_tab.get(position);
        }
    }
}
