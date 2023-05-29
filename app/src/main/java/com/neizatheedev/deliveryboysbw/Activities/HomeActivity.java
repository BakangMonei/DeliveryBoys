package com.neizatheedev.deliveryboysbw.Activities;

/**
 * @author Monei Bakang Motshegwe
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.neizatheedev.deliveryboysbw.MenuActivities.Customer;
import com.neizatheedev.deliveryboysbw.Fragments.HomeFragment;
import com.neizatheedev.deliveryboysbw.Fragments.ProfileFragment;
import com.neizatheedev.deliveryboysbw.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Create instance of fragment manager and replace the fragment container with the dashboard screen
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        ((FragmentTransaction) tx).replace(R.id.fragment_container, new HomeFragment());
        tx.commit();
        //Instantiate the bottom navigation menu and create an OnNavigationItemSelectedListener to select pages accordingly.
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    //OnNavigationItemSelectedListener followed by a switch case to display selected pages
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                private Activity activity = HomeActivity.this; // Store reference to the activity

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };


}