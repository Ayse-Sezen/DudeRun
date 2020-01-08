package com.example.glitch;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable{

    private Thread thread;
    private boolean isPlaying, isGameOver = false, changeLevel = false, reset = false, gameWon = false;
    private Background background1, background2;
    private int screenX;
    private int screenY;
    private int extralife;
    private int score = 0, backgroundRotationCtr = 0, lives = 3, backgroundSpeed = 10,legSpeed = 30, owletSpeed = 30, monsterSpeed = 30;
    private DudeRun run;
    private Paint paint;
    private Random random;
    private SharedPreferences preferences, preferences2;
    private List<Bullet> bullets;
    private Monster[] monsters;
    private Owlet[] owlets;
    private ChickenLeg[] legs;
    public static float screenRatioX, screenRatioY;
    private GameActivity activity;


    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;

        preferences = activity.getSharedPreferences("game", Context.MODE_PRIVATE);
        preferences2 = activity.getSharedPreferences("game2", Context.MODE_PRIVATE);

        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 1440f / screenX;
        screenRatioY = 2560f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());


        background2.x = screenX;

        run = new DudeRun(this, screenY, getResources());

        bullets = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);


        monsters = new Monster[2];

        for(int i = 0; i < 2; i++){
            Monster monster = new Monster(getResources());
            monsters[i] = monster;
        }

        owlets = new Owlet[2];

        for(int i = 0; i < 2; i++){
            Owlet owlet = new Owlet(getResources());
            owlets[i] = owlet;
        }

        legs = new ChickenLeg[2];

        for(int i = 0; i < 2; i++){
            ChickenLeg leg = new ChickenLeg(getResources());
            legs[i] = leg;
        }

        random = new Random();
    }


    @Override
    public void run(){
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    private void update(){
        if(!changeLevel) { // If we reached the next level, we indicate that by stopping the moving background and letting blue char run off screen
            moveBackground();
        }

        if(reset){
            changeLevels();
        }

        if(!reset) {
            moveDude();
        }

       List<Bullet> trash = new ArrayList<>();

        for(Bullet bullet : bullets){
            if(bullet.x > screenX){
                trash.add(bullet);
            }

            bullet.x += 50; //+ screenRatioX;

            for(Monster monster: monsters){
                if(Rect.intersects(monster.getCollisionShape(), bullet.getCollisionShape())){
                    score++;
                    monster.x = -500;
                    bullet.x  = screenX + 500;
                    monster.wasShot = true;
                }
            }

            for(Owlet owlet: owlets){
                if(Rect.intersects(owlet.getCollisionShape(), bullet.getCollisionShape())){
                    score++;
                    owlet.x = -500;
                    bullet.x  = screenX + 500;
                    owlet.wasShot = true;
                }
            }

            for(ChickenLeg leg: legs){
                if(Rect.intersects(leg.getCollisionShape(), bullet.getCollisionShape())){
                    score--;
                    leg.x = -500;
                    bullet.x  = screenX + 500;
                    leg.wasShot = true;
                }
            }
        }

        for(Bullet bullet : trash){
            bullets.remove(bullet);
        }

        moveMonster();
        moveOwlets();
        moveLegs();


    }


    private void moveBackground(){
            background1.x -= backgroundSpeed;// * screenRatioX;
            background2.x -= backgroundSpeed;// * screenRatioX;



                if (background1.x + background1.background.getWidth() < 0) {
                    background1.x = screenX;
                    backgroundRotationCtr++;

                    if(backgroundRotationCtr >= 8){
                        changeLevel = true;
                    }
                }

                if (background2.x + background2.background.getWidth() < 0) {
                    background2.x = screenX;
                    backgroundRotationCtr++;

                    if(backgroundRotationCtr >= 8){
                        changeLevel = true;
                    }
                }



    }

    private void moveDude(){
        if(!changeLevel) {
            if (run.isGoingUp) {
                run.y -= 30;// * screenRatioY;
            } else {
                run.y += 30;// * screenRatioY;
            }

            if (run.y < 300) {
                run.y = 300;
            }

            if (run.y > screenY - run.height - 140) {
                run.y = screenY - run.height - 140;
            }
        }
        else{
            run.x += 20;// * screenRatioX;

            if(run.x > screenX) {// 1920 screenX
                // change background to white
                // set reset bool to true
                reset = true;

                if(background1.levelCtr == 5){
                    gameWon = true;
                    isGameOver = false;
                }
            }
        }
    }

private void moveOwlets(){
    for(Owlet owlet: owlets){
        owlet.x -= owlet.speed;

        if(owlet.x + owlet.width < 0){

            int bound = (int) (owletSpeed);// * screenRatioX);
            owlet.speed = random.nextInt(bound);

            if(backgroundRotationCtr < 2) {
                owletSpeed = 30;
                if (owlet.speed < 20){// * screenRatioX) {
                    owlet.speed = (int) (20);// * screenRatioX);
                }
            }

            if(backgroundRotationCtr == 2){
                    //owletSpeed += 10;
                owletSpeed += 3;

                if (owlet.speed < 20){// * screenRatioX) {
                    owlet.speed = (int) (30);// * screenRatioX);
                }

            }

            if(backgroundRotationCtr == 4){
                //owletSpeed += 10;
                owletSpeed += 3;

                if (owlet.speed < 30){// * screenRatioX) {
                    owlet.speed = (int) (40);// * screenRatioX);
                }

            }

            if(backgroundRotationCtr == 6){
                //owletSpeed += 10;
                owletSpeed += 2;

                if (owlet.speed < 40){// * screenRatioX) {
                    owlet.speed = (int) (50);// * screenRatioX);
                }

            }

            if(owlet.ranIntoDude){
                owlet.ranIntoDude = false; // reset this flag to false every time it respawns
            }

            if(!changeLevel) {
                owlet.x = screenX;
                owlet.y = random.nextInt(screenY - owlet.height - 140);

                if (owlet.y < 300) {
                    owlet.y = 300;
                }


            }

            owlet.wasShot = false;
        }

        if(Rect.intersects(owlet.getCollisionShape(), run.getCollisionShape())){
            if(!owlet.ranIntoDude) {
                owlet.ranIntoDude = true;
                lives--;
            }

            if(lives == 0){
                isGameOver = true;
                gameWon = false;
                return;
            }
            return;
        }
    }
}

    private void moveLegs(){
        for(ChickenLeg leg: legs){
            leg.x -= leg.speed;

            if(leg.x + leg.width < 0){

                int bound = (int) (legSpeed);// * screenRatioX);
                leg.speed = random.nextInt(bound);

                if(backgroundRotationCtr < 2) {
                    legSpeed = 30;
                    if (leg.speed < 20){// * screenRatioX) {
                        leg.speed = (int) (20);// * screenRatioX);
                    }
                }

                if(backgroundRotationCtr == 2){
                    //owletSpeed += 10;
                    legSpeed += 3;

                    if (leg.speed < 20){// * screenRatioX) {
                        leg.speed = (int) (30);// * screenRatioX);
                    }

                }

                if(backgroundRotationCtr == 4){
                    //owletSpeed += 10;
                    owletSpeed += 3;

                    if (leg.speed < 30){// * screenRatioX) {
                        leg.speed = (int) (40);// * screenRatioX);
                    }

                }

                if(backgroundRotationCtr == 6){
                    //owletSpeed += 10;
                    owletSpeed += 2;

                    if (leg.speed < 40){// * screenRatioX) {
                        leg.speed = (int) (50);// * screenRatioX);
                    }

                }

                if(leg.ranIntoDude){
                    leg.ranIntoDude = false; // reset this flag to false every time it respawns
                }

                if(!changeLevel) {
                    leg.x = screenX;
                    leg.y = random.nextInt(screenY - leg.height - 140);

                    if (leg.y < 300) {
                        leg.y = 300;
                    }


                }

                leg.wasShot = false;
            }

            if(Rect.intersects(leg.getCollisionShape(), run.getCollisionShape())){
                if(!leg.ranIntoDude) {
                    leg.ranIntoDude = true;
                    extralife++;
                    if(extralife % 5 == 0){
                        lives++;
                    }
                }

                if(lives == 0){
                    isGameOver = true;
                    gameWon = false;
                    return;
                }
                return;
            }
        }
    }


private void moveMonster(){
    for(Monster monster: monsters){
        monster.x -= monster.speed;

        if(monster.x + monster.width < 0){

            int bound = (int) (monsterSpeed);// * screenRatioX);
            monster.speed = random.nextInt(bound);

            if(backgroundRotationCtr < 2) {
                monsterSpeed = 30;
                if (monster.speed < 20){// * screenRatioX) {
                    monster.speed = (int) (20);// * screenRatioX);
                }
            }

            if(backgroundRotationCtr == 2){

                monsterSpeed += 3;

                if (monster.speed < 30){// * screenRatioX) {
                    monster.speed = (int) (30);// * screenRatioX);
                }

            }

            if(backgroundRotationCtr == 4){

                monsterSpeed += 3;

                if (monster.speed < 40){// * screenRatioX) {
                    monster.speed = (int) (40);// * screenRatioX);
                }

            }

            if(backgroundRotationCtr == 6){

                monsterSpeed += 2;

                if (monster.speed < 50){// * screenRatioX) {
                    monster.speed = (int) (50);// * screenRatioX);
                }

            }

            /*if(monster.speed < 10 * screenRatioX){
                monster.speed = (int) (10 * screenRatioX);
            }*/

            if(monster.ranIntoDude){
                monster.ranIntoDude = false;
            }

            if(!changeLevel) {
                monster.x = screenX;
                monster.y = random.nextInt(screenY - monster.height - 140);

                if (monster.y < 300) {
                    monster.y = 300;
                }

            }
            monster.wasShot = false;
        }

        if(Rect.intersects(monster.getCollisionShape(), run.getCollisionShape())){
            if(!monster.ranIntoDude) {
                monster.ranIntoDude = true;
                lives--;
            }

            if(lives == 0) {
                isGameOver = true;
                return;
            }

            return;
        }
    }
}


private void changeLevels(){
    // clear screen of all little guys
    for(Monster monster: monsters){
        monster.x = screenX;
        monster.y = random.nextInt(screenY - monster.height - 140);

        if(monster.y < 300){
            monster.y = 300;
        }

        monsterSpeed = 30;
        int bound = (int) (monsterSpeed);// * screenRatioX);
        monster.speed = random.nextInt(bound);
    }

    for(Owlet owlet: owlets){
        owlet.x = screenX;
        owlet.y = random.nextInt(screenY - owlet.height - 140);

        if (owlet.y < 300) {
            owlet.y = 300;
        }

        owletSpeed = 30;
        int bound = (int) (owletSpeed);// * screenRatioX);
        owlet.speed = random.nextInt(bound);

    }

    for(ChickenLeg leg: legs){
        leg.x = screenX;
        leg.y = random.nextInt(screenY - leg.height - 140);

        if (leg.y < 300) {
            leg.y = 300;
        }

        legSpeed = 30;
        int bound = (int) (legSpeed);// * screenRatioX);
        leg.speed = random.nextInt(bound);

    }
    // change the background
    background1.changeBackground(screenX, screenY, getResources());
    background2.changeBackground(screenX, screenY, getResources());

    run.setPosition(screenY);

    // reset position of blue guy

    // reset background speed
    backgroundSpeed = 10;

    changeLevel = false;
    reset = false;
    backgroundRotationCtr = 0;

}


    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for(Monster monster: monsters){
                canvas.drawBitmap(monster.getMonster(), monster.x, monster.y, paint);
            }

            for(Owlet owlet: owlets){
                canvas.drawBitmap(owlet.getOwlet(), owlet.x, owlet.y, paint);
            }
            for(ChickenLeg leg: legs){
                canvas.drawBitmap(leg.getChickenLeg(), leg.x, leg.y, paint);
            }

            //canvas.drawText(score + "", screenX / 2f, 164, paint);
            canvas.drawText("Score: " + score, screenX / 2f, 164, paint);
            canvas.drawText("Lives: " + lives, screenX / 8f, 164, paint);

            if(isGameOver){
                isPlaying = false;
                canvas.drawBitmap(run.getDead(), run.x, run.y, paint);
                saveIfHighScore();
                //waitBeforeExiting();
                gameOver();
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }

            if(gameWon){
                isPlaying = false;
                saveIfHighScore();
                //waitBeforeExiting();
                gameWon();
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }


            canvas.drawBitmap(run.getRun(), run.x, run.y, paint);



            for(Bullet bullet: bullets){
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            }


            getHolder().unlockCanvasAndPost(canvas);

        }
    }

   /*private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }*/

   private void gameOver(){
       try {
           Thread.sleep(3000);
           activity.startActivity(new Intent(activity, GameOverActivity.class));
           activity.finish();
       } catch (InterruptedException e){
           e.printStackTrace();
       }
   }

  private void gameWon(){
       try {
           Thread.sleep(3000);
           activity.startActivity(new Intent(activity, GameWonActivity.class));
           activity.finish();
       } catch (InterruptedException e){
           e.printStackTrace();
       }
   }


    private void saveIfHighScore() {
        if(preferences.getInt("highscore", 0) < score){ //////////////////////////////////////////////////////
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }

        SharedPreferences.Editor editor2 = preferences2.edit();
        editor2.putInt("yourscore", score);
        editor2.apply();

    }

    private void sleep(){
        try {
            Thread.sleep(17);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX() < screenX / 2){
                    run.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                run.isGoingUp = false;
                //if(event.getX() > screenX / 2) {
                  //  run.toShoot++;
                //}
                run.toShoot++;
                break;
        }

        return true;
    }

    public void newBullet() {
        Bullet bullet = new Bullet(getResources());
        bullet.x = run.x + run.width;
        bullet.y = run.y + (run.height / 2);
        bullets.add(bullet);
    }


}


