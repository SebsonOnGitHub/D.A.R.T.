package com.example.dartapp.dart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendDartHitTask sendDartHitTask = new SendDartHitTask(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String matchInfo) {
                // The matchinfo from server will be available in matchInfo variable
                Toast toast = Toast.makeText(MainActivity.getContext(), matchInfo,Toast.LENGTH_LONG);
                toast.show();
            }
        });
        sendDartHitTask.execute("http://10.0.2.2:3000/", "12");
    }
    public static Context getContext(){
        return MainActivity.context;
    }
}
