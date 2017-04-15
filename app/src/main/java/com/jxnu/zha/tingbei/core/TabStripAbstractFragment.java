package com.jxnu.zha.tingbei.core;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxnu.zha.qinglibrary.util.DensityUtil;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.utils.ThemeUtil;
import com.jxnu.zha.tingbei.widgets.PagerSlidingTabStrip;
import com.nineoldandroids.view.ViewHelper;

public abstract class TabStripAbstractFragment extends AbstractFragment {
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private LinearLayout mTabsLinearLayout;
	private PagerSlidingTabStrip mTabs;
	private ViewPager mViewPager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_base_tabstrip, container, false);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		mTabs.setIndicatorColor(getIndicatorColor());
		mViewPager = (ViewPager) view.findViewById(R.id.tabsContent);
		init();
	}

	private int getIndicatorColor(){
		int color = getResources().getColor(R.color.color_tabStripTextColorSelected);
		try{
			int themeStyle = ThemeUtil.style;
			switch (themeStyle){
				case R.style.BlueTheme:
					color = getResources().getColor(R.color.blue);
					break;
				case R.style.BrownTheme:
					color = getResources().getColor(R.color.brown);
					break;
				case R.style.redTheme:
					color = getResources().getColor(R.color.red);
					break;
				case R.style.BlueGreyTheme:
					color = getResources().getColor(R.color.blue_grey);
					break;
				case R.style.YellowTheme:
					color = getResources().getColor(R.color.yellow);
					break;
				case R.style.DeepPurpleTheme:
					color = getResources().getColor(R.color.deep_purple);
					break;
				case R.style.PinkTheme:
					color = getResources().getColor(R.color.pink);
					break;
				case R.style.GreenTheme:
					color = getResources().getColor(R.color.color_tabStripTextColorSelected);
					break;
			}
		}catch (Exception e){
			color = getResources().getColor(R.color.color_tabStripTextColorSelected);
		}
		return color;
	}
	
	/**
	 * @author caibing.zhang
	 * @createdate 2015年1月24日 下午4:31:41
	 * @Description: 显示标签
	 * @param currentPosition
	 */
	public void setViewPagerCurrentItem(int currentPosition){
		if(mViewPager!=null)
			mViewPager.setCurrentItem(currentPosition);
	}
	public void init(){
		initTabsValue();
		initFragment();
		mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mTabs.setViewPager(mViewPager);
		// Set first tab selected
		mTabsLinearLayout = ((LinearLayout) mTabs.getChildAt(0));

		seTabsTextColor(0);
	}
	/**
	 * mPagerSlidingTabStrip默认值配置
	 * 
	 */
	private void initTabsValue() {
		mTabs.setAllCaps(false);
		mTabs.setShouldExpand(true);
		mTabs.setTextSize((int) DensityUtil.numerical2px(getActivity(), 15));
		mTabs.setOnPageChangeListener(mTabsOnPageChangeListener);
		mTabs.setTextColor(Color.WHITE);
	}
	/**
	 * 设置显示的文字的颜色，选中与不选中
	 * @param position
	 */
	private void seTabsTextColor(int position){
		for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
			int color;
			TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
			if (i == position) {
				color = getIndicatorColor();
			} else {
				color = getResources().getColor(R.color.color_tabStripTextColorUnSelected);
			}
			tv.setTextColor(color);
		}
	}
	
	private ViewPager.OnPageChangeListener mTabsOnPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			ViewHelper.setTranslationY(mTabs, 0);
		}
		@Override
		public void onPageSelected(int position) {
			seTabsTextColor(position);
		}
		@Override
		public void onPageScrollStateChanged(int state) {
		}
	};
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		private Fragment[] fragments;
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			fragments=new Fragment[getCount()];
		}
		@Override
		public Fragment getItem(int position) {

			Fragment fragment = fragments[position];

			if(fragment==null){

				fragment = getFragment(position);
				fragments[position]=fragment;
			}
			return fragment;
		}
		@Override
		public int getCount() {
			return getTabTitle().length;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			return getTabTitle()[position];
		}
	}
	
	/**
	 * @Description: 获取TabTitle 文字数组
	 * @return
	 */
	protected abstract String[] getTabTitle();
	
	/**
	 * @Description: 获取点击或滑动显示的Fragment
	 * @param position
	 * @return
	 */
	protected abstract Fragment getFragment(int position);
	protected abstract void initFragment();

	private static final class TransformerItem {
        final String title;
        final Class<? extends PageTransformer> clazz;
        public TransformerItem(Class<? extends PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }
        @Override
        public String toString() {
            return title;
        }
    }
}
