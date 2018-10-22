package asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

import utils.UtilsRepository;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class RepositoryAsyncTask extends AsyncTask<String,Void,String> {
    HttpClient httpclient;
    HttpGet httpget;
    HttpResponse response;
    String result;
    UtilsRepository utilsRepository;

    public RepositoryAsyncTask(){

    }

    @Override
    protected String doInBackground(String... strings) {
        httpclient = new DefaultHttpClient();
        httpget = new HttpGet(strings[0]);
        utilsRepository = new UtilsRepository();
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = utilsRepository.convertStreamToString(instream);
                instream.close();

            }
            else{
                Log.e("entity","is null");
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());

        }
        return result;
    }

}
