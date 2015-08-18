package com.jn.sd.imall.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jn.sd.imall.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;//广告栏ViewPager

    private ImageView[] tips;//圆点图标图片数组
    private ImageView[] mImageViews;//ViewPaper广告图片
    private int[] imgIdArray;//ViewPager广告图片资源ID

    private ScheduledExecutorService mScheduledSer;//定时器，定时处理广告的切换操作
    private int currentItem = (3) * 100;//当前


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contactsLayout = inflater.inflate(R.layout.fragment_home,
                container, false);
        /***  动态指定ViewPaper的大小 start by luffy  **/
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Resources res = getResources();
        Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.bg_test);
        int tempWidth = bm.getWidth();
        int tempHeight = bm.getHeight();
        RelativeLayout layout = (RelativeLayout) contactsLayout.findViewById(R.id.viewpaper_layout);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.height = (int) (tempHeight * ((double) dm.widthPixels / (double) tempWidth));
        layout.setLayoutParams(params);
        /***  动态指定ViewPaper的大小 end  by luffy  **/


        ViewGroup group = (ViewGroup) contactsLayout.findViewById(R.id.viewGroup);
        mViewPager = (ViewPager) contactsLayout.findViewById(R.id.home_mall_ads_view_pager);

        imgIdArray = new int[]{R.drawable.bg_test, R.drawable.bg_test, R.drawable.bg_test};
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 1;
            layoutParams.rightMargin = 1;
            group.addView(imageView, layoutParams);
        }


        //初始化广告的图片
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }
        //设置Adapter
        mViewPager.setAdapter(new AdsViewPagerAdapter());
        //设置监听，主要是设置点点的背景
        mViewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        mViewPager.setCurrentItem((mImageViews.length) * 100);

        startTimerTask();
        return contactsLayout;
    }

    private void startTimerTask() {
        //        mScheduledSer = Executors.newSingleThreadScheduledExecutor();
        //通过定时器 来完成 每2秒钟切换一个图片
        //经过指定的时间后，执行所指定的任务
        //scheduleAtFixedRate(command, initialDelay, period, unit)
        //command 所要执行的任务
        //initialDelay 第一次启动时 延迟启动时间
        //period  每间隔多次时间来重新启动任务
        //unit 时间单位
//        mScheduledSer.scheduleAtFixedRate(new ViewPagerTask(), 1, 1, TimeUnit.SECONDS);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        setImageBackground(position % mImageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    public class AdsViewPagerAdapter extends PagerAdapter {

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
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);

            } catch (Exception e) {

            }
            return mImageViews[position % mImageViews.length];
        }


    }

    private class ViewPagerTask implements Runnable {
        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                //设定viewPager当前页面
                switch (msg.what) {
                    case 1:
                        mViewPager.setCurrentItem(currentItem);
                        Log.i("bb", currentItem + "");
                        break;

                }

            }
        };

        public void run() {
            //实现我们的操作
            //改变当前页面
            Log.i("aa", currentItem + "");
            currentItem = (currentItem + 1) % mImageViews.length;
            Log.i("cc", currentItem + "");
            //Handler来实现图片切换
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }
}