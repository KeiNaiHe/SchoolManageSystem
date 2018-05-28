package huohuo.cn.hncc.schoolmanagesystem.dynamicpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/19.
 */

public class DynamicMainFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mList_fragment;
    private List<String> mList_tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamicmain, null);
        initServiceData();
        initView(view);
        initData();
        return view;
    }


    private void initData() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList_fragment.get(position);
            }

            @Override
            public int getCount() {
                return mList_fragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mList_tab.get(position);
            }
        });
        //第二个参数为自动刷新
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
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tl_dynamicMain_toolBar);
        for (int i = 0; i < mList_tab.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mList_tab.get(i)));
        }

        mViewPager = view.findViewById(R.id.viewPager_dynamicMain);

    }

    private void initServiceData() {
        mList_fragment = new ArrayList<>();
        mList_fragment.add(new AttentionFragment());
        mList_fragment.add(new SchoolmateFragment());

        mList_tab = new ArrayList<>();
        mList_tab.add("关注");
        mList_tab.add("同校");
    }


}
