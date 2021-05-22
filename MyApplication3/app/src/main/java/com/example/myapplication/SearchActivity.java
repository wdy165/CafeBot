package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    // 데이터를 넣은 리스트변수
    private List<String> list;

    // 검색 버튼
    private Button searchButton;
    private AutoCompleteTextView autoView;
    private EditText edit;

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

        // 검색창 구현
        //list = new ArrayList<>();

        // 단어 추가
        settingList();

        autoView = (AutoCompleteTextView) findViewById(R.id.edit);

        autoView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        // 검색 버튼
        initSearchButton();

       // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new SearchActivity.ItemSelectedListener());
    }

    public void settingList(){
        // 검색창
        InputStream is = this.getResources().openRawResource(R.raw.datatest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<List<String>> ret = new ArrayList<List<String>>();

        list = new ArrayList<String>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // do something with "line"
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                list.add(array[1]);
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

/*
        String[] items = {"아메리카노", "카페라떼", "에스프레소", "카라멜마끼아또", "와플", "크로와상", "스무디",
        "딸기스무디", "요거트스무디", "요거트", "프라페", "오레오프라페", "초코프라페", "콜드브루 아메리카노",
        "아이스티", "복숭아아이스티", "케익", "베이글", "치즈베이글", "스콘"};
*/
    }

    public void initSearchButton(){
        searchButton = findViewById(R.id.search_button);
        edit = findViewById(R.id.edit);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    searchButton.setEnabled(false);
                } else {
                    searchButton.setEnabled(true);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                autoView = (AutoCompleteTextView) findViewById(R.id.edit);
                String searchKeyword = autoView.getText().toString();
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.ResultActivity.class);
                intent.putExtra("검색어", searchKeyword);
                startActivity(intent);

            }
        });
    };

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