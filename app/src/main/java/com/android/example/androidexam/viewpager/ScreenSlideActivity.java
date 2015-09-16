
package com.android.example.androidexam.viewpager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.example.androidexam.R;
import com.android.example.androidexam.fragment.ColorFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ws on 2015-09-16.
 */
public class ScreenSlideActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private List<Fragment> mList;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_screen_slide);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // 짝퉁 데이터
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new ColorFragment());
        }

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),
                mList);

        mViewPager.setAdapter(adapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmList;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);

            mmList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mmList.get(position);
        }

        @Override
        public int getCount() {
            return mmList.size();
        }
    }
}
