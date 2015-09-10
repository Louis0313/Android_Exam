
package com.android.example.androidexam.calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.example.androidexam.R;
import com.android.example.androidexam.calendar.adapter.CalendarAdapter;
import com.android.example.androidexam.calendar.adapter.TodoAdapter;
import com.android.example.androidexam.calendar.model.Schedule;
import com.android.example.androidexam.calendar.view.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;

    private TextView mTitleTextView;

    private ListView mTodoListView;
    private TodoAdapter mTodoAdapter;

    // 모든일정
    private Map<Calendar, List<Schedule>> mSchedulMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mSchedulMap = new HashMap<>();

        mTitleTextView = (TextView) findViewById(R.id.tv_title);

        // 버튼 이벤트 연결
        findViewById(R.id.btn_prev_month).setOnClickListener(this);
        findViewById(R.id.btn_next_month).setOnClickListener(this);

        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mTodoListView = (ListView) findViewById(R.id.lv_Todo);

        // 어댑터 준비
        // View 에 어댑터를 설정
        mCalendarAdapter = new CalendarAdapter(this);
        mCalendarView.setAdapter(mCalendarAdapter);

//        List<Schedule> test = new ArrayList<>();
//        test.add(new Schedule(5, 30, "땡땡이"));
//        test.add(new Schedule(6, 30, "밥 먹기"));
//        test.add(new Schedule(7, 30, "잠자기"));
//        mTodoAdapter = new TodoAdapter(this, test);
//        mTodoListView.setAdapter(mTodoAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemLongClickListener(this);
        mCalendarView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_prev_month:
                mCalendarAdapter.prevMonth();
                break;
            case R.id.btn_next_month:
                mCalendarAdapter.nextMonth();
                break;

        }
        updateTitle();

    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalender().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalender().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년" + month + "월");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
        final TimePicker timePicker = (TimePicker) layout.findViewById(R.id.picker_time);
        final EditText editText = (EditText) layout.findViewById(R.id.et_schedule);

        // LongClick 한 곳의 Calendar 객체를 얻음.
        final Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 6.0에서 deprecated 하면됨
                // getCurrentHour() -> getHour()
                // geCurrentMinute() -> getMinute()
                // 뭔가 합니다
                Schedule schedule = new Schedule(timePicker.getCurrentHour(), timePicker.getCurrentMinute(),
                        editText.getText().toString());

                // 현재 날짜에 연결 된 일정 리스트를 얻음.
                List<Schedule> list = mSchedulMap.get(calendar);

                // 만약에 리스트가 없으면 초기화 한다
                if (list == null) {
                    list = new ArrayList<Schedule>();
                }

                // 지금 추가 할 스케쥴을 추가.
                list.add(schedule);

                // 저장;
                mSchedulMap.put(calendar, list);


                // 어댑터에 표시할 스케쥴 리스트를 전달.
                mTodoAdapter = new TodoAdapter(CalendarActivity.this, list);

                // 리스트뷰에 어댑터를 연결.
                mTodoListView.setAdapter(mTodoAdapter);
            }
        });
        // View layout =
        // LayoutInflator.form(this).inflate(R.layout.dialog_schedule, null);
        builder.setView(layout);
        builder.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 선택 된 것으로 하고 색상 변경
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

        // 현재 날짜에 연결 된 일정 리스트를 얻음.
        Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
        List<Schedule> list = mSchedulMap.get(calendar);

        if (list == null) {
            list = Collections.emptyList();
        }
        // 어댑터에 표시할 스케쥴 리스트를 전달
        mTodoAdapter = new TodoAdapter(CalendarActivity.this, list);
        // 리스트뷰에 어댑터를 연결
        mTodoListView.setAdapter(mTodoAdapter);
    }
}