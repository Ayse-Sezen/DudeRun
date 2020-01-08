package com.example.glitch;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.glitch.GameView.screenRatioX;

public class DudeRun {
    public boolean isGoingUp = false;
    public int toShoot = 0;
    int x, y, width, height, runCounter = 0, shootCounter = 1;
    Bitmap run1, run2, run3, run4, run5, run6, shoot1, shoot2, shoot3, shoot4, shoot5, shoot6, dead;
    private GameView gameView;

    DudeRun(GameView gameView, int screenY, Resources res){

        this.gameView = gameView;

        run1 = BitmapFactory.decodeResource(res, R.drawable.duderun1);
        run2 = BitmapFactory.decodeResource(res, R.drawable.duderun2);
        run3 = BitmapFactory.decodeResource(res, R.drawable.duderun3);
        run4 = BitmapFactory.decodeResource(res, R.drawable.duderun4);
        run5 = BitmapFactory.decodeResource(res, R.drawable.duderun5);
        run6 = BitmapFactory.decodeResource(res, R.drawable.duderun6);


        width = run1.getWidth();
        height = run1.getHeight();

        //width /= 4;
        //height /= 4;

        //width *= (int) screenRatioX;
        //height *= (int) screenRatioY;

        //width *= (int) (width * screenRatioX);
        //height *= (int) (height * screenRatioY);

        run1 = Bitmap.createScaledBitmap(run1, width, height, false);
        run2 = Bitmap.createScaledBitmap(run2, width, height, false);

        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);
        shoot6 = BitmapFactory.decodeResource(res, R.drawable.shoot6);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);
        shoot6 = Bitmap.createScaledBitmap(shoot6, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dudemonsterdead4);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        setPosition(screenY);
        /*y = screenY / 2;
        x = (int) (64 * screenRatioX);*/
    }

    void setPosition(int screenY){
        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    Bitmap getRun() {
        if (toShoot != 0) {
        if (shootCounter == 1) {
            shootCounter++;
            return shoot1;
        }

        if (shootCounter == 2) {
            shootCounter++;
            return shoot2;
        }

        if (shootCounter == 3) {
            shootCounter++;
            return shoot3;
        }

        if (shootCounter == 4) {
            shootCounter++;
            return shoot4;
        }

        if (shootCounter == 5) {
            shootCounter++;
            return shoot5;
        }

        shootCounter = 1;
        toShoot--;
        gameView.newBullet();
        return shoot6;
    }
        if(runCounter == 0){
            runCounter++;
            return run1;
        }

        if(runCounter == 1){
            runCounter++;
            return run2;
        }

        if(runCounter == 2){
            runCounter++;
            return run3;
        }

        if(runCounter == 3){
            runCounter++;
            return run4;
        }

        if(runCounter == 4){
            runCounter++;
            return run5;
        }

        runCounter = 0;
        return run6;
    }


    Rect getCollisionShape(){
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getDead(){
        return dead;
    }

}




