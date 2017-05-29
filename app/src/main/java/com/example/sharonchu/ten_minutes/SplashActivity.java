package com.example.sharonchu.ten_minutes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.Random;
import android.os.Handler;
import android.widget.Toast;

import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity {

    private ImageView startImage;
    private int[] images= {R.drawable.start0,R.drawable.start1,R.drawable.start2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        displayImages();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                SharedPreferences setting = getSharedPreferences(userInfo, 0);
                Boolean user_first = setting.getBoolean("FIRST",true);
                if(user_first){//第一次
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);

    }

    private void displayImages(){
        startImage = (ImageView) findViewById(R.id.startImage);
        Random random = new Random();
        int index = random.nextInt(images.length);
        startImage.setImageResource(images[index]);
        //进行缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        //动画播放完成后保持形状
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //finish();
                //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startImage.startAnimation(scaleAnimation);
    }



}
