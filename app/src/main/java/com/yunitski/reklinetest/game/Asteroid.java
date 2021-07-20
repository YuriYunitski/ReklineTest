package com.yunitski.reklinetest.game;

import android.content.Context;

import com.yunitski.reklinetest.R;

import java.util.Random;

public class Asteroid extends SpaceBody{

    public Asteroid(Context context) {
        Random random = new Random();

        int ast = (int)(Math.random() * 3);
        switch (ast){ // выбиарем случайный астероид
            case 0:
                bitmapId = R.drawable.asteroid;
                break;
            case 1:
                bitmapId = R.drawable.asteroid1;
                break;
            case 2:
                bitmapId = R.drawable.asteroid2;
                break;
        }

        y = 0;

        int radius = 2; // радиус
        x = random.nextInt(GameView.maxX) - radius;
        int astSize = (int)(Math.random() * 3) + 1;
        size = radius * astSize; // получаем случайный размер астероида

        float minSpeed = (float) 0.1; // минимальная скорость

        float maxSpeed = (float) 0.5; // максимальная скорость
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() { // движение астероида
        y += speed;
    }

    public boolean isCollision(float shipX, float shipY, float shipSize) { // узнаем не столкнулся ли корабль с астероидом
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }
}
