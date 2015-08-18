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
    private List<View> mImageViews;//ViewPaper广告图片
    private int[] imgIdArray;//ViewPager广告图片资源ID

//    private ScheduledExecutorService mScheduledSer;//定时器，定时处理广告的切换操作
    private int currentItem = (3) * 100;//当前


    //用来指示当前显示图片所在位置
    private LinearLayout viewIndicator;


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


        viewIndicator = (LinearLayout) contactsLayout.findViewById(R.id.vpindicator);
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
            viewIndicator.addView(imageView, layoutParams);
        }


        //初始化广告的图片
        mImageViews  = new ArrayList<View>();
        for (int i = 0; i < imgIdArray.length; i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            mImageViews.add(imageView);
            imageView.setBackgroundResource(imgIdArray[i]);
        }
        //设置Adapter
        mViewPager.setAdapter(new AdsViewPagerAdapter(mImageViews));
        //设置监听，主要是设置点点的背景
        mViewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
//        mViewPager.setCurrentItem((mImageViews.length) * 100);

//        startTimerTask();
        return contactsLayout;
    }


    @Override
    public void onResume() {
        super.onResume();
        //activity启动两秒钟后，发送一个message，用来将viewPager中的图片切换到下一个
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止viewPager中图片的自动切换
        mHandler.removeMessages(1);
    }
    private void startTimerTask() {
//                mScheduledSer = Executors.newSingleThreadScheduledExecutor();
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
//        setImageBackground(position % mImageViews.length);

        int len = viewIndicator.getChildCount();
        for(int i = 0; i < len; ++i)
            viewIndicator.getChildAt(i).setBackgroundResource(R.drawable.page_indicator_unfocused);
           viewIndicator.getChildAt(position).setBackgroundResource(R.drawable.page_indicator_focused);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //设置背景
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
        private List<View> mData;

        public AdsViewPagerAdapter(List<View> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

            container.removeView(mData.get(position));

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            try {
//                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
//
//            } catch (Exception e) {
//
//            }
//            return mImageViews[position % mImageViews.length];

            View v = mData.get(position);
            container.addView(v);
            return v;

        }


    }

    private class ViewPagerTask implements Runnable {


        public void run() {
            //实现我们的操作
            //改变当前页面
            Log.i("aa", currentItem + "");
//            currentItem = (currentItem + 1) % mImageViews.length;
            Log.i("cc", currentItem + "");
            //Handler来实现图片切换
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

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


//    private Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch(msg.what) {
//                case 1:
//                    int totalcount = mViewPager.size();//autoChangeViewPager.getChildCount();
//                    int currentItem = autoChangeViewPager.getCurrentItem();
//
//                    int toItem = currentItem + 1 == totalcount ? 0 : currentItem + 1;
//
//                    Log.i(TAG, "totalcount: " + totalcount + "   currentItem: " + currentItem + "   toItem: " + toItem);
//
//                    autoChangeViewPager.setCurrentItem(toItem, true);
//
//                    //每两秒钟发送一个message，用于切换viewPager中的图片
//                    this.sendEmptyMessageDelayed(1, 2000);
//            }
//        }
//    };


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch(msg.what) {
                case 1:
                    int totalcount = mImageViews.size();//autoChangeViewPager.getChildCount();
                    int currentItem = mViewPager.getCurrentItem();

                    int toItem = currentItem + 1 == totalcount ? 0 : currentItem + 1;

//                    Log.i(TAG, "totalcount: " + totalcount + "   currentItem: " + currentItem + "   toItem: " + toItem);

                    mViewPager.setCurrentItem(toItem, true);

                    //每两秒钟发送一个message，用于切换viewPager中的图片
                    this.sendEmptyMessageDelayed(1, 2000);
            }
        }
    };

}