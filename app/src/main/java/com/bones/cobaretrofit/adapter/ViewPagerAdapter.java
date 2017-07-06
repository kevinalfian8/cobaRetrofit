package com.bones.cobaretrofit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bones.cobaretrofit.fragment.InTheatresFragment;
import com.bones.cobaretrofit.fragment.PopularFragment;
import com.bones.cobaretrofit.fragment.UpcomingFragment;

/**
 * Created by lenovo ip on 22/06/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new InTheatresFragment();
                break;
            case 1:
                fragment = new PopularFragment();
                break;
            case 2:
                fragment = new UpcomingFragment();
                break;
            default:
                fragment = new InTheatresFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        String title = null;
        switch (position){
            case 0:
                title = "In Theatres";
                break;
            case 1:
                title = "Popular";
                break;
            case 2:
                title = "Upcoming";
                break;
            default:
                title = "In Theatres";
        }
        return title;
    }

}
