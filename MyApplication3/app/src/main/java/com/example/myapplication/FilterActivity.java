package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FilterActivity extends AppCompatActivity {

    private int state = 0;
    private RadioButton sortLow;
    private RadioButton sortHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        sortLow = (RadioButton)findViewById(R.id.sortLow1);
        sortHigh = (RadioButton)findViewById(R.id.sortHigh1);

        // mainAcitivity 에서 넘겨받은 state를 이곳의 state에 저장한다.
        Intent intent = getIntent();
        state = intent.getExtras().getInt("state");

        // 넘겨받은 state를 확인하고 오름, 내림 차순에 따라 체크박스를 선택한다.
        if(state == 0){
            sortLow.setChecked(true);
        }
        else if(state == 1){
            sortHigh.setChecked(true);
        }
    }

    // acitivity_filter의 적용 버튼에 onClick에 연결된 함수
    public void goback(View v){
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);
        int i = rg.getCheckedRadioButtonId();

        // 현재 무엇이 체크되었는지 보고 상태를 저장한다.
        if(i==R.id.sortLow1){
            state = 0;
        }
        else if(i==R.id.sortHigh1){
            state = 1;
        }

        // 상태를 인탠트에 담아 mainActivity에 넘긴다.
        Intent intent = new Intent();
        intent.putExtra("state", state);
        setResult(state, intent);
        finish();
    }
}