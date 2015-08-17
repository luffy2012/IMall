package com.jn.sd.imall.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jn.sd.imall.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private static final int PAGE_SIZE = 3;//主页广告切换栏总的广告数目
    private ViewPager mViewPager;//广告栏ViewPager
    private PagerAdapter mPagerAapter;//ViewPager的内容适配器
    private List<View> mList;//页面信息数组

    private View mFirstView,mSencondView,mThirdView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 初始化 mViewPager


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contactsLayout = inflater.inflate(R.layout.fragment_home,
                container, false);

        mFirstView = inflater.inflate(R.layout.home_ads_page_view_first,
                container, false);
        mSencondView = inflater.inflate(R.layout.home_ads_page_view_second,
                container, false);
        mThirdView = inflater.inflate(R.layout.home_ads_page_view_third,
                container, false);
        mList = new ArrayList<>(PAGE_SIZE);
        mList.add(mFirstView);
        mList.add(mSencondView);
        mList.add(mThirdView);
        mViewPager = (ViewPager)contactsLayout.findViewById(R.id.home_mall_ads_view_pager);
        mViewPager.setAdapter(new AdsViewPagerAdapter(mList));

        return contactsLayout;

        /** add by wangss
        View contactsLayout = inflater.inflate(R.layout.home_tools_bar,
                container, false);

        mFirstView = inflater.inflate(R.layout.home_ads_page_view_first,
                container, false);
        mSencondView = inflater.inflate(R.layout.home_ads_page_view_second,
                container, false);
        mThirdView = inflater.inflate(R.layout.home_ads_page_view_third,
                container, false);
        mList = new ArrayList<>(PAGE_SIZE);
        mList.add(mFirstView);
        mList.add(mSencondView);
        mList.add(mThirdView);
        mViewPager = (ViewPager)contactsLayout.findViewById(R.id.home_mall_ads_view_pager);
        mViewPager.setAdapter(new AdsViewPagerAdapter(mList));

        return contactsLayout;
         */
    }

    private void initViews(View view) {

    }

    private class AdsViewPagerAdapter extends PagerAdapter{

        private List<View> mListViews;

        public AdsViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position),0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

}
