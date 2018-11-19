package com.example.dartapp.dart;

import android.os.AsyncTask;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SendDartHitTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String[] params) {
        String url = params[0];
        JSONObject info = new JSONObject();
        try{
            info.put("score", params[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(MainActivity.getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        mRequestQueue.add(request);

        return "Sucessfully sent dart information";
    }


    @Override
    protected void onPostExecute(String s) {
        Toast toast = Toast.makeText(MainActivity.getContext(), "YAY A TOAST", Toast.LENGTH_LONG);
        toast.show();
        super.onPostExecute(s);
    }
}
