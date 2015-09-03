
package com.android.example.androidexam.mission;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.example.androidexam.R;

public class Mission03_1Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission03_1);

        Button mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn1.setOnClickListener(this);
        Button mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn2.setOnClickListener(this);
        Button mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn3.setOnClickListener(this);

    }

    public AlertDialog createDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("고객 관리");
        builder.setPositiveButton("확인", null);

        AlertDialog dialog = createDialogBox();
        dialog.show();

        return dialog;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;

        }

    }

}
