package com.desafioconcrete.jonasmferreira.appgitjavapop.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.desafioconcrete.jonasmferreira.appgitjavapop.utils.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RepositoryController {
    public static String TAG = RepositoryController.class.getSimpleName();
    public static String URL = "https://api.github.com/repos/";

    public static void getPullRequests(String criador,String repositorio){
        getPullRequests(criador,repositorio,"1");
    }
    public static void getPullRequests(String criador,String repositorio,String page){
        String url = URL+criador+"/"+repositorio+"/pulls";
        final RequestParams params = new RequestParams();
        params.put("page",page);
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
                    Log.d(TAG, "Subiu Obj");
                    Log.d(TAG, response.toString());
                    sendError();
                } catch (Exception e) {
                    sendError();
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                try {
                    Log.d(TAG, "Subiu");
                    Log.d(TAG, response.toString());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("success", true);
                    bundle.putString("pulls", response.toString());
                    Intent intent = new Intent("desafio-android-get-pull-request");
                    if (bundle != null) {
                        intent.putExtras(bundle);
                    }
                    LocalBroadcastManager.getInstance(MyApplication.getInstance()).sendBroadcast(intent);
                } catch (Exception e) {
                    sendError();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                try{
                    Log.d(TAG, "Não Subiu");
                    Log.d(TAG, errorResponse.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                sendError();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONArray errorResponse) {
                try{
                    Log.d(TAG, "Não Subiu");
                    Log.d(TAG, errorResponse.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                sendError();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable e) {
                try{
                    Log.d(TAG, "Não Subiu");
                    Log.d(TAG, responseString.toString());
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                sendError();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });



        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        mainHandler.post(myRunnable);
    }


    protected static void sendError(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("success", false);
        bundle.putString("pulls", "");
        Intent intent = new Intent("desafio-android-get-pull-request");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).sendBroadcast(intent);
    }
}
