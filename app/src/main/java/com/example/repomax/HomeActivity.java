package com.example.repomax;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    //Initialize variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Button btndrawer;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    EditText className, SectionName;
    Button addClass, cancelClass;
    Planes planes;
    FragmentManager fragmentManager;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Assign variable

        mAuth= FirebaseAuth.getInstance();
        fragmentManager = this.getSupportFragmentManager();

        tabLayout = findViewById(R.id.tab_layout);
        drawerLayout = findViewById(R.id.drawing_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {


            switch (item.getItemId()) {

                case R.id.nav_home:

                    Log.i("Menu_drawer_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                case R.id.dark_mode:

                    Log.i("Dark_mode_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                case R.id.comp_trans:

                    Log.i("Trans_comp_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                case R.id.logout_now:

                    Log.i("Log_Out_tag","Loging Out");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    mAuth.signOut();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }


            return true;
        });


        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("ProEduk \uD83E\uDD89");


        //Initialize adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        //Initialize main fragment
        //Add fragments

        adapter.addFragment(new Planes(), "Planes");
        adapter.addFragment(new Asistencia(), "Asistencia/Grupos");
        adapter.addFragment(new Notas(), "Notas");
        //Set adapter
        viewPager.setAdapter(adapter);
        //Connect tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            Toast.makeText(getApplicationContext(), "Presionaste Compartir", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "Presionaste Acerca", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.exit) {
            Toast.makeText(getApplicationContext(), "Presionaste Cerrar", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.search) {

            Toast.makeText(getApplicationContext(), "Presionaste Buscar", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.settings) {

            Toast.makeText(getApplicationContext(), "Presionaste Configuración", Toast.LENGTH_SHORT).show();
        } else if (actionBarDrawerToggle.onOptionsItemSelected(item)) {


            return super.onOptionsItemSelected(item);
        }
        return true;


    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class MainAdapter extends FragmentPagerAdapter {
        //Initialize array list
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        int[] imageList = {R.drawable.ic_baseline_book_24, R.drawable.ic_baseline_rule_folder_24,
                R.drawable.ic_baseline_star_24};

        //Create constructor
        public void addFragment(Fragment fragment, String s) {
            //Add fragment
            fragmentList.add(fragment);
            //Add string
            stringList.add(s);

        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //Return selected fragment
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //Return total fragment
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //Initialize drawable
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext()
                    , imageList[position]);
            //Set bound
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            //Initialize spannable string
            SpannableString spannableString = new SpannableString(" " +
                    stringList.get(position));
            //Initialize image span
            ImageSpan imageSpan = new ImageSpan(drawable,
                    ImageSpan.ALIGN_BOTTOM);
            //Set span
            spannableString.setSpan(imageSpan, 0, 1
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //Return spannable string
            return spannableString;

            //Return tab title

        }

    }


}
