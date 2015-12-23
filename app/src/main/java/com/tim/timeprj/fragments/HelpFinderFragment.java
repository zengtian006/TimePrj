package com.tim.timeprj.fragments;


/**
 * Created by Zeng on 02/11/15.
 */


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.timeprj.R;

import java.util.ArrayList;
import java.util.List;


public class HelpFinderFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_favourite,
            R.drawable.ic_tab_call,
            R.drawable.ic_tab_contacts
    };
    View rootView;

    public HelpFinderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_helper, container, false);
        // Inflate the layout for this fragment
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new BuyFragment(), "BUY");
        adapter.addFrag(new ShareFragment(), "SHARE");
        adapter.addFrag(new SellFragment(), "SELL");
        adapter.addFrag(new BuyFragment(), "BUY");
        adapter.addFrag(new ShareFragment(), "SHARE");
        adapter.addFrag(new SellFragment(), "SELL");
        adapter.addFrag(new BuyFragment(), "BUY");
        adapter.addFrag(new ShareFragment(), "SHARE");
        adapter.addFrag(new SellFragment(), "SELL");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return rootView;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[0]);
        tabLayout.getTabAt(4).setIcon(tabIcons[1]);
        tabLayout.getTabAt(5).setIcon(tabIcons[2]);
        tabLayout.getTabAt(6).setIcon(tabIcons[0]);
        tabLayout.getTabAt(7).setIcon(tabIcons[1]);
        tabLayout.getTabAt(8).setIcon(tabIcons[2]);
    }
}
