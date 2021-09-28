package com.example.effe_21ca;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class SignInUp extends AppCompatActivity {

    TabLayout tabLayoutx;
    ViewPager viewPagerx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);

        viewPagerx=findViewById(R.id.ViewPagera);
        viewPagerx.setAdapter(new fragmentAdaptor(getSupportFragmentManager()));

        tabLayoutx=findViewById(R.id.tabLayouta);
        tabLayoutx.setupWithViewPager(viewPagerx);
    }
}