package com.jacobzipper.lockscreenroulette;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends Activity {

    /**
     * This array contains the game board itself.
     */
    private int[][] gameBoard;

    /**
     * Object referencing the piece currently played
     */
    private Piece piece;

    /**
     * Contains <code>true</code> if game is lost, <code>false</code> instate.
     */
    boolean gameOver;

    /**
     * Temporal variable used to determine if it's time for a pice to fall.
     */
    long previousTime;

    /**
     * @see #previousTime
     */
    long timeBefore;

    /**
     * This class is called when te program starts and stay awake the whole time.
     */
    private GestureDetector gestureDetector;

    private TetrisView tV;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        BroadcastReceiver screenReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.classes.get((int)(Math.random()*MainActivity.classes.size()))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenReceiver, intentFilter);
        gameBoard = new int[10][22];

        gestureDetector = new GestureDetector(new SwipeGestureDetector());

        piece = new Piece(gameBoard);

        tV = new TetrisView(this);
        tV.setPiece(piece);
        setContentView(tV);

        int delay = 250; // delay for 1 sec.
        int period = 250; // repeat every 10 sec.
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                tV.getPiece().moveDown();
                if(tV.getPiece().isGameOver()) {
                    timer.cancel();
                    GameActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(getApplicationContext(),GameActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }
                    });
                }
                else if (tV.getPiece().getScore() >= 80) {
                    timer.cancel();
                    GameActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                }
            }
        }, delay, period);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuGameAbort) {
            finish();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onLeftSwipe() {
        tV.getPiece().moveLeft();
    }

    private void onRightSwipe() {
        tV.getPiece().moveRight();
    }

    private void onSingleTapConfirmed() {
        tV.getPiece().rotateClockwise();
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        // Swipe properties, you can change it to make the swipe
        // longer or shorter and speed
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();
                float yDiff = e1.getY() - e2.getY();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    GameActivity.this.onLeftSwipe();

                    // Right swipe
                } else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    GameActivity.this.onRightSwipe();
                }
            } catch (Exception e) {
                Log.e("YourActivity", "Error on gestures");
            }
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            GameActivity.this.onSingleTapConfirmed();
            return true;
        }
    }
}
