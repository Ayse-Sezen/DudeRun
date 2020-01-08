package com.example.glitch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int tutdone = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tutdone == 0){
                    startActivity(new Intent(MainActivity.this, GameActivity.class));
                }

            }
        });

        findViewById(R.id.howtoplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tutdone == 0) {
                    tutdone = 1;
                    summonLeg("So you want to\n learn to play?\n I guess Ill have mercy\n and teach you", 15, 20);

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.tutorial_one,
                            (ViewGroup) findViewById(R.id.tut1_toast_container));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                    summonLeg("Also the monsters will\n hurt you, so give \n them a hug for me!",15,20);

                    LayoutInflater inflater2 = getLayoutInflater();
                    View layout2 = inflater2.inflate(R.layout.tutorial_two,
                            (ViewGroup) findViewById(R.id.tut2_toast_container));
                    Toast toast2 = new Toast(getApplicationContext());
                    toast2.setDuration(Toast.LENGTH_LONG);
                    toast2.setView(layout2);
                    toast2.show();

                    LayoutInflater inflater3 = getLayoutInflater();
                    View layout3 = inflater3.inflate(R.layout.tutorial_three,
                            (ViewGroup) findViewById(R.id.tut3_toast_container));
                    Toast toast3 = new Toast(getApplicationContext());
                    toast3.setDuration(Toast.LENGTH_LONG);
                    toast3.setView(layout3);
                    toast3.show();

                    summonLeg("Also note shooting\n me is a party foul, \n and you'll lose a point!",15,20);
                    summonLeg("Goodluck!",20,20);
                    tutdone = 2;
                }
                if(tutdone == 2){
                    tutdone = 0;
                }
            }
        });
    }

    void summonLeg(String msg, int size, int pad){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(msg);
        text.setPadding(pad,pad,pad,pad);
        text.setTextSize(size);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
