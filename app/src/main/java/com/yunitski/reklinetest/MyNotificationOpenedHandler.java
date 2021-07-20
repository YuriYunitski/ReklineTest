package com.yunitski.reklinetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;
import com.yunitski.reklinetest.activities.LaunchActivity;

public class MyNotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler{

    SharedPreferences sharedPreferences;

    // константы для sharedpreferences
    public static final String FILE = "file";
    public static final String KEY = "key";
    public static final String NOTIFICATION_CLICKED = "notification_clicked";

    private final Context context;

    public MyNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {

        OSNotificationAction.ActionType actionType = result.getAction().getType();
        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());


        startApp(); // Запуск активити при нажатии на уведомление
        setBehavior(); // Запоминаем что уведомление было нажато
    }

    private void startApp() {
        Intent intent = new Intent(context, LaunchActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }

    private void setBehavior(){
        sharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, NOTIFICATION_CLICKED);
        editor.apply();
    }
}