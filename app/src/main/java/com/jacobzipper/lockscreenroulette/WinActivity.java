package com.jacobzipper.lockscreenroulette;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zipper on 10/21/17.
 */

public class WinActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        TextView timeText = (TextView) findViewById(R.id.winText);
        final double time = (System.currentTimeMillis() - MainActivity.startTime) / 1000;
        timeText.setText("You wasted " + time + " seconds unlocking your phone...");
        findViewById(R.id.fbButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I just wasted " + time + " seconds using lockScreenRoulette trying to open my phone!");
                startActivity(Intent.createChooser(shareIntent, "Share..."));
            }
        });
        findViewById(R.id.twitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I just wasted " + time + " seconds using lockScreenRoulette trying to open my phone!");
                startActivity(Intent.createChooser(shareIntent, "Share..."));
            }
        });

        findViewById(R.id.sadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout screen = (RelativeLayout) findViewById(R.id.winLayout);
                Animation fadeAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
                fadeAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                screen.startAnimation(fadeAnim);
            }
        });
    }
}
