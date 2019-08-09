package com.postulacion.githubjavapop.utils;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.postulacion.githubjavapop.model.ItemRepository;
import com.postulacion.githubjavapop.model.PullRequest;
import com.postulacion.githubjavapop.model.Repository;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Utilities {
    public static List<Repository> convertJsonToRepository(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String content = null;
        while ((content = bufferedReader.readLine()) != null) {
            stringBuilder.append(content);
        }
        String json = stringBuilder.toString();

        Gson gson = new GsonBuilder().setLenient().create();
        return gson.fromJson(json, ItemRepository.class).getRepositoryList();
    }

    public static List<PullRequest> convertJsonToPullRequest(BufferedReader bufferedReader) throws IOException, JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        String content = null;
        while ((content = bufferedReader.readLine()) != null) {
            stringBuilder.append(content);
        }
        String json = stringBuilder.toString();

        Gson gson = new GsonBuilder().setLenient().create();

        Type listType = new TypeToken<List<PullRequest>>() {
        }.getType();

        return gson.fromJson(json, listType);
    }

    public static void getNickName() {
        System.out.println("Start getNick");
        String Url = "https://api.github.com/users/DanielAlonsoTM";
        URL url = null;
        try {
            url = new URL(Url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String content = null;
            while ((content = bufferedReader.readLine()) != null) {
                stringBuilder.append(content);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            System.out.println("Name: " + jsonObject.getString("Name"));
        } catch (Exception e) {
            Log.e("ERROR", "Detail: " + e);
        }
    }
}
