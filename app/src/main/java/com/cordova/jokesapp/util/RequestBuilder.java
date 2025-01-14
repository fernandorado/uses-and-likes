package com.cordova.jokesapp.util;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cordova.jokesapp.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fernando on 10/18/2016.
 */
public class RequestBuilder {

    public final static String URL_MAIN_JSON = "https://jokes-server.herokuapp.com/jokes";
    public final static String URL_ALL_JOKES = URL_MAIN_JSON + "/all/";
    public final static String URL_JOKE_CHUNK = URL_MAIN_JSON + "/chunk/";
    public final static String URL_JOKE_LIKE = URL_MAIN_JSON + "/like/";
    public final static String URL_JOKE_DISLIKE = URL_MAIN_JSON + "/dislike/";

    public static void requestGetAllJokes (Context context, final String tag, boolean includeLatest, final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        final String url = includeLatest ? (URL_ALL_JOKES + tag): URL_MAIN_JSON;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(MainActivity.TAG, url);
                        Log.i(MainActivity.TAG, response.toString());
                        callback.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(MainActivity.TAG, error.toString());
                        callback.onError(error.getMessage());
                    }
                }){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "admin", "admin6699123").getBytes(), Base64.DEFAULT)));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public static void requestGetJokesByChunk (Context context, final String tag, long chunk, final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        final String url = URL_JOKE_CHUNK + tag + "/" + chunk;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(MainActivity.TAG, url);
                        Log.i(MainActivity.TAG, response.toString());
                        callback.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(MainActivity.TAG, error.toString());
                        callback.onError(error.getMessage());
                    }
                }){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "admin", "admin6699123").getBytes(), Base64.DEFAULT)));
                return params;
            }
        };
        queue.add(stringRequest);
    }


    public static void requestUpdateLike (Context context, final String urlWithID) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, urlWithID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(MainActivity.TAG, urlWithID);
                        Log.i(MainActivity.TAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(MainActivity.TAG, error.toString());
                    }
                }){

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "admin", "admin6699123").getBytes(), Base64.DEFAULT)));
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
