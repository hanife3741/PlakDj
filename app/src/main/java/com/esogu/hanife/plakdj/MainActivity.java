package com.esogu.hanife.plakdj;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {
    SeekBar music;
    ProgressBar disc;
    Chronometer kronometre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = (SeekBar) findViewById(R.id.seekBar);
        disc = (ProgressBar) findViewById(R.id.progressBar);
        kronometre = (Chronometer) findViewById(R.id.chronometer);
       final MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.kalimba);
        disc.setOnTouchListener(new View.OnTouchListener() {

            private float currX;
            private float currY;

            public boolean onTouch(View v, MotionEvent event) {
                float x1 = 0, x2, y1 = 0, y2, dx, dy , oldx =0,oldy=0;
                String direction;
                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                     // finger touches the screen
                        oldx = event.getX();
                        oldy = event.getY();
                        player.start();
                        kronometre.start();

                    case MotionEvent.ACTION_MOVE:
                        // finger moves on the screen
                        x2 = event.getX();
                        y2 = event.getY();
                        dx = x2-x1;
                        dy = y2-y1;
                        if(Math.abs(dx) > Math.abs(dy)) {
                            if(dx>0) {
                                direction = "right";
                                Log.e("right...","moving..");
                            }else{
                                direction = "left";
                                Log.e("left...","moving..");

                            }
                        } else {
                            if (dy > 0) {
                                direction = "down";
                                Log.e("down...", "moving..");

                                currX = event.getRawX();
                                currY = event.getRawY();

                                Log.e("x=", "" + (currX - oldx));
                                Log.e("y=", "" + (currY - oldy));


                                ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(v.getLayoutParams());
                                marginParams.setMargins((int) (currX - oldx), (int) (currY - oldy), 0, 0);

                                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
                                v.setLayoutParams(layoutParams);


                            } else {
                                direction = "up";
                                Log.e("up...", "moving..");
                            }
                            player.isPlaying();
                        }
                        kronometre.start();
                        break;

                        case MotionEvent.ACTION_UP:
                        // finger leaves the screen
                        player.pause();
                        kronometre.stop();
                        break;
                }
                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
