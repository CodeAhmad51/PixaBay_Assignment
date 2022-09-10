package com.example.pixabayassignment.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pixabayassignment.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkClient {


    final String URL_PREFIX = "https://pixabay.com/api/?key=29663554-ebdfc803cdab90170300a882b&q=";
    final String URL_SUFFIX = "&image_type=photo";

    public void getData(String query , int page , Context context){

        List<String> imgUrls = new ArrayList<>();
        MainActivity activity = (MainActivity) context;

        Log.d("S6" , "Entered");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_PREFIX + query + "&page=" + page + URL_SUFFIX, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("hits");

                            for(int i=0; i< array.length();i++){
                                JSONObject object = array.getJSONObject(i);
                                imgUrls.add(object.getString("webformatURL"));

                            }

                            activity.updateUrls(imgUrls);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        activity.addRequest(jsonObjectRequest);
    }

}

