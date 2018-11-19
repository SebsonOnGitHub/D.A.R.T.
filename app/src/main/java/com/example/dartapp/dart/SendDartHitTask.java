package com.example.dartapp.dart;

import android.os.AsyncTask;
import android.widget.Toast;

public class SendDartHitTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String[] params) {
        return params[0];
    }


    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(MainActivity.getContext(), "YAY A TOAST", Toast.LENGTH_LONG);
        super.onPostExecute(s);
    }

    public void doInBackground() {
    }
}
