
package com.android.example.androidexam.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.example.androidexam.R;

/**
 * Created by ws on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

//        TextView textView = (TextView) findViewById(R.id.tv_sign_up);
//        SpannableString content = new SpannableString("회원가입");
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        textView.setText(content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
            break;
            case R.id.btn_login:
            // Todo 로그인 처리
                break;

        }
    }
}
