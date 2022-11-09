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
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    //Initialize variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    View headerView;

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference mDatabase;
    String userID;
    TextView namer, emailer;
    ImageView profp, profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Assign variable
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        profp = findViewById(R.id.profile_image);
        namer = findViewById(R.id.name);
        emailer = findViewById(R.id.theemail);
        mAuth = SessionManager.getInstance().getmAuth();
        user = mAuth.getCurrentUser();
        mDatabase = SessionManager.getInstance().getFirebaseDatabase().getReference(TeacherUser.class.getSimpleName());
        userID = user.getUid();

        getUserInformation();
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        gsc = GoogleSignIn.getClient(this, gso);
//
//
//        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
//        if (acc != null) {
//
//            String personName = acc.getDisplayName();
//            String personEmail = acc.getEmail();
//            String profilepic = String.valueOf(acc.getPhotoUrl());
//        }

        fragmentManager = this.getSupportFragmentManager();
        TextView datetaker = findViewById(R.id.dateshower);
        datetaker.setText(currentDate);
        tabLayout = findViewById(R.id.tab_layout);
        drawerLayout = findViewById(R.id.drawing_layout);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        headerView = navigationView.getHeaderView(0);
        profileImage = headerView.findViewById(R.id.profileImage);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               gotoProfile();
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {


            switch (item.getItemId()) {




                case R.id.nav_home:

                    Log.i("Menu_drawer_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                    break;
                case R.id.dark_mode:

                    Log.i("Dark_mode_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                    break;

                case R.id.comp_trans:

                    Log.i("Trans_comp_tag", "Hote item is clicked");
                    drawerLayout.closeDrawer(GravityCompat.START);

                    break;

                case R.id.logout_now:


                    Log.i("Log_Out_tag", "Loging Out");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    SessionManager.getInstance().logoutFromSession();

                    goBackToSplash();

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

    private void goToSettings() {

        Intent intent = new Intent(HomeActivity.this, ProfiletesterActivity.class);
        intent.putExtra("Settings", "Settings");
        startActivity(intent);

    }

    private void getUserInformation() {

        mDatabase.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TeacherUser user = snapshot.getValue(TeacherUser.class);

                if(user!=null){
                    String fullName = user.getName();
                    String email = user.getEmail();

                    namer.setText(fullName);
                    emailer.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void gotoProfile() {

        Intent gotoProfile = new Intent(this, ProfiletesterActivity.class);
        startActivity(gotoProfile);
    }

    private void goBackToSplash() {

        Intent goBackToSplash = new Intent(this, Splashzone.class);
        startActivity(goBackToSplash);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.search) {

            Toast.makeText(getApplicationContext(), "Presionaste Buscar", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.settings) {
            goToSettings();

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
