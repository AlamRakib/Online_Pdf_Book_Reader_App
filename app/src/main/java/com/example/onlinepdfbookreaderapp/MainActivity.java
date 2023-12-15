package com.example.onlinepdfbookreaderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);

        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new NobelFragment());
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int tabPosition = tab.getPosition();

                if(tabPosition == 0)
                {
                    FragmentManager fManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new NobelFragment());
                    fragmentTransaction.commit();

                }

                if(tabPosition == 1)
                {
                    FragmentManager fManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new PoemFragment());
                    fragmentTransaction.commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}