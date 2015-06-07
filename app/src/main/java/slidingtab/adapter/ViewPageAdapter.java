package slidingtab.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tienlenmiennam.slidingtag_example.Tab1Fragment;
import tienlenmiennam.slidingtag_example.Tab2Fragment;

/**
 * Created by Tran on 6/1/2015.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[];
    int numOfTab;

    public ViewPageAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        Titles = mTitles;
        numOfTab = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
            return new Tab1Fragment();
        }
        else if(i == 1) {
            return new Tab2Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }
}
