package com.jxnu.zha.tingbei.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.core.AbstractActivity;
import com.jxnu.zha.tingbei.core.BaseActivity;
import com.jxnu.zha.tingbei.fragment.MainFragment;

import butterknife.BindView;

public class MainActivity extends AbstractActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;  //菜单
    private LinearLayout mMainContent;
    private Toolbar mToolbar;
    private Fragment[] fragments = new Fragment[] {new MainFragment()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.mIsTemplate = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void init() {
        mDrawerLayout = findWidget(R.id.id_drawerLayout);
        mNavigationView = findWidget(R.id.id_nvMenu);
        mMainContent = findWidget(R.id.id_mainContent);
        mToolbar = findWidget(R.id.id_toolbar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar
                , R.string.menuDrawer_open, R.string.menuDrawer_close);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();//show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);    //不加这一句，则不旋转
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
//                index = 1;
                break;
            case R.id.action_setting:
//                index = 2;
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
}
