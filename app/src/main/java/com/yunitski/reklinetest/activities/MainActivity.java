package com.yunitski.reklinetest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.yunitski.reklinetest.MyNotificationOpenedHandler;
import com.yunitski.reklinetest.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(MyNotificationOpenedHandler.FILE, Context.MODE_PRIVATE);
        String s = sharedPreferences.getString(MyNotificationOpenedHandler.KEY, "nope");

        if (s.equals("notification_clicked")){
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        } else {
            startActivity(new Intent(MainActivity.this, MainActivity3.class));
        }
        MainActivity.this.finish();
//        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }
}