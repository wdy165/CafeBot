package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

interface OnTimePickerSetListener{
    void onTimePickerSet(int state);
}

public class DrinkMenu extends AppCompatActivity implements OnTimePickerSetListener {
    private int categoryState = 0;
    private int subcategoryState = 0;

    private RadioGroup radioGroup;
    private RadioButton caffeine;
    private RadioButton decaffeine;
    private RadioButton latte;
    private RadioButton blended;
    private RadioButton yogurt;
    private RadioButton ade;
    private RadioButton tea;
    private TextView textView;

    FragmentBlended fragmentBlended;
    FragmentCaffeine fragmentCaffeine;

    public void onTimePickerSet(int state){
        subcategoryState = state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup3);
        caffeine = (RadioButton)findViewById(R.id.caffeine);
        decaffeine = (RadioButton)findViewById(R.id.decaffeine);
        latte = (RadioButton)findViewById(R.id.latte);
        blended = (RadioButton)findViewById(R.id.blended);
        yogurt = (RadioButton)findViewById(R.id.yogurt);
        ade = (RadioButton)findViewById(R.id.ade);
        tea = (RadioButton)findViewById(R.id.tea);

        fragmentBlended = new FragmentBlended();
        fragmentCaffeine = new FragmentCaffeine();
        textView = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();
        categoryState = intent.getExtras().getInt("categoryState");
        subcategoryState = intent.getExtras().getInt("subcategoryState");

        switch (categoryState){
            case 1 :
                caffeine.setChecked(true);
                textView.setText("카페인");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("subcategoryState", subcategoryState);
                fragmentCaffeine.setArguments(bundle);

                transaction.replace(R.id.fragment_container, fragmentCaffeine);
                transaction.commit();
                break;
            case 2 :
                decaffeine.setChecked(true);
                break;
            case 3 :
                latte.setChecked(true);
                break;
            case 4 :
                blended.setChecked(true);
                textView.setText("블렌디드");
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("subcategoryState", subcategoryState);
                fragmentBlended.setArguments(bundle2);

                transaction2.replace(R.id.fragment_container, fragmentBlended);
                transaction2.commit();
                break;
            case 5 :
                yogurt.setChecked(true);
                break;
            case 6 :
                ade.setChecked(true);
                break;
            case 7 :
                tea.setChecked(true);
                break;
            default:
                break;
        }
    }
    public void selectElse(View v){
        textView.setText("");

        if(fragmentBlended.isAdded()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(fragmentBlended);
            transaction.commit();
        }
        else if (fragmentCaffeine.isAdded()){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(fragmentCaffeine);
            transaction.commit();
        }
    }

    public void selectCaffeine(View v){
        textView.setText("카페인");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragmentCaffeine);
        transaction.commit();
    }

    public void selectBlended(View v){
        textView.setText("블렌디드");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragmentBlended);
        transaction.commit();
    }

    public void goback(View v){
        int i = radioGroup.getCheckedRadioButtonId();
        String categoryName;
        Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CategoryResultActivity.class);

        switch(i){
            case R.id.caffeine :
                categoryState = 1;
                break;
            case R.id.decaffeine :
                categoryState = 2;
                categoryName = "디카페인";
                intent.putExtra("카테고리명", categoryName);
                break;
            case R.id.latte :
                categoryState = 3;
                categoryName = "라떼";
                intent.putExtra("카테고리명", categoryName);
                break;
            case R.id.blended :
                categoryState = 4;
                break;
            case R.id.yogurt :
                categoryState = 5;
                categoryName = "요거트";
                intent.putExtra("카테고리명", categoryName);
                break;
            case R.id.ade :
                categoryState = 6;
                categoryName = "에이드";
                intent.putExtra("카테고리명", categoryName);
                break;
            case R.id.tea :
                categoryState = 7;
                categoryName = "티";
                intent.putExtra("카테고리명", categoryName);
                break;
            default:
                categoryState = 0;
                break;
        }

        if(subcategoryState == 1){
            categoryName = "프라페";
            intent.putExtra("카테고리명", categoryName);
        }
        else if(subcategoryState == 2){
            categoryName = "쉐이크";
            intent.putExtra("카테고리명", categoryName);
        }
        else if(subcategoryState == 3){
            categoryName = "스무디";
            intent.putExtra("카테고리명", categoryName);
        }
        else if(subcategoryState == 4){
            categoryName = "과일주스";
            intent.putExtra("카테고리명", categoryName);
        }
        else if(subcategoryState == 5){
            categoryName = "에스프레소&라떼";
            intent.putExtra("카테고리명", categoryName);
        }else if(subcategoryState == 6){
            categoryName = "콜드브루";
            intent.putExtra("카테고리명", categoryName);
        }
        intent.putExtra("categoryState", categoryState);
        intent.putExtra("subcategoryState", subcategoryState);
        setResult(categoryState, intent);
        startActivity(intent);
        finish();

    }
}