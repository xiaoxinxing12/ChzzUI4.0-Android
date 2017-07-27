package org.chzz.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by copy on 2017/7/27.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabs=new ArrayList<>();
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<String> mTabs) {
        super(fm);
        this.mTabs = mTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return new TabFragment();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }
}
