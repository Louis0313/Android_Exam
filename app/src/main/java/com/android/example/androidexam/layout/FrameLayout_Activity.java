
package com.android.example.androidexam.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.example.androidexam.R;

public class FrameLayout_Activity extends AppCompatActivity {

    private Button mChangeBtn;
    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_);

        mChangeBtn = (Button) findViewById(R.id.change_btn);
        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);

        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();

            }
        });

    }

    public void changeImage() {
        if (mImageView1.getVisibility() == View.VISIBLE) {

            mImageView1.setVisibility(View.INVISIBLE);
            mImageView2.setVisibility(View.VISIBLE);
        } else {
            mImageView2.setVisibility(View.INVISIBLE);
            mImageView1.setVisibility(View.VISIBLE);
        }

    }
}
