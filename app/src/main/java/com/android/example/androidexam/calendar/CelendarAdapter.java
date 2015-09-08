package com.android.example.androidexam.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ws on 2015-09-08.
 */
public class CelendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;


    public CelendarAdapter(Context context, List<Calendar> list) {
        mContext = context;
        mList = list;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
