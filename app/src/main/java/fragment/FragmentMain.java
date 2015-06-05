package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import slidingtab.adapter.ViewPageAdapter;
import tung.slidingtag_example.R;
import tung.slidingtag_example.SlidingTabLayout;

/**
 * Created by user on 6/5/2015.
 */
public class FragmentMain extends Fragment{
    public FragmentMain(){}

    CharSequence Titles[]={"Home","Events"};
    int Numboftabs =2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View v =inflater.inflate(R.layout.home_layout, container, false);
        ViewPageAdapter adapter =  new ViewPageAdapter(getFragmentManager(), Titles, Numboftabs);

        ViewPager pager = (ViewPager)v.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        SlidingTabLayout tabs = (SlidingTabLayout) v.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);
        return v;
    }
}
