package com.yunitski.reklinetest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.onesignal.OneSignal;
import com.yunitski.reklinetest.MyNotificationOpenedHandler;
import com.yunitski.reklinetest.R;

public class LaunchActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private static final String ONESIGNAL_APP_ID = "f6836300-c5c7-4b24-81fe-4f89d9fe9ddc"; // id onesignal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        progressBar = findViewById(R.id.progressBarLauncher);

        DelayTask delayTask = new DelayTask(); // для работы progressbar используем асинхронный поток
        delayTask.execute();

    }

    class DelayTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(ProgressBar.VISIBLE); // показывает progressbar
        }
        @Override
        protected String doInBackground(Void... voids) { // выполняем всю работу при запуске приложения
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

            // инициализация OneSignal
            // код взят из официальной документации
            OneSignal.initWithContext(getApplicationContext());
            OneSignal.setAppId(ONESIGNAL_APP_ID);
            OneSignal.setNotificationOpenedHandler(new MyNotificationOpenedHandler(getApplicationContext()));
            SharedPreferences sharedPreferences = getSharedPreferences(MyNotificationOpenedHandler.FILE, Context.MODE_PRIVATE);


            String activityStarted = sharedPreferences.getString(MyNotificationOpenedHandler.KEY, "nope");
            // проверяем было ли запущено приложение через пуш
            if (activityStarted.equals(MyNotificationOpenedHandler.NOTIFICATION_CLICKED)){
                startActivity(new Intent(LaunchActivity.this, WebViewActivity.class));
            } else {
                startActivity(new Intent(LaunchActivity.this, GameActivity.class));
            }
            LaunchActivity.this.finish();

            return null;
        }
    }
}