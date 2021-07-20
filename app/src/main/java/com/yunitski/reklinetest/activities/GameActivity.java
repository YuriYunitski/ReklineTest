package com.yunitski.reklinetest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yunitski.reklinetest.R;
import com.yunitski.reklinetest.game.GameView;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener{

    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка

    GameView gameView;
    LinearLayout gameLayout;

    ImageButton leftButton;
    ImageButton rightButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = new GameView(this); // создаём gameView

        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.addView(gameView);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);

        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();// определяем какая кнопка
        if (id == R.id.leftButton) {
            switch (event.getAction()) { // определяем нажата или отпущена
                case MotionEvent.ACTION_DOWN:
                    isLeftPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isLeftPressed = false;
                    break;
            }
        } else if (id == R.id.rightButton) {
            switch (event.getAction()) { // определяем нажата или отпущена
                case MotionEvent.ACTION_DOWN:
                    isRightPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isRightPressed = false;
                    break;
            }
        }
        return true;
    }

}