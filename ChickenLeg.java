package com.example.glitch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ChickenLeg {
    public int speed = 20;
    public boolean wasShot = true, ranIntoDude = false;
    int x = 0, y, width, height, monsterCounter = 1;

    Bitmap leg1, leg2, leg3, leg4, leg5;

    ChickenLeg (Resources res){
        leg1 = BitmapFactory.decodeResource(res, R.drawable.aleg);
        leg2 = BitmapFactory.decodeResource(res, R.drawable.bleg);
        leg3 = BitmapFactory.decodeResource(res, R.drawable.cleg);
        leg4 = BitmapFactory.decodeResource(res, R.drawable.dleg);
        leg5 = BitmapFactory.decodeResource(res, R.drawable.eleg);

        width = leg1.getWidth();
        height = leg1.getHeight();

        leg1 = Bitmap.createScaledBitmap(leg1, width, height, false);
        leg2 = Bitmap.createScaledBitmap(leg2, width, height, false);
        leg3 = Bitmap.createScaledBitmap(leg3, width, height, false);
        leg4 = Bitmap.createScaledBitmap(leg4, width, height, false);
        leg5 = Bitmap.createScaledBitmap(leg5, width, height, false);

        y = -height;

    }
    Bitmap getChickenLeg(){
        if(monsterCounter == 1){
            monsterCounter++;
            return leg1;
        }

        if(monsterCounter == 2){
            monsterCounter++;
            return leg2;
        }

        if(monsterCounter == 3){
            monsterCounter++;
            return leg3;
        }

        if(monsterCounter == 4){
            monsterCounter++;
            return leg4;
        }

        monsterCounter = 1;
        return leg5;
    }

    Rect getCollisionShape(){
        return new Rect(x, y, x + width, y + height);
    }


}
