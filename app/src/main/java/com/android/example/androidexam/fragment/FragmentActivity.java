
package com.android.example.androidexam.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.example.androidexam.R;

import java.util.Random;

/**
 * Created by ws on 2015-09-15.
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorFragment mColorFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mColorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(
                R.id.frag_color);

        findViewById(R.id.button).setOnClickListener(this);

        // getSupportFragmentManager().beginTransaction().add()
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onClick(View v) {
        mColorFragment.setColor(getRandomColor());
    }
}
