package com.example.dartapp.dart;

import android.os.AsyncTask;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BaseHttpStack;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class SendDartHitTask extends AsyncTask<String, Void, String> {
    OnTaskCompleted listener;
    JSONObject response;
    boolean gotResponse = false;

    SendDartHitTask(OnTaskCompleted listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String[] params) {
        String url = params[0];
        JSONObject info = new JSONObject();
        try{
            info.put("match_id", params[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(MainActivity.getContext());
        JsonObjectRequest request = new JsonObjectRequest(GET, url, info, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject resp) {
                response = resp;
                gotResponse = true;
                listener.onTaskCompleted(resp.toString());
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

        };
        mRequestQueue.add(request);
       // mRequestQueue.start();
        if(gotResponse){
            return response.toString();
        }
        return "Something went wrong";
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
