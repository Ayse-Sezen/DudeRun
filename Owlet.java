package com.example.glitch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Owlet {
    public int speed = 20;
    public boolean wasShot = true, ranIntoDude = false;
    int x = 0, y, width, height, owletCounter = 1;

    Bitmap owlet1, owlet2, owlet3, owlet4, owlet5, owlet6;

    Owlet(Resources res){
        owlet1 = BitmapFactory.decodeResource(res, R.drawable.owletrun1);
        owlet2 = BitmapFactory.decodeResource(res, R.drawable.owletrun2);
        owlet3 = BitmapFactory.decodeResource(res, R.drawable.owletrun3);
        owlet4 = BitmapFactory.decodeResource(res, R.drawable.owletrun4);
        owlet5 = BitmapFactory.decodeResource(res, R.drawable.owletrun5);
        owlet6 = BitmapFactory.decodeResource(res, R.drawable.owletrun6);

        width = owlet1.getWidth();
        height = owlet1.getHeight();

        owlet1 = Bitmap.createScaledBitmap(owlet1, width, height, false);
        owlet2 = Bitmap.createScaledBitmap(owlet2, width, height, false);
        owlet3 = Bitmap.createScaledBitmap(owlet3, width, height, false);
        owlet4 = Bitmap.createScaledBitmap(owlet4, width, height, false);
        owlet5 = Bitmap.createScaledBitmap(owlet5, width, height, false);
        owlet6 = Bitmap.createScaledBitmap(owlet6, width, height, false);

        y = -height;
    }

    Bitmap getOwlet(){
        if(owletCounter == 1){
            owletCounter++;
            return owlet1;
        }

        if(owletCounter == 2){
            owletCounter++;
            return owlet2;
        }

        if(owletCounter == 3){
            owletCounter++;
            return owlet3;
        }

        if(owletCounter == 4){
            owletCounter++;
            return owlet4;
        }

        if(owletCounter == 5){
            owletCounter++;
            return owlet5;
        }

        owletCounter = 1;
        return owlet6;
    }

    Rect getCollisionShape(){
        return new Rect(x, y, x + width, y + height);
    }
}
