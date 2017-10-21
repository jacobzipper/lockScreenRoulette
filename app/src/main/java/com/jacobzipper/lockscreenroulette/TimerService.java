package com.jacobzipper.lockscreenroulette;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by zipper on 10/21/17.
 */

public class TimerService extends Service {
    public static long millis;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        millis = System.currentTimeMillis();
    }
}
