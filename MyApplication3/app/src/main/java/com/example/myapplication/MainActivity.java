package com.example.myapplication;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.ImageButton;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
=======
import java.util.Arrays;
>>>>>>> brchWDY
import java.util.List;
import java.util.Random;

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

        this.viewpage(randomMenu()); // 뷰페이저

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
                return true;
            //가이드 액티비티 실행
            case R.id.action_qna:
                Intent intent6 = new Intent(getApplicationContext(), com.example.myapplication.QnaActivity.class);
                startActivity(intent6);
                return true;
            //qna맥티비티 실행
            case R.id.action_made:
                ((TextView) findViewById(R.id.textView)).setText("제작");

                Toast myToast = Toast.makeText(this.getApplicationContext(), R.string.madeby, Toast.LENGTH_SHORT);
                myToast.show();
                return true;
            // 제작자 표시 메시지
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 새로고침 버튼
    public void onRefreshButton(View v) {
        viewpage(randomMenu());
    }

    // 메뉴 리스트
    public ArrayList<DataPage> randomMenu() {
<<<<<<< HEAD
        InputStream is = this.getResources().openRawResource(R.raw.datatest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);
=======

        InputStream is = this.getResources().openRawResource(R.raw.datatest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<List<String>> ret = new ArrayList<List<String>>();

        list = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // do something with "line"
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                list.add(new DataPage(R.drawable.sample_1, array[1], array[0], array[2]));
                //배열에서 리스트 반환
//                tmpList = Arrays.asList(array);
//                System.out.println(tmpList);
//                ret.add(tmpList);
            }
        }
        catch (IOException ex) {
            // handle exception
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                // handle exception
            }
        }
>>>>>>> brchWDY

        list = new ArrayList<>();
        int i = 0;
        try {
            String line;
            while ((line = reader.readLine()) != null && (i < typedArray.length())) {
                // do something with "line"
                String array[] = line.split(",");

                list.add(new DataPage(typedArray.getResourceId(i, -1), array[0], array[1], array[2], Integer.parseInt(array[3])));
                i++;
            }
        }
        catch (IOException ex) {
            // handle exception
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                // handle exception
            }
        }
        return list;


//        list.add(new DataPage(R.drawable.sample_1, "아메리카노", "스타벅스", 4900));
//        list.add(new DataPage(R.drawable.sample_2, "아메리카노", "투썸플레이스", 4100));
//        list.add(new DataPage(R.drawable.sample_3, "아메리카노", "이디야커피", 3000));
//        list.add(new DataPage(R.drawable.sample_4, "망고 스무디", "공차", 5300));
//        list.add(new DataPage(R.drawable.sample_5, "치즈폼 딥초코 스무디", "공차", 5300));
//        list.add(new DataPage(R.drawable.sample_6, "바닐라 딜라이트", "할리스", 4500));
//        list.add(new DataPage(R.drawable.sample_7, "에스프레소", "할리스", 2500));
//        list.add(new DataPage(R.drawable.sample_8, "고구마라떼", "메가커피", 3000));
//        list.add(new DataPage(R.drawable.sample_9, "자몽티", "메가커피", 3000));
//        list.add(new DataPage(R.drawable.sample_10, "타로 밀크티", "공차", 4000));
//        list.add(new DataPage(R.drawable.sample_11, "녹차라떼", "빽다방", 3000));
//        list.add(new DataPage(R.drawable.sample_12, "달콤아이스티", "빽다방", 2500));
//        list.add(new DataPage(R.drawable.sample_13, "긴페스츄리와플", "빽다방", 3000));
//        list.add(new DataPage(R.drawable.sample_14, "큰마들렌", "빽다방", 3000));
//        list.add(new DataPage(R.drawable.sample_15, "완전딸기바나나 빽스치노(SOFT)", "빽다방", 4000));

//        return list;
    }

    private void viewpage(ArrayList<DataPage> list) {
        // 전체 메뉴 리스트 가져옴
        ArrayList randomList = new ArrayList<>();

        // 랜덤 함수 이용
        Random ra = new Random();

        // 전체 리스트에서 메뉴 10개만 랜덤으로 가져옴
        for (int i=0; i<10; i++) {
            int rv = ra.nextInt(list.size());
            randomList.add(list.get(rv));
            list.remove(rv);
        }

        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new ViewPagerAdapter(randomList));

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


