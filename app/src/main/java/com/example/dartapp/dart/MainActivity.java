package com.example.dartapp.dart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendDartHitTask sendDartHitTask = new SendDartHitTask();
        sendDartHitTask.execute("Fisk");
    }
    public static Context getContext(){
        return MainActivity.context;
    }
}
