
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SearchFilter extends AppCompatActivity {

    private int cafeState = 0b0;
    private RadioButton sortLow;
    private RadioButton sortHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        sortLow = (RadioButton)findViewById(R.id.sortLow2);
        sortHigh = (RadioButton)findViewById(R.id.sortHigh2);

        CheckBox cb1 = (CheckBox)findViewById(R.id.cb1);
        CheckBox cb2 = (CheckBox)findViewById(R.id.cb2);
        CheckBox cb3 = (CheckBox)findViewById(R.id.cb3);
        CheckBox cb4 = (CheckBox)findViewById(R.id.cb4);
        CheckBox cb5 = (CheckBox)findViewById(R.id.cb5);
        CheckBox cb6 = (CheckBox)findViewById(R.id.cb6);
        CheckBox cb7 = (CheckBox)findViewById(R.id.cb7);
        CheckBox cb8 = (CheckBox)findViewById(R.id.cb8);
        CheckBox cb9 = (CheckBox)findViewById(R.id.cb9);
        CheckBox cb10 = (CheckBox)findViewById(R.id.cb10);
        CheckBox cb11 = (CheckBox)findViewById(R.id.cb11);
        CheckBox cb12 = (CheckBox)findViewById(R.id.cb12);
        CheckBox cb13 = (CheckBox)findViewById(R.id.cb13);
        CheckBox cb14 = (CheckBox)findViewById(R.id.cb14);
        CheckBox cb15 = (CheckBox)findViewById(R.id.cb15);
        CheckBox cb16 = (CheckBox)findViewById(R.id.cb16);
        CheckBox cb17 = (CheckBox)findViewById(R.id.cb17);
        CheckBox cb18 = (CheckBox)findViewById(R.id.cb18);


        // mainAcitivity 에서 넘겨받은 state를 이곳의 state에 저장한다.
        Intent intent = getIntent();
        cafeState = intent.getExtras().getInt("cafeState");

        // 넘겨받은 state를 각 비트별로 확인 후 무엇이 체크되어있는지 체크한다.
        // 마지막 비트는 오름, 내림 차순 결정 비트로 그에 따라 체크한다.
        if((cafeState & 0b1000000000000000000) == 0b1000000000000000000) cb1.setChecked(true);
        if((cafeState & 0b0100000000000000000) == 0b0100000000000000000) cb2.setChecked(true);
        if((cafeState & 0b0010000000000000000) == 0b0010000000000000000) cb3.setChecked(true);
        if((cafeState & 0b0001000000000000000) == 0b0001000000000000000) cb4.setChecked(true);
        if((cafeState & 0b0000100000000000000) == 0b0000100000000000000) cb5.setChecked(true);
        if((cafeState & 0b0000010000000000000) == 0b0000010000000000000) cb6.setChecked(true);
        if((cafeState & 0b0000001000000000000) == 0b0000001000000000000) cb7.setChecked(true);
        if((cafeState & 0b0000000100000000000) == 0b0000000100000000000) cb8.setChecked(true);
        if((cafeState & 0b0000000010000000000) == 0b0000000010000000000) cb9.setChecked(true);
        if((cafeState & 0b0000000001000000000) == 0b0000000001000000000) cb10.setChecked(true);
        if((cafeState & 0b0000000000100000000) == 0b0000000000100000000) cb11.setChecked(true);
        if((cafeState & 0b0000000000010000000) == 0b0000000000010000000) cb12.setChecked(true);
        if((cafeState & 0b0000000000001000000) == 0b0000000000001000000) cb13.setChecked(true);
        if((cafeState & 0b0000000000000100000) == 0b0000000000000100000) cb14.setChecked(true);
        if((cafeState & 0b0000000000000010000) == 0b0000000000000010000) cb15.setChecked(true);
        if((cafeState & 0b0000000000000001000) == 0b0000000000000001000) cb16.setChecked(true);
        if((cafeState & 0b0000000000000000100) == 0b0000000000000000100) cb17.setChecked(true);
        if((cafeState & 0b0000000000000000010) == 0b0000000000000000010) cb18.setChecked(true);
        if ((cafeState & 0b0000000000000000001) == 0b0000000000000000001) {
            sortHigh.setChecked(true);
        } else {
            sortLow.setChecked(true);
        }
    }

    public void goback(View v){
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup2);
        int i = rg.getCheckedRadioButtonId();

        CheckBox cb1 = (CheckBox)findViewById(R.id.cb1);
        CheckBox cb2 = (CheckBox)findViewById(R.id.cb2);
        CheckBox cb3 = (CheckBox)findViewById(R.id.cb3);
        CheckBox cb4 = (CheckBox)findViewById(R.id.cb4);
        CheckBox cb5 = (CheckBox)findViewById(R.id.cb5);
        CheckBox cb6 = (CheckBox)findViewById(R.id.cb6);
        CheckBox cb7 = (CheckBox)findViewById(R.id.cb7);
        CheckBox cb8 = (CheckBox)findViewById(R.id.cb8);
        CheckBox cb9 = (CheckBox)findViewById(R.id.cb9);
        CheckBox cb10 = (CheckBox)findViewById(R.id.cb10);
        CheckBox cb11 = (CheckBox)findViewById(R.id.cb11);
        CheckBox cb12 = (CheckBox)findViewById(R.id.cb12);
        CheckBox cb13 = (CheckBox)findViewById(R.id.cb13);
        CheckBox cb14 = (CheckBox)findViewById(R.id.cb14);
        CheckBox cb15 = (CheckBox)findViewById(R.id.cb15);
        CheckBox cb16 = (CheckBox)findViewById(R.id.cb16);
        CheckBox cb17 = (CheckBox)findViewById(R.id.cb17);
        CheckBox cb18 = (CheckBox)findViewById(R.id.cb18);

        // 오름, 내림 차순 비트를 결정한다.
        if(i==R.id.sortLow2){
            cafeState = 0b0000000000000000000;
        }
        else if(i==R.id.sortHigh2){
            cafeState = 0b0000000000000000001;
        }

        // 현재 어떤 카페가 체크되어 있는지 현재 상태를 저장한다.
        if(cb1.isChecked()) cafeState += 0b1000000000000000000;
        if(cb2.isChecked()) cafeState += 0b0100000000000000000;
        if(cb3.isChecked()) cafeState += 0b0010000000000000000;
        if(cb4.isChecked()) cafeState += 0b0001000000000000000;
        if(cb5.isChecked()) cafeState += 0b0000100000000000000;
        if(cb6.isChecked()) cafeState += 0b0000010000000000000;
        if(cb7.isChecked()) cafeState += 0b0000001000000000000;
        if(cb8.isChecked()) cafeState += 0b0000000100000000000;
        if(cb9.isChecked()) cafeState += 0b0000000010000000000;
        if(cb10.isChecked()) cafeState += 0b0000000001000000000;
        if(cb11.isChecked()) cafeState += 0b0000000000100000000;
        if(cb12.isChecked()) cafeState += 0b0000000000010000000;
        if(cb13.isChecked()) cafeState += 0b0000000000001000000;
        if(cb14.isChecked()) cafeState += 0b0000000000000100000;
        if(cb15.isChecked()) cafeState += 0b0000000000000010000;
        if(cb16.isChecked()) cafeState += 0b0000000000000001000;
        if(cb17.isChecked()) cafeState += 0b0000000000000000100;
        if(cb18.isChecked()) cafeState += 0b0000000000000000010;

        // 현재 상태를 인텐트로 넘긴다.
        Intent intent = new Intent();
        intent.putExtra("cafeSate", cafeState);
        setResult(cafeState, intent);
        finish();
    }
}