
package com.android.example.androidexam.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.example.androidexam.R;

/**
 * Created by ws on 2015-09-08.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private Animation mScaleAndRotateAnimation;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        mImageView = (ImageView) findViewById(R.id.ivImage);

        findViewById(R.id.btnStart).setOnClickListener(this);

        // 애니메이션
        mScaleAndRotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.anim_scale_rotate);

    }

    @Override
    public void onClick(View v) {
        mImageView.startAnimation(mScaleAndRotateAnimation);

    }
}
