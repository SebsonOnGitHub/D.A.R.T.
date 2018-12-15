package com.example.dartapp.dart;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.Random;

public class LoopTask extends AsyncTask<GameLogic, Void, Integer> {
    LoopCompleted listener;

    LoopTask(LoopCompleted listener){this.listener = listener;}

    @Override
    protected Integer doInBackground(GameLogic... strings) {
        Random rand = new Random();
        for(int i = 0; true; i++) {
            int[] data = strings[0].update(i + 1, rand.nextInt(21), false);
            listener.loopCompleted(data);
            i = i%3;
            SystemClock.sleep(4000);
        }
    }
}
