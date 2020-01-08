package com.example.glitch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Monster {
    public int speed = 20;
    public boolean wasShot = true, ranIntoDude = false;
    int x = 0, y, width, height, monsterCounter = 1;

    Bitmap monster1, monster2, monster3, monster4, monster5, monster6;

    Monster(Resources res){
        monster1 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun1);
        monster2 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun2);
        monster3 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun3);
        monster4 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun4);
        monster5 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun5);
        monster6 = BitmapFactory.decodeResource(res, R.drawable.pinkmonsterrun6);

        width = monster1.getWidth();
        height = monster1.getHeight();

        monster1 = Bitmap.createScaledBitmap(monster1, width, height, false);
        monster2 = Bitmap.createScaledBitmap(monster2, width, height, false);
        monster3 = Bitmap.createScaledBitmap(monster3, width, height, false);
        monster4 = Bitmap.createScaledBitmap(monster4, width, height, false);
        monster5 = Bitmap.createScaledBitmap(monster5, width, height, false);
        monster6 = Bitmap.createScaledBitmap(monster6, width, height, false);

        y = -height;
    }

    Bitmap getMonster(){
        if(monsterCounter == 1){
            monsterCounter++;
            return monster1;
        }

        if(monsterCounter == 2){
            monsterCounter++;
            return monster2;
        }

        if(monsterCounter == 3){
            monsterCounter++;
            return monster3;
        }

        if(monsterCounter == 4){
            monsterCounter++;
            return monster4;
        }

        if(monsterCounter == 5){
            monsterCounter++;
            return monster5;
        }

        monsterCounter = 1;
        return monster6;
    }

    Rect getCollisionShape(){
        return new Rect(x, y, x + width, y + height);
    }
}
