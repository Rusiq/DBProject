package com.example.ruslan.dbproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.SearchView;
import android.view.View;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static ShowFragment sShowFragment;


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return AddFragment.newInstance(position + 1);

        } else {
            if (sShowFragment == null)

                sShowFragment = ShowFragment.newInstance(position + 1);
            return sShowFragment;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Add";
            case 1:
                return "Show Database";

        }
        return null;
    }
}
