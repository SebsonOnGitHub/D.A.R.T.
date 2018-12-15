package com.example.dartapp.dart;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameLogic gameLogic = new GameLogic();
        updateTextView(gameLogic.getGameData());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    SendDartHitTask sendDartHitTask = new SendDartHitTask(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String matchInfo) {
                            // The matchinfo from server will be available in matchInfo variable
                            try {
                                JSONObject dartInfo = new JSONObject(matchInfo);
                                String status = dartInfo.getString("answer");
                                Log.d("ANTON", status);
                                if(status.equals("accepted")) {
                                    Log.d("ANTON", "Got a dart");
                                    Log.d("ANTON", dartInfo.toString());
                                    String val = dartInfo.getString("score");
                                    String dartNum = dartInfo.getString("dart");
                                    Log.d("ANTON", "value" + val);
                                    Log.d("ANTON", "dartnum " + dartNum);
                                    updateTextView(gameLogic.update(Integer.valueOf(dartNum), Integer.valueOf(val)));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    sendDartHitTask.execute("http://10.0.2.2:8000/getdart", "12");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
    public static Context getContext(){
        return MainActivity.context;
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
        if(data[9] == -1)
            ((TextView)findViewById(R.id.points_curr_2)).setText("");
        else
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
