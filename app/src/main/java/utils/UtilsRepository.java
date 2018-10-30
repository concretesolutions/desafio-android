package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by felipe.recabarren on 20-10-18.
 */

public class UtilsRepository {

    public UtilsRepository() {
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public JSONObject stringToJsonObject(String json){
        try{
            return new JSONObject(json);
        }catch (Throwable t){
            return null;
        }
    }
    @Nullable
    public Bitmap loadBitmap(String url) {
        try{
            URL newurl = new URL(url);
            return BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
        }catch (Exception e){
            return null;
        }
    }



}
