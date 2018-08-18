package com.example.pc_peyman.threes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    public Color color;
    private Button reset;
    private TextView bestValue;
    private GameView gameView;
    private Button btnNext;
    private AlphaAnimation animation1;
    private Animation a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        G.mainActivit=this;
        txt =  (TextView)findViewById(R.id.valueScore);
        bestValue = (TextView)findViewById(R.id.bestValue);
        reset = (Button)findViewById(R.id.button2);
        gameView = (GameView)findViewById(R.id.gameView);
        btnNext = (Button)findViewById(R.id.next);
        a = AnimationUtils.loadAnimation(this,R.anim.slide_left);


        animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(500);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.resetGame();
            }
        });
        UpdateScore();
        UpdateRanInt();
    }

    @Override
    protected void onPause() {
        super.onPause();
        G.performance.edit().putInt("Best", G.best).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        G.performance.edit().putInt("Best", G.best).commit();
    }

    public void UpdateScore(){
        txt.setText(""+G.score);
        bestValue.setText(""+G.best);
    }
    public void UpdateRanInt(){
        if(G.Next()==1){
            btnNext.setTranslationX(3f);
            btnNext.setTranslationY(3f);
            btnNext.startAnimation(animation1);
            btnNext.setBackground(this.getResources().getDrawable(R.drawable.btn));
        }
        else if(G.Next()==2){
            btnNext.setTranslationX(3f);
            btnNext.setTranslationY(3f);
            btnNext.startAnimation(animation1);
            btnNext.setBackground(this.getResources().getDrawable(R.drawable.btn2));
        }
        else{
            btnNext.setTranslationX(3f);
            btnNext.setTranslationY(3f);
            btnNext.startAnimation(animation1);
            btnNext.setBackground(this.getResources().getDrawable(R.drawable.btn3));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            G.performance.edit().putInt("Best", G.best).commit();
            System.exit(0);
        }

        return super.onKeyDown(keyCode, event);
    }
}
