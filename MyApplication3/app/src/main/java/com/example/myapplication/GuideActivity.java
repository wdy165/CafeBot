package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;



public class GuideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guideline);

        startLoading();
    }
//가이드라인 레이아웃 실행

    private void startLoading() {

            Button btnclose = (Button) findViewById(R.id.close) ;
            btnclose.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent4 = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent4);

                }
            }) ;

    }
}
//가이드라인에서 닫기 버튼을 누르면 메인화면이 출력됨.