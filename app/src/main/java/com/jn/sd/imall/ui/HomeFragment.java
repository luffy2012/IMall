package com.jn.sd.imall.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jn.sd.imall.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  implements ViewPager.OnPageChangeListener{
    private static final int PAGE_SIZE = 3;//主页广告切换栏总的广告数目
    private ViewPager mViewPager;//广告栏ViewPager
    private PagerAdapter mPagerAapter;//ViewPager的内容适配器
    private List<View> mList;//页面信息数组


    private ImageView[] tips;
    private ImageView[] mImageViews;
    private int[] imgIdArray ;


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

        /*mFirstView = inflater.inflate(R.layout.home_ads_page_view_first,
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
        mViewPager.setAdapter(new AdsViewPagerAdapter(mList));*/

        ViewGroup group = (ViewGroup)contactsLayout.findViewById(R.id.viewGroup);
        mViewPager = (ViewPager) contactsLayout.findViewById(R.id.home_mall_ads_view_pager);

        imgIdArray = new int[]{R.drawable.bg_test, R.drawable.bg_test, R.drawable.bg_test};
        tips = new ImageView[imgIdArray.length];
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10,10));
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }

        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }
        //设置Adapter
        mViewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        mViewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        mViewPager.setCurrentItem((mImageViews.length) * 100);


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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % mImageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        Log.i("dd",selectItems+"");
        for(int i=0; i<tips.length; i++){

            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
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

    public class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
//            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);

            }catch (Exception e){

            }
            return mImageViews[position % mImageViews.length];
        }



    }

}
