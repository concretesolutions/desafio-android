package com.example.sharked.accenture.webservices;

import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRequestHandler<T> extends AsyncTask<Object, Void, T>{

    @Override
    protected T doInBackground(Object[] urls) {
        try {

            HttpGet httpGet = new HttpGet(getURL());
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpGet);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);


                Gson gson = this.getGsonBuilder().create();
                T output = gson.fromJson(data , this.getResponseClass());
                return output;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(T result) {
        Log.e("Error",(null == result)?"NULL":"NOT NULL");
        Log.e("Class",result.getClass().toString());
        EventBus.getDefault().post(result);
    }


    protected GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

    protected abstract String getURL();

    protected abstract Class<T> getResponseClass();

    public boolean isList() {return false;}

    protected abstract String getHttpMethod();


}
