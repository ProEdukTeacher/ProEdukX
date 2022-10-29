package com.example.repomax;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {
    LoginFragment loginFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment();

        if (savedInstanceState == null)

            fragmentManager.beginTransaction()

                    .replace(R.id.loginActivityContainer, loginFragment)
//                  .addToBackStack(null)
                    .commit();
    }

//    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            super.onBackPressed();
//        } else {
//            getFragmentManager().popBackStack();
//        }
//    }




}
