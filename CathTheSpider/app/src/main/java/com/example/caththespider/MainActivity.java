package com.example.caththespider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageViews;
    Handler handler;
    Runnable runnable;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score=0;
        timeText=findViewById(R.id.timeTextViewId);
        scoreText=findViewById(R.id.scoreTextViewId);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageViews=new ImageView[]{imageView,
                imageView2,
                imageView3,
                imageView4,
                imageView5,
                imageView6,
                imageView7,
                imageView8,
                imageView9};
        hideImage();
        for (int i = 0; i < imageViews.length ; i++) {
            imageViews[i].setVisibility(View.INVISIBLE);
        }
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time : "+l/1000);
            }

            @Override
            public void onFinish() {

                for (int i = 0; i < imageViews.length ; i++) {
                    imageViews[i].setVisibility(View.INVISIBLE);
                }
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Tekrar oynamak ister misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over ", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();



    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score : "+score);
    }
    public void hideImage(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < imageViews.length ; i++) {
                    imageViews[i].setVisibility(View.INVISIBLE);
                }
                Random random =new Random();
                int i=random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,750);
            }
        };
        handler.post(runnable);
    }
}