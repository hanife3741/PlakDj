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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
       final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.progressbar);
        disc.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                     // finger touches the screen
                       // player.start();
                        kronometre.start();


                    case MotionEvent.ACTION_MOVE:
                        // finger moves on the screen
                            player.start();
                            kronometre.start();
                        disc.startAnimation(animation);

                        case MotionEvent.ACTION_UP:
                        // finger leaves the screen
                        player.pause();
                        kronometre.stop();
                        disc.clearAnimation();

                }
                return onTouch(v,event);
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
