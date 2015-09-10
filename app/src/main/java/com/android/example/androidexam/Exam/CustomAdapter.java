
package com.android.example.androidexam.Exam;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.androidexam.R;

import java.util.List;
import java.util.Random;

/**
 * Created by ws on 2015-09-10.
 */
public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<Data> mData;

    public CustomAdapter(Context context, List<Data> data) {
        mData = data;
        mContext = context;

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.exam_item, parent, false);
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView number = (TextView) convertView.findViewById(R.id.number);

            holder.image = image;
            holder.name = name;
            holder.number = number;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Data data = (Data) getItem(position);
        Log.d("test", "getView " + data.getName());

        holder.image.setImageResource(data.getImageResourceId());
        holder.name.setText(data.getName());
        holder.number.setText(data.getNumber());

        Random rnd = new Random();
        int color = getArgb(rnd);
        convertView.setBackgroundColor(color);

        return convertView;
    }

    private int getArgb(Random rnd) {
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView number;
    }
}
