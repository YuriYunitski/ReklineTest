package com.yunitski.reklinetest.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    public static int maxX = 18; // размер по горизонтали
    public static int maxY = 30; // размер по вертикали
    // инициализация размеров на surface
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private final Paint paint;
    private final SurfaceHolder surfaceHolder;
    private ArrayList<Asteroid> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private int currentTime = 0;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }
    private void update() {
        if(!firstTime) {
            ship.update();
            for (Asteroid asteroid : asteroids) {
                asteroid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                ship = new Ship(getContext()); // добавляем корабль
            }

            Canvas canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            ship.drawBody(paint, canvas); // рисуем корабль

            for(Asteroid asteroid: asteroids){ // рисуем астероиды
                asteroid.drawBody(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){ // перебираем все астероиды и проверяем не касается ли один из них корабля
        for (Asteroid asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){
                gameRunning = false; // останавливаем игру
                try {
                    Thread.sleep(1000);
                    restart(); // перезапускаем через секунду
                    run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        int ASTEROID_INTERVAL = 50;
        if(currentTime >= ASTEROID_INTERVAL){
            Asteroid asteroid = new Asteroid(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }

    private void restart(){ // перезапуск при столкновении
        firstTime = true;
        gameRunning = true;
        currentTime = 0;
        asteroids = new ArrayList<>();
    }

}
