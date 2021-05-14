package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.viewpager2.widget.ViewPager2;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 툴바
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    // 가운데
    private ViewPager2 viewPager2;
    private DotsIndicator dotsIndicator;
    private ArrayList<DataPage> list;

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SearchPage fragmentSearch = new SearchPage();
    private HomePage fragmentHome = new HomePage();
    private CafePage fragmentCafe = new CafePage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        this.InitializeLayout(); // 툴바

        this.viewpage(); // 뷰페이저

        //FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.replace(R.id.frameLayout, fragmentSearch).commitAllowingStateLoss();
        // 바텀네비게이션

    }

    //툴바
    private void InitializeLayout() {
        //toolBar를 통해 App Bar 생성
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true);  // 왼쪽 버튼 사용 여부 true
        actionBar.setHomeAsUpIndicator(R.drawable.ic_reorder_white_24dp);  // 왼쪽 버튼 이미지 설정

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.nav_coffeedrink:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_dessert:
                        Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        Log.e("Frag", "Fragment");
    }

    // 툴바 오른쪽 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수


    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_guide:

                Intent intent5 = new Intent(getApplicationContext(), com.example.myapplication.GuideActivity.class);
                startActivity(intent5);
            //가이드 액티비티 실행


            //qna맥티비티 실행

            case R.id.action_made:
                ((TextView) findViewById(R.id.textView)).setText("제작");

                    Toast myToast = Toast.makeText(this.getApplicationContext(),R.string.madeby, Toast.LENGTH_SHORT);
                    myToast.show();
            // 제작자 표시 메시지
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void viewpage(){
        list = new ArrayList<>();
        list.add(new DataPage(R.drawable.sample_1, "아메리카노","스타벅스", 4900));
        list.add(new DataPage(R.drawable.sample_2, "아메리카노","투썸플레이스", 4100));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","이디야커피", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노","EDIYA", 3000));

        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new ViewPagerAdapter(list));

        dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager2(viewPager2);
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


