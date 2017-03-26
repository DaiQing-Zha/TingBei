package com.jxnu.zha.tingbei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxnu.zha.tingbei.R;
import com.jxnu.zha.tingbei.core.TabStripAbstractFragment;

/**
 * Created by DaiQing.Zha on 2017/3/17.
 * email:13767191284@163.com
 * description:
 */
public class MainFragment extends TabStripAbstractFragment {

    private Fragment[] fragments = new Fragment[4];
    @Override
    protected String[] getTabTitle() {

        String[] subItems = getResources().getStringArray(R.array.mainFragmentItems);
        return subItems;
    }

    @Override
    protected Fragment getFragment(int position) {

        Fragment fragment = fragments[position];
        return fragment;
    }

    @Override
    protected void initFragment() {
        fragments[0] = new HandPickFragment();
        fragments[1] = new RecommendFragment();
        fragments[2] = new RankingFragment();
        fragments[3] = new RadioFragment();
    }
}
