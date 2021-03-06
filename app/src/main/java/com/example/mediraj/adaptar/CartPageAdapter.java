package com.example.mediraj.adaptar;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mediraj.fragment.cart.DiagnosticFragment;
import com.example.mediraj.fragment.cart.PathologyFragment;
import com.example.mediraj.fragment.cart.SurgicalFragment;

public class CartPageAdapter extends FragmentPagerAdapter {
    private int noOfTabs;
    private Context context;

    public CartPageAdapter(FragmentManager fm, int noOfTabs, Context context) {
        super(fm);
        this.noOfTabs = noOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DiagnosticFragment diagnosticFragment = new DiagnosticFragment();
                return diagnosticFragment;
            case 1:
                PathologyFragment pathologyFragment = new PathologyFragment();
                return pathologyFragment;
            case 2:
                SurgicalFragment surgicalFragment = new SurgicalFragment();
                return surgicalFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
