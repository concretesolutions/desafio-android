package com.desafioconcrete.jonasmferreira.appgitjavapop.controller;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserController {
    public static String TAG = UserController.class.getSimpleName();
    public static String URL = "https://api.github.com/users/";
    public static void getUser(String username){
        getUser(username,"");
    }
    public static void getUser(String username,String jsonSearch){
        final RequestParams params = new RequestParams();
        String url = URL+":"+username;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setUserAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");

        client.get(url,params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                try {
                    Log.d(TAG, "Subiu");
                    Log.d(TAG, response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                try{
                    Log.d(TAG, "Subiu");
                    Log.d(TAG, errorResponse.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONArray errorResponse) {
                try{
                    Log.d(TAG, "Subiu");
                    Log.d(TAG, errorResponse.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable e) {
                try{
                    Log.d(TAG, "Subiu");
                    Log.d(TAG, responseString.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
