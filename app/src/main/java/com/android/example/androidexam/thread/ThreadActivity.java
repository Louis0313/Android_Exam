
package com.android.example.androidexam.thread;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.androidexam.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThreadActivity.class.getSimpleName();
    private Button mThread1btn;
    private Button mThread2btn;

    private TextView mNumberTextView1;
    private TextView mNumberTextView2;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_thread);

        mThread1btn = (Button) findViewById(R.id.btn_thread1);
        mThread2btn = (Button) findViewById(R.id.btn_thread2);

        mNumberTextView1 = (TextView) findViewById(R.id.tv_number1);
        mNumberTextView2 = (TextView) findViewById(R.id.tv_number2);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mThread1btn.setOnClickListener(this);
        mThread2btn.setOnClickListener(this);
    }

    private DownloadTask mDownloadTask;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1:
                progressDialogExam();

                // 굉장히 오래 걸리는 처리 (10초)
                // for (....)

                // 완료 되었습니다.

                break;
            case R.id.btn_thread2:

                if (mDownloadTask == null || mDownloadTask.getStatus() == AsyncTask.Status.FINISHED) {
                    // 실행 할 때 마다 인스턴스 생성
                    mDownloadTask = new DownloadTask();
                    mDownloadTask.execute();

                }
                break;
        }

    }

    private void progressDialogExam() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드 중");
        progressDialog.setCancelable(false); // 뒤로 가기로 캔슬되는 것 막기
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 다운로드 끝
                    progressDialog.dismiss();

                }
            }
        }).start();

        Log.d(TAG, "ttt");
    }

    // 백그라운드 처리는 바로바로 보이지만, UI 변경은 한번에 보여진다.
    private void runOnUiThreadExam() {
        // UI Thread 로 동작하게 해 주는 Activity 제공 메소드.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 스레드가 쉬는 동안에 에러가 나면 여기서 에러 처리 위치

                    }
                    Log.d(TAG, "" + i); // bckggound
                    mNumberTextView1.setText("" + i); // foregound
                }
            }
        });
    }

    private void threadAndHandler() {
        // Handler 클래스 상속을 생략 한 것

        // 보이는 부분에서 동작하는 Thread
        // UI Thread
        // Foreground Thread
        // Main Thread
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumberTextView1.setText("" + msg.arg1);
            }
        };

        // Thread 클래스 상속을 생략 한 것

        // 안보이는 부분에서 동작하는 Thread
        // Thread
        // Background Thread
        // Worker Thread

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작 하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 스레드가 쉬는 동안에 에러가 나면 여기서 에러 처리 위치
                    }

                    Message msg = new Message();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }

    // 스레드 사용 방법1
    // background 에서 동작
    private void thread1() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작 하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 스레드가 쉬는 동안에 에러가 나면 여기서 에러 처리 위치
                    }
                    mNumberTextView1.setText("" + i);
                }
            }
        });
        thread.start();
    }

    private class DownloadTask extends AsyncTask<Void, Integer, Void> {
        private AlertDialog.Builder mmBuilder;

        // UI Thread
        // doInBackground 전에 호출 됨
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mmBuilder = new AlertDialog.Builder(ThreadActivity.this);
            mmBuilder.setMessage("다운로드가 완료 되었습니다");
            mmBuilder.setNegativeButton("닫기", null);

            mProgressBar.setProgress(0);
        }

        // Background Thread
        @Override
        protected Void doInBackground(Void... params) {
            // 다운로드 처리
            for (int i = 0; i < 100; i++) {
                // 0.2 초 쉬고
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
                // onProgressUpdate 를 호출
                publishProgress(i + 1);
            }
            return null;
        }

        // UI Thread
        // doInBackground 에서 publishProgress 로 호출하면 호출됨
        // 직접 호출 하지 않는 이유 : 죽으니까.
        //
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
            mNumberTextView2.setText(values[0] + "%");
        }

        // UI Thread
        // doInBackground 가 수행 된 후에 호출 됨
        // doInBackground 에서 return 된 값이 파라메터로 넘어 옴
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mmBuilder.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            Log.i(TAG, "Task is cancelled - 1");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);

            Log.i(TAG, "Task is cancelled - 2");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy ");

        mDownloadTask.cancel(true);
        mDownloadTask = null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart ");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart ");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause ");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }
}
