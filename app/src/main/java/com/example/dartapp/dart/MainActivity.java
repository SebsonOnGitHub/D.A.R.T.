package com.example.dartapp.dart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Log.d("ANTON", "LOOPAR");
                    SendDartHitTask sendDartHitTask = new SendDartHitTask(new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String matchInfo) {
                            // The matchinfo from server will be available in matchInfo variable
                            Toast toast = Toast.makeText(MainActivity.getContext(), matchInfo, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                    sendDartHitTask.execute("http://10.0.2.2:8000/getdart", "12");
                    try {
                        Thread.sleep(2000);
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
}
