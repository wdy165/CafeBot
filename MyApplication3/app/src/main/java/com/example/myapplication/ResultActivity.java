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
    private int cafeState = 0b1111111111111111110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        // 텍스트 검색결과값 가져오기
        //this.findSearchDataValue();

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
        // 마지막 비트를 확인해서 가격 오름, 내림 정렬을 결정한다.
        dataSort(cafeState % 2);

        // 이곳에 cafeState의 각 비트를 확인해서 특정 카페를 데이터에서 받아와 list에 넣거나
        // 혹은 이미 list에 있는 데이터 중 특정 카페를 제거 하는 식의 코드를 넣으면 됨

        Intent dataIntent = getIntent();
        String filterValue;

        InputStream is = this.getResources().openRawResource(R.raw.cafe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);

        list = new ArrayList<>();
        searchList = viewpage();

        int i = 0;

        list = searchList;
        filterValue = dataIntent.getStringExtra("카페필터");
        if(filterValue == null){
            Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_LONG).show();
        }
        try {
            String line;

            while ((line = reader.readLine()) != null && (i < typedArray.length())) {
                // do something with "line"
                String array[] = line.split(",");

                if(filterValue != null && filterValue.contains(array[1])) {
                    list.remove(i);
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
        /*newList = list;

        if(cafeState == 0b011111111111111 || cafeState == 0b011111111111110) {
            for (DataPage d:list) {
                if (d.getCafeName() == "스타벅스") {
                    newList.remove(d);
                }
            }
        }*/
        /*
        if((cafeState & 0b010000000000000) != 0b010000000000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "커피빈") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b001000000000000) != 0b001000000000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "탐앤탐스") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000100000000000) != 0b000100000000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "엔제리너스") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000010000000000) != 0b000010000000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "빽다방") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000001000000000) != 0b000001000000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "이디야커피") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000100000000) != 0b000000100000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "파스쿠찌") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000010000000) != 0b000000010000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "커피베이") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000001000000) != 0b000000001000000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "할리스커피") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000000100000) != 0b000000000100000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "공차") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000000010000) != 0b000000000010000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "메가커피") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000000001000) != 0b000000000001000) {
            for (DataPage d:list) {
                if (d.getCafeName() == "카페베네") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000000000100) != 0b000000000000100) {
            for (DataPage d:list) {
                if (d.getCafeName() == "요거프레소") {
                    newList.remove(d);
                }
            }
        }
        if((cafeState & 0b000000000000010) == 0b000000000000010) {
            for (DataPage d:list) {
                if (d.getCafeName() == "투썸플레이스") {
                    newList.remove(d);
                }
            }
        }*/

        /*Intent dataIntent = getIntent();
        String filterValue;
        String[] filterArr;
        InputStream is = this.getResources().openRawResource(R.raw.datatest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);

        String line = reader.readLine();
        String array[] = line.split(",");
        list = viewpage();
        // 체크하지않은 카페필터값 가져오기
        filterValue = dataIntent.getStringExtra("카페필터");
        filterArr = filterValue.split(",");
        int i = 0;
*/

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

    private ArrayList<DataPage> viewpage(){
        Intent dataIntent = getIntent();
        String keyword;

        InputStream is = this.getResources().openRawResource(R.raw.cafe_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        TypedArray typedArray = getResources().obtainTypedArray(R.array.all_menu);
        list = new ArrayList<>();
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
