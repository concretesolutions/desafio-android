package com.github.api.morepopulargithubapp.util;

import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

public class JsonUtil {
    private static Gson gson;

    public static Gson getGson() {

        if( gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static HashMap<String, Object> parseJson(Object src) {
        String jsonString = getGson().toJson(src);

        return  getGson().fromJson(jsonString, new TypeToken<HashMap<String,Object>>() {}.getType());
    }

    public static List<Repository> parseJsonRepositoryList(String jsonString) {
        return getGson().fromJson(jsonString, new TypeToken <List<Repository>>() {}.getType());
    }

    public static List<PullRequest> parseJsonPullRequestsList(String jsonString) {
        return getGson().fromJson(jsonString, new TypeToken <List<PullRequest>>() {}.getType());
    }
}
