package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class fragmentAdaptor extends FragmentPagerAdapter {
    public fragmentAdaptor(@NonNull FragmentManager fm) {
        super(fm);
    }

    public fragmentAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SignUpFragment();
            case 1:
                return new LoginFragment();
            default:
                return new SignUpFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;

        if (position==0){
            title="SIGN UP";
        }
        else if (position==1){
            title="SIGN IN";
        }


        return title;
    }
}
