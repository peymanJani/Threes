package com.example.pc_peyman.threes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by PC_peyman on 2/11/2017.
 */

public class GameView extends android.support.v7.widget.AppCompatImageView {

    boolean check_below;
    private int conter;
    private Paint textPaint;
    private int cell[][] = new int[4][4];
    private int width;
    private Paint paint;
    private int height;
    private static final int side_Left = 1;
    private static final int side_Right=2;
    private static final int side_Down=3;
    private static final int side_Up=4;
  public Animation a;




    public GameView(Context  context) {
        super(context);
        view();
      a = AnimationUtils.loadAnimation(context,R.anim.slide_left);
    }

    public GameView(Context  context , AttributeSet attributeSet) {
        super(context , attributeSet);
        view();
    }

    public GameView(Context  context , AttributeSet attributeSet , int defStyle) {
        super(context , attributeSet , defStyle);
        view();
    }

    public void view(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#9900c7a0"));


        textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(G.convertDpToPixel(40));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#000000"));

        //textPaint.setARGB(88,00,128,144);
        // resetGame();
        nextNum();
        nextNum();

    }

    private void go_Down() {
        byte value=0;
        check_below=false;
        for (int j = 3; j >= 0; j--) {
            for (int i = 3; i >= 0; i--){
                if(i<3){
                    int k=i;
                   if( k<3 && cell[k+1][j]==0 ) {
                       if(cell[k][j]!=0) {
                           cell[k + 1][j] = cell[k][j];
                           cell[k][j] = 0;
                           k++;
                           check_below = true;
                       }
                    }
                   else if(cell[k+1][j]==1 && cell[k][j]==1){k--; }
                   else if(cell[k+1][j]==2 && cell[k][j]==2){k--; }
                   else if(cell[k+1][j]==1 && cell[k][j]==2){cell[k+1][j] += cell[k][j];power(k+1,j);cell[k][j] = 0; check_below=true;}
                   else if(cell[k+1][j]==2 && cell[k][j]==1){cell[k+1][j] += cell[k][j];power(k+1,j);cell[k][j] = 0; check_below=true;}

                   else if(k<3 && cell[k+1][j] == cell[k][j]){
                        cell[k+1][j] += cell[k][j];
                        power(k+1,j);
                        cell[k][j] = 0;
                       check_below=true;
                    }
                }

            }
        }
       // invalidate();
    }
    private void go_top() {
        byte value=0;
        check_below=false;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++){
                if(i>0){
                    int k=i;
                    if( k>0 && cell[k-1][j]==0 ) {
                        if(cell[k][j]!=0) {
                            cell[k - 1][j] = cell[k][j];
                            cell[k][j] = 0;
                            k--;
                            check_below = true;
                        }
                    }
                    else if(cell[k-1][j]==1 && cell[k][j]==1){k--; }
                    else if(cell[k-1][j]==2 && cell[k][j]==2){k--; }
                    else if(cell[k-1][j]==1 && cell[k][j]==2){cell[k-1][j] += cell[k][j];power(k-1,j);cell[k][j] = 0; check_below=true;}
                    else if(cell[k-1][j]==2 && cell[k][j]==1){cell[k-1][j] += cell[k][j];power(k-1,j);cell[k][j] = 0; check_below=true;}

                    else if(k>0 && cell[k-1][j] == cell[k][j]){
                        cell[k-1][j] += cell[k][j];
                        power(k-1,j);
                        cell[k][j] = 0;
                        check_below=true;
                    }
                }

            }
        }
        invalidate();
    }
    private void go_Right() {
        check_below=false;
        for (int i = 3; i >=0; i--) {
            for (int j = 3; j>=0; j--){
                if(j<3){
                    int k=j;
                    if( k<3 && cell[i][k+1]==0 ) {
                        if(cell[i][k]!=0){
                        cell[i][k+1] = cell[i][k];
                        cell[i][k] = 0;
                        k++;
                        check_below=true;
                        }
                    }
                    else if(cell[i][k+1]==1 && cell[i][k]==1){k--; }
                    else if(cell[i][k+1]==2 && cell[i][k]==2){k--; }
                    else if(cell[i][k+1]==1 && cell[i][k]==2){cell[i][k+1] += cell[i][k];power(i,k+1);cell[i][k] = 0; check_below=true;}
                    else if(cell[i][k+1]==2 && cell[i][k]==1){cell[i][k+1] += cell[i][k];power(i,k+1);cell[i][k] = 0; check_below=true;}

                    else if((k<3 && cell[i][k+1] == cell[i][k]) ){
                        cell[i][k+1] += cell[i][k];
                        power(i,k+1);
                        cell[i][k] = 0;
                        check_below=true;
                    }
                }
            }
        }
        invalidate();
    }
    private void go_left() {
        byte value=0;
        check_below=false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                if(j>0){
                    int k=j;
                    if( k>0 && cell[i][k-1]==0 ) {
                        if(cell[i][k]!=0){
                            check_below=true;
                            cell[i][k-1] = cell[i][k];
                            cell[i][k] = 0;
                            k--;
                        }
                    }
                    else if(cell[i][k-1]==1 && cell[i][k]==1){k--;}
                    else if(cell[i][k-1]==2 && cell[i][k]==2){k--;}
                    else if(cell[i][k-1]==1 && cell[i][k]==2){cell[i][k-1] += cell[i][k];power(i,k-1);cell[i][k] = 0; check_below=true; value=1;}
                    else if(cell[i][k-1]==2 && cell[i][k]==1){cell[i][k-1] += cell[i][k];power(i,k-1);cell[i][k] = 0; check_below=true; value=1;}

                    else if(k>0 && cell[i][k-1] == cell[i][k]){
                        cell[i][k-1] += cell[i][k];
                        power(i,k-1);
                        cell[i][k] = 0;
                        check_below=true;
                        value=1;
                    }
                }

            }
        }
        invalidate();
    }

    private void Check_Down(){
        check_below=false;
        for (int j = 3; j >= 0; j--) {
            for (int i = 3; i >= 0; i--){
                if(i<3){
                    int k=i;
                    if( k<3 && cell[k+1][j]==0 ) {
                        if(cell[k][j]!=0) { k++;  check_below = true;}}
                    else if(cell[k+1][j]==1 && cell[k][j]==1){k--; }
                    else if(cell[k+1][j]==2 && cell[k][j]==2){k--; }
                    else if(cell[k+1][j]==1 && cell[k][j]==2){ check_below=true;}
                    else if(cell[k+1][j]==2 && cell[k][j]==1){check_below=true;}
                    else if(k<3 && cell[k+1][j] == cell[k][j]){check_below=true;}
                }

            }
        }
    }
    private void Check_Left() {
        check_below = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j > 0) {
                    int k = j;
                    if (k > 0 && cell[i][k - 1] == 0) {
                        if (cell[i][k] != 0) {
                            k--;
                            check_below = true;
                        }
                    } else if (cell[i][k - 1] == 1 && cell[i][k] == 1) {
                        k--;
                    } else if (cell[i][k - 1] == 2 && cell[i][k] == 2) {
                        k--;
                    } else if (cell[i][k - 1] == 1 && cell[i][k] == 2) {
                        check_below = true;
                    } else if (cell[i][k - 1] == 2 && cell[i][k] == 1) {
                        check_below = true;
                    } else if (k > 0 && cell[i][k - 1] == cell[i][k]) {
                        check_below = true;
                    }
                }

            }
        }
    }
    private void Check_Right(){
        check_below=false;
        for (int i = 3; i >=0; i--) {
            for (int j = 3; j>=0; j--){
                if(j<3){
                    int k=j;
                    if( k<3 && cell[i][k+1]==0 ) {
                        if(cell[i][k]!=0){
                            k++;
                            check_below=true;
                        }
                    }
                    else if(cell[i][k+1]==1 && cell[i][k]==1){k--; }
                    else if(cell[i][k+1]==2 && cell[i][k]==2){k--; }
                    else if(cell[i][k+1]==1 && cell[i][k]==2){check_below=true;}
                    else if(cell[i][k+1]==2 && cell[i][k]==1){ check_below=true;}

                    else if((k<3 && cell[i][k+1] == cell[i][k]) ){
                        check_below=true;
                    }
                }
            }
        }
    }
    private void Check_Up(){
        check_below=false;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++){
                if(i>0){
                    int k=i;
                    if( k>0 && cell[k-1][j]==0 ) {
                        if(cell[k][j]!=0) {
                            k--;
                            check_below = true;
                        }
                    }
                    else if(cell[k-1][j]==1 && cell[k][j]==1){k--; }
                    else if(cell[k-1][j]==2 && cell[k][j]==2){k--; }
                    else if(cell[k-1][j]==1 && cell[k][j]==2){ check_below=true;}
                    else if(cell[k-1][j]==2 && cell[k][j]==1){ check_below=true;}
                    else if(k>0 && cell[k-1][j] == cell[k][j]){
                        check_below=true;
                    }
                }

            }
        }
    }

   private boolean Side(){

       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++){
               if (cell[i][j]==0) {
                   return true;
               }
           }
       }
        return false;
    }

    private void power(int i , int j){
        //cell[i][j]*=2;
        G.score += cell[i][j];
        G.updateScore();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width=getWidth();
        height = getHeight();

    }



    private int loseNum;
    private void nextNum(){

        boolean checkLose = true;
        for (int i = 0 ;i<4 ; i++) {
            for (int j = 0; j < 4; j++) {
                if(cell[i][j]==0 ){
                        checkLose=false;
                        break;
                }

            }
        }
        if(!check_below && checkLose){
            if(loseNum==0) {
                loseNum=1;
                Toast.makeText(getContext(), "You Lose!!!", Toast.LENGTH_LONG).show();
            }

        }else {
            while (true) {
                int ranX = (int) Math.floor(Math.random() * 4);
                int ranY = (int) Math.floor(Math.random() * 4);
                if (cell[ranX][ranY] == 0) {
                        cell[ranX][ranY] = G.next;
                    break;
                }
            }
            G.updateRanInt(G.next);
            invalidate();
        }
    }
    private void lose() {
        boolean checkLose = true;

        conter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cell[i][j] == 0) {
                    checkLose = false;
                    break;
                }

            }
        }
        Check_Left();
        Check_Right();
        Check_Up();
        Check_Down();
        if (!check_below && checkLose) {

            Toast.makeText(getContext(), "You Lose!!!", Toast.LENGTH_LONG).show();
        }
    }
    public void resetGame(){
        loseNum=0;
        G.score=0;
        G.updateScore();
        for (int i = 0 ;i<4 ; i++) {
            for (int j = 0; j < 4; j++) {
                cell[i][j] = 0;
            }
        }
        nextNum();
        nextNum();
    }

    private float lastDownX;
    private float lastDownY;
    private final static int MODE_LEFT=1;
    private final static int MODE_RIGHT=2;
    private final static int MODE_UP=3;
    private final static int MODE_DOWN=4;
    private boolean movePending;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastDownX = event.getRawX();
                lastDownY = event.getRawY();
                movePending = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if ( !movePending) {
                    return true;
                }

                int mode = 0;
                float diffX = lastDownX - event.getRawX();
                float diffY = lastDownY - event.getRawY();

                if (Math.abs(diffX) > 10 && Math.abs(diffX) > 2 * Math.abs(diffY)) {
                    if (diffX > 0) {
                        mode = MODE_LEFT;
                    } else {
                        mode = MODE_RIGHT;
                    }

                    movePending = false;
                }

                if (Math.abs(diffY) > 10 && Math.abs(diffY) > 2 * Math.abs(diffX)) {
                    if (diffY > 0) {
                        mode = MODE_UP;
                    } else {
                        mode = MODE_DOWN;
                    }

                    movePending = false;
                }

                switch (mode) {
                    case MODE_LEFT:
                        go_left();
                        if( check_below ){ nextNum();}
                        else {
                            lose();
                        }
                        break;

                    case MODE_RIGHT:
                        go_Right();
                        if (check_below) {
                            nextNum();
                        }
                        else {
                            lose();
                        }
                        break;

                    case MODE_UP:
                        go_top();

                        if (check_below) {
                            nextNum();
                        }else {
                            lose();
                        }
                        break;

                    case MODE_DOWN:
                        go_Down();
                        if (check_below) {
                            nextNum();
                        }else {
                            lose();
                        }
                        break;
                }
                break;
        }

        return true;
    }



    private String getNumberColor(int number) {
        switch (number) {
            case 0:
                return "#b6d9d9";
            case 1:
                return "#00BCD4";
            case 2:
                return "#ff6680";
            case 3:
                return "#ecfffe";
            case 4:
                return "#CE93D8";

            case 8:
                return "#F48FB1";

            case 16:
                return "#F06292";

            case 32:
                return "#E57373";

            case 64:
                return "#EF5350";

            case 128:
                return "#F44336";
        }

        return "#651FFF";
    }


    private AlphaAnimation animation1;
    @Override
    protected void onDraw(Canvas canvas) {
        animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(500);

        super.onDraw(canvas);
        int size = width/4;
        int hsize = height/4;
        float offset = getWidth() - 4.0f * size;
        int padding = 5;
        for(int i = 0 ; i<4 ; i++){
            for (int j = 0 ; j<4 ; j++){

                paint.setColor(Color.parseColor(getNumberColor((int)cell[i][j])));
                RectF rect = new RectF(j*size+padding, i*size+padding,(j+1)*size-padding , (i+1)*size-padding);
                canvas.drawRoundRect(rect, G.convertDpToPixel(8), G.convertDpToPixel(8), paint);

                float centerX = (j + 0.5f) * size + offset / 2.0f;
                float centerY = (i + 0.5f) * size;

                if(cell[i][j]!=0) {
                    // canvas.drawText(""+(int)cell[i][j],size*(j+0.5f),size*(i+0.6f),textPaint);
                    String textToDraw = new String("" + (int)cell[i][j]);
                    Rect bounds = new Rect();

                    textPaint.getTextBounds(textToDraw, 0, textToDraw.length(), bounds);
                    canvas.drawText(textToDraw, centerX, centerY + (bounds.bottom - bounds.top) / 2, textPaint);
                }
            }
        }
    }
}