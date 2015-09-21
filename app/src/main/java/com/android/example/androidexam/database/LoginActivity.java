
package com.android.example.androidexam.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.example.androidexam.R;
import com.android.example.androidexam.database.contract.UserContract;
import com.android.example.androidexam.database.helper.UserDbHelper;

/**
 * Created by ws on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDbHelper mUserDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                // Todo 로그인 처리
                // long insertedId = mUserDbHelper.insert("test", "test",
                // "test");

                Cursor cursor = mUserDbHelper.query();
                if (cursor != null) {
                    cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        long itemId = cursor.getLong(cursor
                                .getColumnIndex(UserContract.UserEntry._ID));
                        Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}

// 언더라인
// TextView textView = (TextView) findViewById(R.id.tv_sign_up);
// SpannableString content = new SpannableString("회원가입");
// content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
// textView.setText(content);
