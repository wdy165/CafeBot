package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DessertMenu extends AppCompatActivity {
    private int categoryState = 0;
    private RadioButton cake;
    private RadioButton icecream;
    private RadioButton sandwich;
    private RadioButton bakery;
    private RadioButton salad;
    private RadioButton bingsu;
    private RadioButton etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_menu);

        cake = (RadioButton)findViewById(R.id.cake);
        icecream = (RadioButton)findViewById(R.id.icecream);
        sandwich = (RadioButton)findViewById(R.id.sandwich);
        bakery = (RadioButton)findViewById(R.id.bakery);
        salad = (RadioButton)findViewById(R.id.salad);
        bingsu = (RadioButton)findViewById(R.id.bingsu);
        etc = (RadioButton)findViewById(R.id.etc);
        Intent intent = getIntent();

        categoryState = intent.getExtras().getInt("categoryState");

        switch (categoryState){
            case 8 :
                cake.setChecked(true);
                break;
            case 9 :
                icecream.setChecked(true);
                break;
            case 10 :
                sandwich.setChecked(true);
                break;
            case 11 :
                bakery.setChecked(true);
                break;
            case 12 :
                salad.setChecked(true);
                break;
            case 13 :
                bingsu.setChecked(true);
                break;
            case 14 :
                etc.setChecked(true);
                break;
            default:
                break;
        }
    }

    public void goback(View v){
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup4);
        int i = rg.getCheckedRadioButtonId();

        switch(i){
            case R.id.cake :
                categoryState = 8;
                break;
            case R.id.icecream :
                categoryState = 9;
                break;
            case R.id.sandwich :
                categoryState = 10;
                break;
            case R.id.bakery :
                categoryState = 11;
                break;
            case R.id.salad :
                categoryState = 12;
                break;
            case R.id.bingsu :
                categoryState = 13;
                break;
            case R.id.etc :
                categoryState = 14;
                break;
            default:
                categoryState = 0;
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("categoryState", categoryState);
        setResult(categoryState, intent);
        finish();
    }
}