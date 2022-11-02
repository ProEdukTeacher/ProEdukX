package com.example.repomax;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ProfiletesterActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiletester);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // Asign variable
        viewPager = findViewById(R.id.view_pager_pf);
        tabLayout = findViewById(R.id.tab_layout_pf);
        fragmentManager = this.getSupportFragmentManager();

    //Initialize adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
    //Initialize fragments

        adapter.addFragment(new PFFragment(), "Profile");
        adapter.addFragment(new SettingFragment(), "Settings");
        adapter.addFragment(new SubscriptionFragment(), "Subscription");
    //Set adapter
        viewPager.setAdapter(adapter);

    //connect with tab layout

        tabLayout.setupWithViewPager(viewPager);

    // Initialize Arraylist
        ArrayList<String> arrayList = new ArrayList<>();



    //METHOD FOR ICONS

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.profyprof);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.gearygear);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.wallywallet);





    }






    private class MainAdapter extends FragmentPagerAdapter {
    //Initialize array list

        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();


    //Create constructor

        public void addFragment (Fragment fragment, String s){
    //Add fragment
            fragmentArrayList.add(fragment);
    //Add title
            stringArrayList.add(s);


        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return fragment position
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            //Return fragment array list size
            return stringArrayList.size();
        }

        @Nullable
       @Override
        public CharSequence getPageTitle(int position) {




        return stringArrayList.get(position);
        }
    }
            // Initialize drawable

          //  Drawable drawable= ContextCompat.getDrawable(getApplicationContext()
            //, imagelist[position]);

            //set bounds
            //assert drawable != null;
            //drawable.setBounds(0,0,drawable.getIntrinsicWidth()
            //, drawable.getIntrinsicHeight());

            // Initialize spannable string

            //SpannableString spannableString = new SpannableString("" +
              //      stringArrayList.get(position));
            // Initialize image span

           // ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            // Set span
           // spannableString.setSpan(imageSpan , 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            //return spannable string
           // return spannableString;

    }
//}
