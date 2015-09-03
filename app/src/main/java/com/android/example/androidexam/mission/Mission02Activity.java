package com.android.example.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.example.androidexam.R;

public class Mission02Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = Mission02Activity.class.getSimpleName();

    private EditText mMessageEditText;
    private TextView mByteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        mMessageEditText = (EditText) findViewById(R.id.edit_text);
        mByteTextView = (TextView) findViewById(R.id.maximum_byte);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged : " + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged : " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "onTextChanged : " + s.toString());

                int length = s.toString().getBytes().length;
                mByteTextView.setText(length + " / 80 바이트");

            }
        });


    }


    @Override
    public void onClick(View v) {


        Button mSendBtn = (Button) findViewById(R.id.send_btn);
        mSendBtn.setOnClickListener(this);
        Button mCloseBtn = (Button) findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);
        Button btn = (Button) findViewById(R.id.send_btn);

        switch (v.getId()) {
            case R.id.send_btn:
                EditText edit = (EditText) findViewById(R.id.edit_text);
                String str = edit.getText().toString();
                Toast.makeText(Mission02Activity.this, "", Toast.LENGTH_SHORT).show();
                break;

            case R.id.close_btn:
                finish();

        }
    }
}
;


// .getByte().length;