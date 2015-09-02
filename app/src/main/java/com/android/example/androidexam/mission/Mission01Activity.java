
package com.android.example.androidexam.mission;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.example.androidexam.R;

public class Mission01Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView1;
    private ImageView mImageView2;

    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        Button mUpBtn = (Button) findViewById(R.id.up_btn);
        mUpBtn.setOnClickListener(this);
        Button mDownBtn = (Button) findViewById(R.id.down_btn);
        mDownBtn.setOnClickListener(this);

        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);

        // mImageView1.setImageResource(R.drawable.cat);

        mBitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.cat);
        mImageView1.setImageBitmap(mBitmap);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.up_btn:
                mImageView1.setImageBitmap(mBitmap);
                mImageView2.setImageBitmap(null);
                break;

            case R.id.down_btn:
                mImageView2.setImageBitmap(mBitmap);
                mImageView1.setImageBitmap(null);
                break;
        }
    }
}
