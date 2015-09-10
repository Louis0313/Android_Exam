
package com.android.example.androidexam.Exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.example.androidexam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ws on 2015-09-10.
 */
public class ExamActivity extends AppCompatActivity {

    private List<Data> mData;
    private CustomAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);

        initData();
        initAdapter();
        initListView();
    }

    private void initAdapter() {
        mAdapter = new CustomAdapter(this, mData);
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mAdapter);

    }

    private void initData() {
        mData = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Data data = new Data();
            data.setImageResourceId(R.mipmap.ic_launcher);
            data.setName("item" + i);
            data.setNumber("subitem" + i);
            mData.add(data);
        }

    }
}
