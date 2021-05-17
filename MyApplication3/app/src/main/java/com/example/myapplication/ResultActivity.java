package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    // 검색
    private TextView resultText;

    // 가운데
    private ViewPager2 result_ViewPager2;
    private DotsIndicator dotsIndicator;
    private ArrayList<DataPage> list;
    private TextView resultNum;
    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();

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

        resultNum = (TextView) findViewById(R.id.resultSumNum);
        int dataNum = list.size();
        resultNum.setText(String.valueOf(dataNum));

        result_ViewPager2 = findViewById(R.id.resultViewPager2);
        result_ViewPager2.setAdapter(new ViewPagerAdapter(list));
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
