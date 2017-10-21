/*
Snake - an Android Game
Copyright 2012 Nick Eyre <nick@nickeyre.com>

Snake is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Snake is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Snake.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.jacobzipper.lockscreenroulette;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GameScreen extends Activity {

  private Game game;
  private FrameLayout frameView;
  private TextView score;
  private Activity mActivity;
  SharedPreferences userPreferences, speedSetting;
  private boolean darkTheme=false,snakeOriented=false,classicMode=false;
  private int speed;

  // Initialize Game Screen
  @Override
  public void onCreate(Bundle savedInstanceState) {
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
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
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
    registerReceiver(screenReceiver, intentFilter);
    // Set Theme, Controls Mode, View Mode & Speed According to Settings
    // Speed Setting is Stored in a Different File Because It Should Not Be Synced Across Devices
    userPreferences = getSharedPreferences("settings", 0);
    speedSetting = getSharedPreferences("speed", 0);
    if(userPreferences.getInt("theme",0) == 1){
      setTheme(android.R.style.Theme_Holo);
      darkTheme=true;
    }
    if(userPreferences.getInt("view",0) == 1)  classicMode = true;
    if(userPreferences.getInt("controls",0) == 1)  snakeOriented = true;
    speed = speedSetting.getInt("speed", 1);

    // Create Game View & Add Handler to Current Activity
    super.onCreate(savedInstanceState);
    if(snakeOriented)
      setContentView(R.layout.game_2arrow);
    else
      setContentView(R.layout.game_4arrow);
    mActivity = this;

    // Grab Score TextView Handle, Create Game Object & Add Game to Frame
    score = (TextView) findViewById(R.id.score);
    game = new Game(this,this,score,darkTheme,classicMode,snakeOriented,speed);
    frameView = (FrameLayout) findViewById(R.id.gameFrame);
    frameView.addView(game);

  }

  // On Left Arrow Click, Snake Turns Left
  // Called from Button in View
  public void leftClick(View view){
    game.snake.turnLeft();
  }

  // On Right Arrow Click, Snake Turns Right
  // Called from Button in View
  public void rightClick(View view){
    game.snake.turnRight();
  }

  // On Down Arrow Click, Snake Turns Down (Four Direction Only)
  // Called from Button in View
  public void downClick(View view){
    game.snake.turnDown();
  }

  // On Up Arrow Click, Snake Turns Up (Four Direction Only)
  // Called from Button in View
  public void upClick(View view){
    game.snake.turnUp();
  }

  // On Game Over, Make Alert Dialog with Two Options
  // Called from Game Object
  public void gameOver(){

    game.setup();
    game.invalidate();
  }

  // On Game Pause, Stop Snake & Make Alert Dialog
  // Called from Hardware Button Handler and Activity Pause
  public void pauseGame(){

    // Do Nothing if Game Over
    if(game.gameOver) return;

    game.snake.stopped = true;

    game.setup();
    game.invalidate();
  }

  // Hardware Button Presses
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)  {

    // On Menu or Back Press, Pause Game
    if ((keyCode == KeyEvent.KEYCODE_MENU ) && event.getRepeatCount() == 0)
      pauseGame();

    // On Left D-Pad Button, Snake Turns Left
    if((keyCode == KeyEvent.KEYCODE_DPAD_LEFT) && event.getRepeatCount()==0)
      game.snake.turnLeft();

    // On Right D-Pad Button, Snake Turns Right
    if((keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) && event.getRepeatCount()==0)
      game.snake.turnRight();

    return true;
  }

  // Pause Game when Activity Paused
  @Override
  public void onPause(){
    super.onPause();
    pauseGame();
  }

}
