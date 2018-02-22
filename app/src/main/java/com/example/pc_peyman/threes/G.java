package com.example.pc_peyman.threes;

/**
 * Created by PC_peyman on 2/14/2017.
 */

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.test.PerformanceTestCase;
import android.util.DisplayMetrics;

/**
 * Created by PC_peyman on 2/14/2017.
 */

public class G extends Application {

    public static MainActivity mainActivit;
    public static int score;
    public static int best;
    public static int next;
    public static SharedPreferences performance;
    private static Context context;
    public static int oneInt;
    public static int towInt;
    private static int conter;

    @Override
    public void onCreate() {
        super.onCreate();
        l=0;
        performance = PreferenceManager.getDefaultSharedPreferences(this);
        best = performance.getInt("Best",0);
        context=getApplicationContext();
    }
    public static void updateScore(){

        if(score>best){
            best=score;
        }
        if(mainActivit!=null){
            mainActivit.UpdateScore();
        }
    }



    public static float convertDpToPixel(float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
    public static int l;
     public static void updateRanInt(int x){
         int min=1; int max =3;
         if (l==0){
             oneInt = min +(int) Math.floor(Math.random() * max-min+1);
             l=1;

             next= oneInt;
         }else if(l==1){
             towInt=min +(int) Math.floor(Math.random() * max-min+1);
             l=0;
             next= towInt;
         }
         if(mainActivit!=null){
             mainActivit.UpdateRanInt();
         }
     }


    public static int Next(){
        if (l==0){
            return towInt;
        }
        else{
            return oneInt;
        }
    }
    public static float convertPixelsToDp(float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
