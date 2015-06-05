package tung.slidingtag_example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import fragment.Fragment01;
import fragment.Fragment02;
import fragment.Fragment03;
import fragment.FragmentMain;
import slidingtab.adapter.MainDrawerAdapter;
import slidingtab.adapter.ViewPageAdapter;

public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPageAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Home","Events"};
    int Numboftabs =2;

    //Data for navigtion
    String TITLES[] = {"Login","Events","Mail","Shop","Travel"};
    int ICONS[] = {
            R.mipmap.ic_facebook,
            R.mipmap.ic_people,
            R.mipmap.ic_task,
            R.mipmap.ic_chat,
            R.mipmap.ic_facebook};

    String NAME = "Tung Tran";
    String DETAIL = "Programmer at Zonmob";
    int PROFILE_PIC = R.mipmap.avatar;

    RecyclerView               mRecyclerView;
    MainDrawerAdapter          mAdapter;
    DrawerLayout               mDrawerLayout;
    RecyclerView.LayoutManager mLayoutManager;

    ActionBarDrawerToggle      mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create tool bar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
//        adapter =  new ViewPageAdapter(getSupportFragmentManager(), Titles, Numboftabs);
//
//        // Assigning ViewPager View and setting the adapter
//        pager = (ViewPager) findViewById(R.id.pager);
//        pager.setAdapter(adapter);
//
//        // Assiging the Sliding Tab Layout View
//        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
//        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
//
//        // Setting Custom Color for the Scroll bar indicator of the Tab View
//        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//            @Override
//            public int getIndicatorColor(int position) {
//                return getResources().getColor(R.color.tabsScrollColor);
//            }
//        });
//
//        // Setting the ViewPager For the SlidingTabsLayout
//        tabs.setViewPager(pager);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.DrawerLayout);
        mRecyclerView  = (RecyclerView)findViewById(R.id.RecyclerView);
        mAdapter       = new MainDrawerAdapter(TITLES,ICONS,NAME,DETAIL,PROFILE_PIC, getApplicationContext());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    displayFragment(recyclerView.getChildPosition(child));
                    Log.e("MainActivity", "Error in creating fragment" + recyclerView.getChildPosition(child));
                    mDrawerLayout.closeDrawers();
                    return true;

                }


                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if(savedInstanceState == null) {
            displayFragment(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void displayFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentMain();
                break;
            case 1:
                fragment = new Fragment01();
                break;
            case 2:
                fragment = new Fragment02();
                break;
            case 3:
                fragment = new Fragment03();
                break;
            case 4:
                fragment = new Fragment02();
                break;
            case 5:
                fragment = new Fragment03();
                break;

            default:
                break;
        }
        if (fragment != null) {
//            tabs.setActivated(false);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_container, fragment).commit();
            mDrawerLayout.closeDrawer(mRecyclerView);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mRecyclerView)) {
            mDrawerLayout.closeDrawer(mRecyclerView);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
