package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Collections;

public class ResultActivity extends AppCompatActivity {
    // 검색
    private TextView resultText;

    // 가운데
    private ViewPager2 result_ViewPager2;
    private ViewPagerAdapter pageNum;
    private ArrayList<DataPage> list;
    private TextView resultNum, resultCur;

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private int state = 0;
    private int cafeState = 0b1111111111111111110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        // 텍스트 검색결과값 가져오기
        this.findSearchDataValue();

        // 뷰페이저 (메뉴검색결과값)
        this.viewpage();

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new ResultActivity.ItemSelectedListener());
    }

    // 검색 화면에서 카페 필터시
    public void cafeFilter(View v){
        Intent intent = new Intent(getApplicationContext(), SearchFilter.class);
        intent.putExtra("cafeState", cafeState);
        startActivityForResult(intent, 1);
    }

    // 액티비티 종료시 자동 실행
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode 는 cafeFilter에서 실행시 0으로 넘기고 mainFilter에서 실행시 1을 넘긴다
        // 카테고리 분류작업은 2를 넘긴다.
        switch (requestCode){
            case 1 :
                searchFiltering(resultCode);
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

    // sortValue에 따라 오름, 내림 차순 정렬
    public void dataSort(int sortValue) {
        if(sortValue == 0){
            Collections.sort(list);
        }
        else if(sortValue == 1){
            Collections.sort(list);
            Collections.reverse(list);
        }

        result_ViewPager2.setAdapter(new ViewPagerAdapter(list));
    }

    private void findSearchDataValue(){
        Intent dataIntent = getIntent();
        String keyword = dataIntent.getStringExtra("검색어");

        resultText = (TextView) findViewById(R.id.resultText1);
        resultText.setText(keyword);
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

        resultCur = (TextView) findViewById(R.id.resultCurNum);
        resultNum = (TextView) findViewById(R.id.resultSumNum);

        result_ViewPager2 = findViewById(R.id.resultViewPager2);
        result_ViewPager2.setAdapter(new ViewPagerAdapter(list));

        // 총 페이지 숫자
        pageNum = new ViewPagerAdapter(list);
        int sumNum = pageNum.getItemCount();
        resultNum.setText(String.valueOf(sumNum));
        resultCur.setText(String.valueOf(1)); // 초기값 1

        // 현재 페이지 숫자
        result_ViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                resultCur.setText(String.valueOf(position + 1));
            }
        });
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
