package com.example.ngocsonit.smartclothing.ui.custom_fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngocsonit.smartclothing.R;
import com.example.ngocsonit.smartclothing.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * display a custom {@link android.support.v4.view.ViewPager} title strip which gives continuous feedback to the user
 * when scrolling.
 * Created by ngocsonit on 15/03/2016.
 */
public class SlidingBarColorTabsFragment extends Fragment {
    private static final String TAG = SlidingBarColorTabsFragment.class.getSimpleName(); // tag

    /**
     * This class represent a Tab to be displayed {@link android.support.v4.view.ViewPager}
     * and it is associated to {@link com.example.ngocsonit.smartclothing.view.SlidingTabLayout}
     */
    static class PagerItem {
        private final CharSequence tabTitle;
        private final int indicatorColor;
        private final int dividerColor;
        private final FashionManagerType type;

        // constructor
        PagerItem(FashionManagerType type, CharSequence title, int indicatorColor, int dividerColor) {
            this.type = type;
            this.tabTitle = title;
            this.indicatorColor = indicatorColor;
            this.dividerColor = dividerColor;
        }

        /**
         * @return A new {@link Fragment} to be displayed by a {@link android.support.v4.view.ViewPager}
         */
        Fragment createFragment() {
            return ClothesManagerFragment.newInstance(this.type, this.tabTitle, this.indicatorColor, this.dividerColor);
        }

        /**
         * @return the title which represents this tab. In this sample this is used directly by
         * {@link android.support.v4.view.PagerAdapter#getPageTitle(int)}
         */
        CharSequence getTitle() {
            return this.tabTitle;
        }

        /**
         * @return the color to be used for indicator on the {@link com.example.ngocsonit.smartclothing.view.SlidingTabLayout}
         */
        int getIndicatorColor() {
            return this.indicatorColor;
        }

        /**
         * @return the color to be used for right divider on the {@link com.example.ngocsonit.smartclothing.view.SlidingTabLayout}
         */
        int getDividerColor() {
            return this.dividerColor;
        }

    }

    /**
     * A custom {@link android.support.v4.view.ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout slidingTabLayout;
    /**
     * A {@link ViewPager} used in conjunction with {@link SlidingTabLayout}
     */
    private ViewPager viewPager;

    /**
     * list {@link com.example.ngocsonit.smartclothing.ui.custom_fragments.SlidingBarColorTabsFragment.PagerItem} to represent tabs
     * in {@link SlidingTabLayout}
     */
    private List<PagerItem> listPagerItemList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // BEGIN_INCLUDE (populate_tabs)
        /**
         * Populate our tab list with tabs. Each item contains a FashionManagerType ,  title, indicator color and divider
         * color, which are used by {@link SlidingTabLayout}.
         */
        this.listPagerItemList.add(new PagerItem(FashionManagerType.SHIRTS, getString(R.string.shirts_type),
                Color.parseColor("#e91e63"),
                Color.parseColor("#5c6bc0")));
        this.listPagerItemList.add(new PagerItem(FashionManagerType.TROUSERS, getString(R.string.trousers_type),
                Color.parseColor("#3f51b5"),
                Color.parseColor("#5c6bc0")));
        this.listPagerItemList.add(new PagerItem(FashionManagerType.SHOES, getString(R.string.shoes_type),
                Color.parseColor("#673ab7"),
                Color.parseColor("#d1c4e9")));
    }

    /**
     * inflate the {@link View} which will be displayed by this {@link Fragment} , from the resource
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sliding_tabs_fragment, container, false);

    }

    // BEGIN_INCLUDE (fragment_onviewcreated)

    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     * <p/>
     * We set the {@link ViewPager}'s adapter to be an instance of
     * {@link com.example.ngocsonit.smartclothing.ui.custom_fragments.SlidingBarColorTabsFragment.ClothesFragmentPagerAdapter}. The {@link SlidingTabLayout} is then given the
     * {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // BEGIN_INCLUDE (setup_viewpager)
        // get the ViewPager and set it's adapter so it can display the items
        this.viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        this.viewPager.setAdapter(new ClothesFragmentPagerAdapter(getChildFragmentManager()));
        // END_INCLUDE (setup_viewpager)


        // BEGIN_INCLUDE (setup_slidingtablayout)
        // get the ViewPager and set it's adapter so it can display the items
        this.slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        this.slidingTabLayout.setViewPager(this.viewPager);
        // END_INCLUDE (setup_slidingtablayout)

        // BEGIN_INCLUDE (tab_colorizer)
        // Set a TabColorizer to customize the indicator and divider colors.
        this.slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return listPagerItemList.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return listPagerItemList.get(position).getDividerColor();
            }


        });
        // END_INCLUDE (tab_colorizer)
    }


    /**
     * The {@link FragmentPagerAdapter} used to display pages in this sample. The individual pages
     * are instances of {@link ClothesManagerFragment} which just display three lines of text. Each page is
     * created by the relevant {@link com.example.ngocsonit.smartclothing.ui.custom_fragments.SlidingBarColorTabsFragment.PagerItem} for the requested position.
     * <p/>
     * The important section of this class is the {@link #getPageTitle(int)} method which controls
     * what is displayed in the {@link SlidingTabLayout}.
     */
    public class ClothesFragmentPagerAdapter extends FragmentPagerAdapter {

        public ClothesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * return the {@link Fragment} to be displayed at {@code position}
         */
        @Override
        public Fragment getItem(int position) {
            return listPagerItemList.get(position).createFragment();
        }

        /**
         * Return number of tabs
         *
         * @return
         */
        @Override
        public int getCount() {
            return listPagerItemList.size();
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we return the value returned from {@link PagerItem#getTitle()} method.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return listPagerItemList.get(position).getTitle();
        }
        // END_INCLUDE (pageradapter_getpagetitle)


    }

}
