package com.yunitski.reklinetest.game;

import android.content.Context;

import com.yunitski.reklinetest.R;
import com.yunitski.reklinetest.activities.GameActivity;

public class Ship extends SpaceBody{

    public Ship(Context context) {
        bitmapId = R.drawable.ship; // определяем начальные параметры
        size = 3;
        x = 8;
        y = GameView.maxY - size - 1;
        speed = (float) 0.4;

        init(context); // инициализируем корабль
    }
    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(GameActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(GameActivity.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }

}
