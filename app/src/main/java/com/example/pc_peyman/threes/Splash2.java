package com.example.pc_peyman.threes;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Splash2 extends Activity {

    private int screenWidth;
    private int screenHeight;
    private View myView3;
    private Typeface titrFont;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash2);

      titrFont = Typeface.createFromAsset(getAssets(), "fonts/hel.otf");

        Animation anim_back = AnimationUtils.loadAnimation(this, R.anim.layer_splash);
//        ImageView image = (ImageView)findViewById(R.id.imageView);
        TextView text = (TextView) findViewById(R.id.textView);
        LinearLayout layer_splash = (LinearLayout) findViewById(R.id.layer_splash);
        text.setTypeface(titrFont);
        layer_splash.startAnimation(anim_back);



        final Intent intent= new Intent(this,MainActivity.class);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                }super.run();
            }
        };
        thread.start();




    }//end oncreate

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        if(false){
            super.onBackPressed();
        }

    }
}
