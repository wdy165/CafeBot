package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SearchPage fragmentSearch = new SearchPage();
    private HomePage fragmentHome = new HomePage();
    private CafePage fragmentCafe = new CafePage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new SearchActivity.ItemSelectedListener());
    }
    //바텀네비
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.searchItem:
                    Intent intent1 = new Intent(getApplicationContext(), com.example.myapplication.SearchActivity.class);
                    startActivity(intent1);
                    //transaction.replace(R.id.frameLayout, fragmentSearch).commitAllowingStateLoss();
                    break;
                case R.id.homeItem:
                    Intent intent2 = new Intent(getApplicationContext(), com.example.myapplication.MainActivity.class);
                    startActivity(intent2);
                    //transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.cafeItem:
                    Intent intent3 = new Intent(getApplicationContext(), com.example.myapplication.MainCflistActivity.class);
                    startActivity(intent3);
                    //transaction.replace(R.id.frameLayout, fragmentCafe).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}