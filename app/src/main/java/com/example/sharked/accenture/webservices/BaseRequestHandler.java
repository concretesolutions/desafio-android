package com.example.sharked.accenture.webservices;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;

public abstract class BaseRequestHandler<T> extends AsyncTask<Object, Void, T> {

    private HttpClient httpclient = new DefaultHttpClient();
    private Exception error;
    private boolean debug = true;

    @Override
    protected T doInBackground(Object[] urls) {
        try {
            String url = getURL();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity);
            if (debug){
                Log.e("Debug","Url:"+url);
                Log.e("Debug","Data:"+data);
                Log.e("Debug", "Status" + status);

            }

            if (status == 200) {
                Gson gson = this.getGsonBuilder().create();
                return gson.fromJson(data , this.getResponseClass());
            }

        } catch (IOException e) {
            error = e;
            e.printStackTrace();
        } catch (JsonParseException e) {
            error = e;
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(T result) {
        Log.e("Debug", (null == result) ? "NULL" : "NOT NULL");
        if (null != result) EventBus.getDefault().post(result);
    }


    private GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

    public Exception getException(){return error;}

    public void setDebug(boolean b) {
        debug = b;
    }
    protected abstract String getURL();

    protected abstract Class<T> getResponseClass();

    protected abstract String getHttpMethod();


}
