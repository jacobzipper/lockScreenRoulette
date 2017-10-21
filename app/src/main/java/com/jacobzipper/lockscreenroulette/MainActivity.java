package com.jacobzipper.lockscreenroulette;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

/**
 * Created by zipper on 10/21/17.
 */

public class MainActivity extends Activity {
    public static ArrayList<Class<?>> classes;
    public static int gameCursor = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classes = new ArrayList<Class<?>>();
        //classes.add(FruitActivity.class);
        //classes.add(GameActivity.class);
        classes.add(Pong.class);
        classes.add(FlappyFragment.class);
        classes.add(GameScreen.class);
        BroadcastReceiver screenReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    startActivity(new Intent(getApplicationContext(),classes.get(gameCursor)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    gameCursor++;
                    MainActivity.gameCursor %= MainActivity.classes.size();
                    unregisterReceiver(this);
                    finish();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenReceiver, intentFilter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                //startService(new Intent(getApplicationContext(), LockScreen.class));
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.DISABLE_KEYGUARD, Manifest.permission.SYSTEM_ALERT_WINDOW)
                .check();
    }

}
