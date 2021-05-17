package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;


public class QnaActivity extends AppCompatActivity {

    private EditText title; // 제목
    private EditText message; // 본문
    private EditText addr; // 나중에 QnA 답변을 받을 이메일 주소
    private Button button; // 전송 버튼

    // 발신자와 수신자는 모두 관리자
    private String user = ""; // 보내는 사람의 이메일 주소
    private String pwd = ""; // 보내는 사람의 이메일 비밀번호

    // 바텀네비게이션뷰
    private BottomNavigationView bottomNav;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        perInternet(); // 인터넷 권한 허용

        submitButton(); // 전송 버튼 이벤트 처리

        // 바텀네비게이션
        bottomNav = findViewById(R.id.navigationView);
        bottomNav.setOnNavigationItemSelectedListener(new QnaActivity.ItemSelectedListener());
    }

    // 인터넷 권한 허용
    public void perInternet() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());
    }

    // 입력값 설정
    public String[] setInput() {
        title = (EditText) findViewById(R.id.em_title);
        message = (EditText) findViewById(R.id.em_msg);
        addr = (EditText) findViewById(R.id.em_address);

        String tt = title.getText().toString();
        String msg = message.getText().toString();
        String ad = addr.getText().toString();
        String next = "\n\n";
        String t_msg = msg + next + ad;
        
        return new String[] {tt, t_msg, ad};
    }

    // Submit 버튼 이벤트 처리
    public void submitButton() {
        button = (Button) findViewById(R.id.em_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GMailSender gMailSender = new GMailSender(user, pwd);

                    String input[] = setInput(); // 제목과 본문 내용 받아옴

                    if (TextUtils.isEmpty(input[0]) || TextUtils.isEmpty(input[1]) || TextUtils.isEmpty(input[2])) {
                        Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        //GMailSender.sendMail(제목, 본문 내용, 받는 사람);
                        gMailSender.sendMail(input[0], input[1], user); // 메일 전송
                        Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();

                        // 메일 전송 후 메인 화면으로 전환
                        Intent intent7 = new Intent(getApplicationContext(), com.example.myapplication.MainActivity.class);
                        startActivity(intent7);
                    }
                } catch (SendFailedException e) {
                    Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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