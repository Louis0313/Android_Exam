
package com.android.example.androidexam.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.example.androidexam.R;

public class ActivityExamActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_STRING = 1000;
    public static final int REQUEST_CODE_PICTURE = 1001;
    private EditText mNameEditText;
    private EditText mPhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mPhoneEditText = (EditText) findViewById(R.id.phone_edit_text);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.dialog_btn).setOnClickListener(this);
    }

    @Override
    // v = 눌린 객체
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                btn1click();
                break;
            case R.id.btn2:
                // TargetActivity 로 이동
                btn2click();
                break;
            case R.id.dialog_btn:
                openDialog();
                break;
        }

    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityExamActivity.this);
        builder.setTitle("타이틀");
        builder.setMessage("메세지");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ActivityExamActivity.this, "확인 눌렸음", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("닫기", null);

        builder.setIcon(R.mipmap.ic_launcher);

        builder.create(); // AlertDialog 를 최종 생성
        builder.show(); // 화면에 표시
    }

    private void btn1click() {
        // TargetActivity 로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhoneEditText.getText().toString());

        startActivity(intent);
    }

    private void btn2click() {
        // TargetActivity 로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhoneEditText.getText().toString());

        // ==========주는 곳===========
        //
        // 데이터를 돌려 받기 위해서는 StartActivityForResult 로 호출 해야 됨
        // 리퀘스트 코드는 원하는 값을 받기 위한 약속같은 것
        startActivityForResult(intent, REQUEST_CODE_STRING);
    }

    // ==========받는 곳===========
    //
    // startActivity 로 호출 후에 데이터를 리턴 받는 곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 결과가 OK 냐
        if (resultCode == RESULT_OK) {
            // RequestCode 에 따라 처리를 나눈다.
            if (requestCode == REQUEST_CODE_STRING) {
                if (data != null) {
                    String result = data.getStringExtra("result");
                    Toast.makeText(ActivityExamActivity.this, "result : " + result,
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(ActivityExamActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
