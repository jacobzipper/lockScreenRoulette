package com.jacobzipper.lockscreenroulette;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Main activity of Pong game.
 */
public class Pong extends Activity {

    private static final int MENU_NEW_GAME = 1;
    private static final int MENU_RESUME = 2;
    private static final int MENU_EXIT = 3;
    public static Activity mActivity;
    BroadcastReceiver screenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                startActivity(new Intent(getApplicationContext(),MainActivity.classes.get(MainActivity.gameCursor)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                MainActivity.gameCursor++;
                MainActivity.gameCursor %= MainActivity.classes.size();
                unregisterReceiver(this);
                finish();
            }
        }
    };

    private PongThread mGameThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pong_layout);
        mActivity = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenReceiver, intentFilter);

        final PongView mPongView = (PongView) findViewById(R.id.main);
        mPongView.setStatusView((TextView) findViewById(R.id.status));
        mPongView.setScoreView((TextView) findViewById(R.id.score));

        mGameThread = mPongView.getGameThread();
        if (savedInstanceState == null) {
            mGameThread.setState(PongThread.STATE_READY);
        } else {
            mGameThread.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameThread.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mGameThread.saveState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_NEW_GAME, 0, R.string.menu_new_game);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);
        menu.add(0, MENU_EXIT, 0, R.string.menu_exit);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_NEW_GAME:
                mGameThread.startNewGame();
                return true;
            case MENU_EXIT:
                finish();
                return true;
            case MENU_RESUME:
                mGameThread.unPause();
                return true;
        }
        return false;
    }

}
