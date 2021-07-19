package com.yunitski.reklinetest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;
import com.yunitski.reklinetest.activities.MainActivity;
import com.yunitski.reklinetest.activities.MainActivity2;

public class MyNotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler{

    SharedPreferences sharedPreferences;

    public static final String FILE = "file";
    public static final String KEY = "key";

    private Application application;

    public MyNotificationOpenedHandler(Application application) {
        this.application = application;
    }

    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {

        OSNotificationAction.ActionType actionType = result.getAction().getType();
        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());

        // Launch new activity using Application object
        startApp();
        setBehavior();
    }



    private void startApp() {
        Intent intent = new Intent(application, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        application.startActivity(intent);

    }

    private void setBehavior(){
        sharedPreferences = application.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, "notification_clicked");
        editor.apply();
    }




}