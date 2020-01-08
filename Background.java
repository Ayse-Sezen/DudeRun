package com.example.glitch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Background {
    int x = 0, y = 0, levelCtr = 1;
    Bitmap background;

    Background(int screenX, int screenY, Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.countrybackground1);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }


    void changeBackground(int screenX, int screenY, Resources res){
        if(levelCtr == 1){
            levelCtr++;
            background = BitmapFactory.decodeResource(res, R.drawable.clouds_background);
            background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
            return;
        }

        if(levelCtr == 2){
            levelCtr++;
            background = BitmapFactory.decodeResource(res, R.drawable.sea_and_stars_background);
            background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
            return;
        }

        if(levelCtr == 3){
            levelCtr++;
            background = BitmapFactory.decodeResource(res, R.drawable.tracks_background);
            background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
            return;
        }

        if(levelCtr == 4){
            levelCtr++;
            background = BitmapFactory.decodeResource(res, R.drawable.forest_stars_greenery_background);
            background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
            return;
        }

    }



}
