package com.example.dartapp.dart;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MainActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisActivity = this;
        final GameLogic gameLogic = new GameLogic();
        final Random rand = new Random();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; true; i++){
                    gameLogic.i = i;
                    thisActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateTextView(gameLogic.update(gameLogic.i%3 + 1,
                                    rand.nextInt(21), gameLogic.i < 6));
                        }
                    });
                SystemClock.sleep(6000);
                }
            }
        });

        thread.start();
    }

    public void updateTextView(int[] data){
        ((TextView)findViewById(R.id.points_1_1)).setText(Integer.toString(data[0]));
        ((TextView)findViewById(R.id.remaining_1_1)).setText(Integer.toString(data[1]));
        ((TextView)findViewById(R.id.points_1_2)).setText(Integer.toString(data[2]));
        ((TextView)findViewById(R.id.remaining_1_2)).setText(Integer.toString(data[3]));
        ((TextView)findViewById(R.id.points_2_1)).setText(Integer.toString(data[4]));
        ((TextView)findViewById(R.id.remaining_2_1)).setText(Integer.toString(data[5]));
        ((TextView)findViewById(R.id.points_2_2)).setText(Integer.toString(data[6]));
        ((TextView)findViewById(R.id.remaining_2_2)).setText(Integer.toString(data[7]));
        ((TextView)findViewById(R.id.points_curr_1)).setText(Integer.toString(data[8]));
        if(data[9] == -1)
            ((TextView)findViewById(R.id.points_curr_2)).setText("");
        else
            ((TextView)findViewById(R.id.points_curr_2)).setText(Integer.toString(data[9]));
        if(data[10] == -1)
            ((TextView)findViewById(R.id.points_curr_3)).setText("");
        else
            ((TextView)findViewById(R.id.points_curr_3)).setText(Integer.toString(data[10]));
        ((TextView)findViewById(R.id.points_curr_tot)).setText(Integer.toString(data[11]));
        ((TextView)findViewById(R.id.remaining_curr)).setText(Integer.toString(data[12]));
        ((TextView)findViewById(R.id.round_1)).setText(Integer.toString(data[13]) + " | " + Integer.toString(data[15]));
        ((TextView)findViewById(R.id.round_2)).setText(Integer.toString(data[14]) + " | " + Integer.toString(data[15]));

        if(data[16] == 1) {
            findViewById(R.id.round_1).setBackgroundColor(Color.GREEN);
            findViewById(R.id.round_2).setBackgroundColor(Color.TRANSPARENT);
        }
        if(data[16] == 2) {
            findViewById(R.id.round_1).setBackgroundColor(Color.TRANSPARENT);
            findViewById(R.id.round_2).setBackgroundColor(Color.GREEN);
        }
    }

}
