package com.jxnu.zha.tingbei.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.util.SharedPreferenceUtil;
import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.adapter.ThemeSelectAdapter;
import com.jxnu.zha.tingbei.constant.ConstantValue;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.core.BaseApplication;
import com.jxnu.zha.tingbei.fragment.MainFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AbstractActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;  //菜单
    private LinearLayout mMainContent;
    private Toolbar mToolbar;
    private TextView mTvName;
    private Fragment[] fragments = new Fragment[] {new MainFragment()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.mIsTemplate = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBroadcast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void init() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_menu_header,null);
        mDrawerLayout = findWidget(R.id.id_drawerLayout);
        mNavigationView = findWidget(R.id.id_nvMenu);
        mMainContent = findWidget(R.id.id_mainContent);
        mToolbar = findWidget(R.id.id_toolbar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar
                , R.string.menuDrawer_open, R.string.menuDrawer_close);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();//show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);    //不加这一句，则不旋转
        mNavigationView.addHeaderView(view);
        mTvName = (TextView) view.findViewById(R.id.tv_menuHead_userName);
        mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!BaseApplication.getInstance().isUserLogin()){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        if (BaseApplication.getInstance().isUserLogin()){
            if (mTvName != null){
                mTvName.setText(BaseApplication.getInstance().getUser().getObj().getNickName());
            }
        }
        showMenuMain();
        setNavigationViewListener();
    }

    /**
     * 显示主Fragment
     */
    private void showMenuMain() {
        Menu menu = mNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.action_main);
        showFragment(menuItem);
    }
    /**
     * 显示fragment
     * @param menuItem
     */
    private void showFragment(MenuItem menuItem){
        int index = 0;
        switch (menuItem.getItemId()){
            case R.id.action_main:
                index = 0;
                break;
            case R.id.action_switchTheme:
                selectTheme();
                break;
            case R.id.action_setting:
                break;
        }
        switchFragmentContent(R.id.tipInfo_layout,fragments[index]);
    }
    /**
     * 对抽屉菜单进行监听
     */
    private void setNavigationViewListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                showFragment(menuItem);
                if (menuItem.getItemId() != R.id.action_setting){
                    mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    /**
     * 当用户点击返回键的时候，让app退出到后台继续运行，而不是退出应用
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (isTaskRoot()) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) { //防止调用两次
                moveTaskToBack(false);
                return true;
            }
            return super.dispatchKeyEvent(event);
        } else {
            finish();
            return super.dispatchKeyEvent(event);
        }
    }

    /**
     * 选择主题
     */
    private void selectTheme() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.mainFragment_changeTheme);
        Integer[] themeIcon = new Integer[]{R.drawable.theme_blue_round, R.drawable.theme_brown_round, R.drawable.theme_red_round,
                R.drawable.theme_blue_grey_round, R.drawable.theme_yellow_round, R.drawable.theme_deep_purple_round,
                R.drawable.theme_pink_round, R.drawable.theme_green_round};
        List<Integer> list = Arrays.asList(themeIcon);
        ThemeSelectAdapter adapter = new ThemeSelectAdapter(this,list);
        final int selectedTheme = (int) SharedPreferenceUtil.get(this, getString(R.string.mainFragment_selectedTheme), 0);
        adapter.setSelectedItem(selectedTheme);

        GridView gridView = (GridView) LayoutInflater.from(this)
                .inflate(R.layout.theme_bg_gridview, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                if (selectedTheme != position) {

                    SharedPreferenceUtil.put(MainActivity.this, getString(R.string.mainFragment_selectedTheme), position);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    /**
     * 注册广播接收器
     */
    private void registerBroadcast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstantValue.ACTION_LOGIN_SUCCESS);
        registerReceiver(broadcastReceiver, filter);
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // 定义广播接收器，接收蓝牙消息
        @Override
        public void onReceive(Context context, final Intent intent) {
            if (ConstantValue.ACTION_LOGIN_SUCCESS.equals(intent.getAction())){
                if (mTvName != null){
                    mTvName.setText(BaseApplication.getInstance().getUser().getObj().getNickName());
                }
            }
        }
    };
}
