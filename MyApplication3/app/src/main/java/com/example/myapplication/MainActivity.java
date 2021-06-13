package com.example.myapplication;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // 툴바
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    // 가운데
    private ViewPager2 viewPager2;
    private DotsIndicator dotsIndicator;
    private ArrayList<DataPage> list;

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    // 필터 체크표시를 확인하고 저장하기 위한 변수
    private int state = 0;
    private int cafeState = 0b1111111111111111110;

    // 카테고리 선택을 구분하기 위한 변수
    private int categoryState = 0;
    private int subcategoryState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        this.InitializeLayout(); // 툴바

        this.viewpage(randomMenu()); // 뷰페이저

    }

    //툴바
    private void InitializeLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_reorder_white_24dp); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();

                if(id == R.id.nav_coffeedrink){
                    categoryDrink();
                }
                else if(id == R.id.nav_dessert){
                    categoryDessert();
                }
                return true;
            }
        });
    }

    // 디저트 선택시 실행
    public void categoryDessert(){
        Intent intent = new Intent(getApplicationContext(), DessertMenu.class);
        intent.putExtra("categoryState", categoryState);
        intent.putExtra("subcategoryState", subcategoryState);
        startActivityForResult(intent, 2);
    }

    // 커피, 음료 선택시 실행
    public void categoryDrink(){
        Intent intent = new Intent(getApplicationContext(), DrinkMenu.class);
        intent.putExtra("categoryState", categoryState);
        intent.putExtra("subcategoryState", subcategoryState);
        startActivityForResult(intent, 2);
    }

    // 메인 화면에서 단순히 가격 필터시
    public void mainFilter(View v){
        Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
        intent.putExtra("state", state);
        startActivityForResult(intent, 0);
    }

    // 툴바 오른쪽 버튼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    // 액티비티 종료시 자동 실행
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Fragment로 넘겨 받은 값을 넣는다.
        subcategoryState = data.getIntExtra("subcategoryState", 0);

        // requestCode 는 cafeFilter에서 실행시 0으로 넘기고 mainFilter에서 실행시 1을 넘긴다
        // 카테고리 분류작업은 2를 넘긴다.
        switch (requestCode){
            case 0 :
                mainFiltering(resultCode);
                break;
            case 2 :
                break;
            default:
                break;
        }
    }

    public void mainFiltering(int resultCode){
        // 현재 상태를 저장한다.
        state = resultCode;
        // 가격 오름, 내림을 결정한다.
        dataSort(state);
    }

    // sortValue에 따라 오름, 내림 차순 정렬
    public void dataSort(int sortValue) {
        if(sortValue == 0){
            Collections.sort(list);
        }
        else if(sortValue == 1){
            Collections.sort(list);
            Collections.reverse(list);
        }

        viewPager2.setAdapter(new ViewPagerAdapter(list));
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
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
                // 지도 액티비티 실행
            case R.id.action_map:
                Intent intent7 = new Intent(getApplicationContext(), com.example.myapplication.MapActivity.class);
                startActivity(intent7);
                return true;
            //qna 액티비티 실행
            case R.id.action_made:
                ((TextView) findViewById(R.id.textView)).setText("제작자");

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
        InputStream is = this.getResources().openRawResource(R.raw.cafe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);

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

        this.list.clear();
        this.list.addAll(randomList);

        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new ViewPagerAdapter(randomList));

        dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager2(viewPager2);
    }

    //바텀네비
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.searchItem:
                    Intent intent1 = new Intent(getApplicationContext(), com.example.myapplication.SearchActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.homeItem:
                    Intent intent2 = new Intent(getApplicationContext(), com.example.myapplication.MainActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.cafeItem:
                    Intent intent3 = new Intent(getApplicationContext(), com.example.myapplication.MainCflistActivity.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    }

}


