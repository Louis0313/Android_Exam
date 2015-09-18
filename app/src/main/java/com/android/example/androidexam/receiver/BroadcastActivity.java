
package com.android.example.androidexam.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by ws on 2015-09-16.
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        button.setText("브로드 캐스트 발송");
        button.setOnClickListener(this);

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        linearLayout.addView(button);

        setContentView(linearLayout);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        sendBroadcast(intent);
    }

    private BroadcastReceiver mReceiver;

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new MyReceiver(), filter);

        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
