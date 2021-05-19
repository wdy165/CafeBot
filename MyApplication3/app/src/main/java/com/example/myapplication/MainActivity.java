package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Collections;
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
    private SearchPage fragmentSearch = new SearchPage();
    private HomePage fragmentHome = new HomePage();
    private CafePage fragmentCafe = new CafePage();

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

        //FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.replace(R.id.frameLayout, fragmentSearch).commitAllowingStateLoss();
        // 바텀네비게이션

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
                    //categoryDrink();
                }
                else if(id == R.id.nav_dessert){
                    //categoryDessert();
                }
                return true;
            }
        });
    }


    // 필터 버튼 눌릴 때 실행 MainActivity의 FilterButton 에 onClick에 설정해둠
    public void filter(View v){
        // 메인 화면에서의 필터라면 mainFilter 실행
        mainFilter();
        // 검색 화면에서의 카페 필터라면 cafeFilter 실행
        //cafeFilter();
    }

    // 검색 화면에서 카페 필터시
    public void cafeFilter(){
        Intent intent = new Intent(getApplicationContext(), SearchFilter.class);
        intent.putExtra("cafeState", cafeState);
        startActivityForResult(intent, 1);
    }

    // 메인 화면에서 단순히 가격 필터시
    public void mainFilter(){
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

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수

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
            case 1 :
                searchFiltering(resultCode);
                break;
            case 2 :
                categorySetting(resultCode);
                break;
            default:
                break;
        }
    }
    // 카테고리 분류 메소드
    public void categorySetting(int resultCode){
        categoryState = resultCode;

        // 코드 추가
        switch (categoryState){
            case 1 :
                Toast.makeText(getApplicationContext(), "카페인 선택", Toast.LENGTH_LONG).show();
                switch (subcategoryState){
                    case 5 :
                        Toast.makeText(getApplicationContext(), "에스프레소&라떼 선택", Toast.LENGTH_LONG).show();
                        break;
                    case 6 :
                        Toast.makeText(getApplicationContext(), "콜드브루 선택", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                break;
            case 2 :
                Toast.makeText(getApplicationContext(), "디카페인 선택", Toast.LENGTH_LONG).show();
                break;
            case 3 :
                Toast.makeText(getApplicationContext(), "라떼 선택", Toast.LENGTH_LONG).show();
                break;
            case 4 :
                Toast.makeText(getApplicationContext(), "블렌디드 선택", Toast.LENGTH_LONG).show();
                switch (subcategoryState){
                    case 1 :
                        Toast.makeText(getApplicationContext(), "프라페 선택", Toast.LENGTH_LONG).show();
                        break;
                    case 2 :
                        Toast.makeText(getApplicationContext(), "쉐이크 선택", Toast.LENGTH_LONG).show();
                        break;
                    case 3 :
                        Toast.makeText(getApplicationContext(),"스무디 선택", Toast.LENGTH_LONG).show();
                        break;
                    case 4 :
                        Toast.makeText(getApplicationContext(), "과일주스 선택", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                break;
            case 5 :
                Toast.makeText(getApplicationContext(), "요거트 선택", Toast.LENGTH_LONG).show();
                break;
            case 6 :
                Toast.makeText(getApplicationContext(), "에이드 선택", Toast.LENGTH_LONG).show();
                break;
            case 7 :
                Toast.makeText(getApplicationContext(), "티 선택", Toast.LENGTH_LONG).show();
                break;
            case 8 :
                Toast.makeText(getApplicationContext(), "케이크 선택", Toast.LENGTH_LONG).show();
                break;
            case 9 :
                Toast.makeText(getApplicationContext(), "아이스크림 선택", Toast.LENGTH_LONG).show();
                break;
            case 10 :
                Toast.makeText(getApplicationContext(), "샌드위치 선택", Toast.LENGTH_LONG).show();
                break;
            case 11 :
                Toast.makeText(getApplicationContext(), "베이커리 선택", Toast.LENGTH_LONG).show();
                break;
            case 12 :
                Toast.makeText(getApplicationContext(), "샐러드 선택", Toast.LENGTH_LONG).show();
                break;
            case 13 :
                Toast.makeText(getApplicationContext(), "빙수 선택", Toast.LENGTH_LONG).show();
                break;
            case 14 :
                Toast.makeText(getApplicationContext(), "기타 선택", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    public void searchFiltering(int resultCode){
        // 현재 상태를 저장한다.
        cafeState = resultCode;
        // 마지막 비트를 확인해서 가격 오름, 내림 정렬을 결정한다.
        dataSort(cafeState % 2);

        // 이곳에 cafeState의 각 비트를 확인해서 특정 카페를 데이터에서 받아와 list에 넣거나
        // 혹은 이미 list에 있는 데이터 중 특정 카페를 제거 하는 식의 코드를 넣으면 됨
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

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

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
        list = new ArrayList<>();
        list.add(new DataPage(R.drawable.sample_1, "아메리카노", "스타벅스", 4900));
        list.add(new DataPage(R.drawable.sample_2, "아메리카노", "투썸플레이스", 4100));
        list.add(new DataPage(R.drawable.sample_3, "아메리카노", "이디야커피", 3000));
        list.add(new DataPage(R.drawable.sample_4, "망고 스무디", "공차", 5300));
        list.add(new DataPage(R.drawable.sample_5, "치즈폼 딥초코 스무디", "공차", 5300));
        list.add(new DataPage(R.drawable.sample_6, "바닐라 딜라이트", "할리스", 4500));
        list.add(new DataPage(R.drawable.sample_7, "에스프레소", "할리스", 2500));
        list.add(new DataPage(R.drawable.sample_8, "고구마라떼", "메가커피", 3000));
        list.add(new DataPage(R.drawable.sample_9, "자몽티", "메가커피", 3000));
        list.add(new DataPage(R.drawable.sample_10, "타로 밀크티", "공차", 4000));
        list.add(new DataPage(R.drawable.sample_11, "녹차라떼", "빽다방", 3000));
        list.add(new DataPage(R.drawable.sample_12, "달콤아이스티", "빽다방", 2500));
        list.add(new DataPage(R.drawable.sample_13, "긴페스츄리와플", "빽다방", 3000));
        list.add(new DataPage(R.drawable.sample_14, "큰마들렌", "빽다방", 3000));
        list.add(new DataPage(R.drawable.sample_15, "완전딸기바나나 빽스치노(SOFT)", "빽다방", 4000));

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

