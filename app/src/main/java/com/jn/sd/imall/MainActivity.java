package com.jn.sd.imall;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jn.sd.imall.R;
import com.jn.sd.imall.ui.CarFragment;
import com.jn.sd.imall.ui.CatagoryFragment;
import com.jn.sd.imall.ui.DiscoverFragment;
import com.jn.sd.imall.ui.HomeFragment;
import com.jn.sd.imall.ui.MineFragment;


/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 *
 * @author luffy
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private Fragment homeFragment;//主页
    private Fragment catagoryFragment;//分类
    private Fragment discoverFragment;//发现
    private Fragment carFragment;//购物车
    private Fragment mineFragment;//我的

    /**
     * 五个选项卡的布局
     */
    private View homeLayout;
    private View catagoryLayout;
    private View discoverLayout;
    private View carLayout;
    private View mineLayout;

    /**
     * 五个选项卡布局的图片
     */
    private ImageView homeImage;
    private ImageView catagoryImage;
    private ImageView discoverImage;
    private ImageView carImage;
    private ImageView mineImage;

    /**
     * 五个选项卡布局的文字
     */
    private TextView homeText;
    private TextView catagoryText;
    private TextView discoverText;
    private TextView carText;
    private TextView mineText;


    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
//         第一次启动时选中第0个tab
        setTabSelection(0);
    }


    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        homeLayout = findViewById(R.id.home_layout);
        catagoryLayout = findViewById(R.id.catagory_layout);
        discoverLayout = findViewById(R.id.discover_layout);
        carLayout = findViewById(R.id.car_layout);
        mineLayout = findViewById(R.id.mine_layout);

        homeImage = (ImageView) findViewById(R.id.home_image);
        catagoryImage = (ImageView) findViewById(R.id.catagory_image);
        discoverImage = (ImageView) findViewById(R.id.discover_image);
        carImage = (ImageView) findViewById(R.id.car_image);
        mineImage = (ImageView) findViewById(R.id.mine_image);

        homeText = (TextView) findViewById(R.id.home_text);
        catagoryText = (TextView) findViewById(R.id.catagory_text);
        discoverText = (TextView) findViewById(R.id.discover_text);
        carText = (TextView) findViewById(R.id.car_text);
        mineText = (TextView) findViewById(R.id.mine_text);

        homeLayout.setOnClickListener(this);
        catagoryLayout.setOnClickListener(this);
        discoverLayout.setOnClickListener(this);
        carLayout.setOnClickListener(this);
        mineLayout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_layout:
                // 当点击了主页tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.catagory_layout:
                // 当点击了分类tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.discover_layout:
                // 当点击了发现tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.car_layout:
                // 当点击了购物车tab时，选中第4个tab
                setTabSelection(3);
                break;
            case R.id.mine_layout:
                // 当点击了我的tab时，选中第4个tab
                setTabSelection(4);
                break;
            default:
                break;
        }
    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了主页tab时，改变控件的图片和文字颜色
                homeImage.setImageResource(R.drawable.main_navigation_home_checked);
                homeText.setTextColor(Color.RED);
                if (homeFragment == null) {
                    // 如果HomeFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    // 如果HomeFragment不为空，则直接将它显示出来
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                // 当点击了分类tab时，改变控件的图片和文字颜色
                catagoryImage.setImageResource(R.drawable.main_navigation_catagory_checked);
                catagoryText.setTextColor(Color.RED);
                if (catagoryFragment == null) {
                    // 如果CatagoryFragment为空，则创建一个并添加到界面上
                    catagoryFragment = new CatagoryFragment();
                    transaction.add(R.id.content, catagoryFragment);
                } else {
                    // 如果CatagoryFragment不为空，则直接将它显示出来
                    transaction.show(catagoryFragment);
                }
                break;
            case 2:
                // 当点击了发现tab时，改变控件的图片和文字颜色
                discoverImage.setImageResource(R.drawable.main_navigation_catagory_checked);
                discoverText.setTextColor(Color.RED);
                if (discoverFragment == null) {
                    // 如果DiscoverFragment为空，则创建一个并添加到界面上
                    discoverFragment = new DiscoverFragment();
                    transaction.add(R.id.content, discoverFragment);
                } else {
                    // 如果DiscoverFragment不为空，则直接将它显示出来
                    transaction.show(discoverFragment);
                }
                break;
            case 3:
                // 当点击了购物车tab时，改变控件的图片和文字颜色
                carImage.setImageResource(R.drawable.main_navigation_car_checked);
                carText.setTextColor(Color.RED);
                if (carFragment == null) {
                    // 如果CarFragment为空，则创建一个并添加到界面上
                    carFragment = new CarFragment();
                    transaction.add(R.id.content, carFragment);
                } else {
                    // 如果CarFragment不为空，则直接将它显示出来
                    transaction.show(carFragment);
                }
                break;
            default:
                // 当点击了我的tab时，改变控件的图片和文字颜色
                mineImage.setImageResource(R.drawable.main_navigation_car_checked);
                mineText.setTextColor(Color.RED);
                if (mineFragment == null) {
                    // 如果MineFragment为空，则创建一个并添加到界面上
                    mineFragment = new MineFragment();
                    transaction.add(R.id.content, mineFragment);
                } else {
                    // 如果MineFragment不为空，则直接将它显示出来
                    transaction.show(mineFragment);
                }
                break;

        }
        transaction.commit();
    }


    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        homeImage.setImageResource(R.drawable.main_navigation_home);
        homeText.setTextColor(Color.parseColor("#82858b"));
        catagoryImage.setImageResource(R.drawable.main_navigation_catagory);
        catagoryText.setTextColor(Color.parseColor("#82858b"));
        discoverImage.setImageResource(R.drawable.main_navigation_catagory);
        discoverText.setTextColor(Color.parseColor("#82858b"));
        carImage.setImageResource(R.drawable.main_navigation_car);
        carText.setTextColor(Color.parseColor("#82858b"));
        mineImage.setImageResource(R.drawable.main_navigation_car);
        mineText.setTextColor(Color.parseColor("#82858b"));
    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (catagoryFragment != null) {
            transaction.hide(catagoryFragment);
        }
        if (discoverFragment != null) {
            transaction.hide(discoverFragment);
        }
        if (carFragment != null) {
            transaction.hide(carFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }
}
