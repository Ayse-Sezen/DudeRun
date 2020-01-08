package com.example.glitch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_over);

        TextView highScore = findViewById(R.id.highScoreText);
        SharedPreferences preferences = getSharedPreferences("game", MODE_PRIVATE);
        highScore.setText("High Score: " + preferences.getInt("highscore", 0));

        TextView yourScore = findViewById(R.id.yourScoreText);
        SharedPreferences preferences2 = getSharedPreferences("game2", MODE_PRIVATE);
        yourScore.setText("Your Score: " + preferences2.getInt("yourscore", 0));


        button = (Button) findViewById(R.id.Button); /////////////////////////// this is where you'd link this back to main

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
