package com.example.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ResultActivity extends AppCompatActivity {
    // 검색
    private TextView resultText;

    // 가운데
    private ViewPager2 result_ViewPager2;
    private ViewPagerAdapter pageNum;
    private ArrayList<DataPage> list, searchList;
    private TextView resultNum, resultCur;

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private int state = 0;
    private int cafeState = 0b111111111111110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        list = new ArrayList<>();

        // 뷰페이저 (메뉴검색결과값)
        this.viewpage();

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new ResultActivity.ItemSelectedListener());
    }

    // 검색 화면에서 카페 필터시
    public void cafeFilter(View v) {
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

    public void searchFiltering(int resultCode) {
        // 현재 상태를 저장한다.
        cafeState = resultCode;

        searchList = new ArrayList<>();
        searchList.addAll(list);

        int listSize = searchList.size();

        ArrayList<DataPage> temp = new ArrayList<>();

        if((cafeState & 0b100000000000000) == 0b100000000000000){
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("스타벅스")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b010000000000000) == 0b010000000000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("커피빈")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b001000000000000) == 0b001000000000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("탐앤탐스")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000100000000000) == 0b000100000000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("엔제리너스")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000010000000000) == 0b000010000000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("빽다방")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000001000000000) == 0b000001000000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("이디야")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000100000000) == 0b000000100000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("파스쿠찌")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000010000000) == 0b000000010000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("커피베이")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000001000000) == 0b000000001000000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("할리스")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000000100000) == 0b000000000100000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("공차")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000000010000) == 0b000000000010000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("메가커피")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000000001000) == 0b000000000001000) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("카페베네")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000000000100) == 0b000000000000100) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("요거프레소")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }
        if((cafeState & 0b000000000000010) == 0b000000000000010) {
            for(int i = 0; i < listSize; i++){
                if(searchList.get(i).getCafeName().equals("투썸플레이스")){
                    temp.add(searchList.get(i));
                    searchList.remove(i);
                    i--;
                    listSize--;
                }
            }
        }

        searchList.clear();
        searchList.addAll(temp);

        // 마지막 비트를 확인해서 가격 오름, 내림 정렬을 결정한다.
        dataSort(cafeState % 2, searchList);

        resultCur = (TextView) findViewById(R.id.resultCurNum);
        resultNum = (TextView) findViewById(R.id.resultSumNum);

        result_ViewPager2 = findViewById(R.id.resultViewPager2);
        result_ViewPager2.setAdapter(new ViewPagerAdapter(searchList));

        // 총 페이지 숫자
        pageNum = new ViewPagerAdapter(searchList);
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


    // sortValue에 따라 오름, 내림 차순 정렬
    public void dataSort(int sortValue, ArrayList<DataPage> list) {
        if(sortValue == 0){
            Collections.sort(list);
        }
        else if(sortValue == 1){
            Collections.sort(list);
            Collections.reverse(list);
        }
    }

    private ArrayList<DataPage> viewpage(){
        Intent dataIntent = getIntent();
        String keyword;

        InputStream is = this.getResources().openRawResource(R.raw.cafe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);
        int i = 0;

        // 검색 키워드값 가져오기
        keyword = dataIntent.getStringExtra("검색어");
        resultText = (TextView) findViewById(R.id.resultText1);
        resultText.setText(keyword);
        try {
            String line;
            while ((line = reader.readLine()) != null && (i < typedArray.length())) {
                // do something with "line"
                String array[] = line.split(",");

                if(array[0].contains(keyword)) {
                    list.add(new DataPage(typedArray.getResourceId(i, -1), array[0], array[1], array[2], Integer.parseInt(array[3])));
                }
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
        return list;
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
