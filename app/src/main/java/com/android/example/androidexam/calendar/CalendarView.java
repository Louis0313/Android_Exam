
package com.android.example.androidexam.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by ws on 2015-09-08.
 */
public class CalendarView extends GridView implements AdapterView.OnItemClickListener {
    // 코드상에서 생성될 때 호출 하는 생성자
    public CalendarView(Context context) {
        this(context, null);
    }

    // xml 에 정의 되었을 때 호출 되는 생성자
    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // Wiget 에서 호출 했을 때
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setNumColumns(7); // 7일로 설정
        setBackgroundResource(android.R.color.darker_gray); // 배경 회색
        setHorizontalSpacing(1);
        setVerticalSpacing(1);

        // 아이템 클릭 이벤트
        setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getAdapter() != null) {
            if (getAdapter() instanceof CalendarAdapter) {
                CalendarAdapter adapter = (CalendarAdapter) getAdapter();
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            } else {
                // throw = 에러가 났을 때 넘기는 거
                throw new IllegalStateException("CalendarAdapter 를 셋팅 해야 합니다");
            }
        }
    }
}
