package com.paulobsa.desafioandroid.util;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class Util {
    public static final String SEARCH_RESULT = "SEARCH_RESULT";
    public static final String LAST_PAGE = "LAST_PAGE";
    public static final String LOG_TAG = "DESAFIO_ANDROID";
    public static final String LOADING = "LOADING";
    public static final String FIRST_ATTEMPT = "FIRST_ATTEMPT";
    public static final String CURRENT_PAGE = "CURRENT_PAGE";

    private static String GITHUB_API_BASE_URL = "https://api.github.com";
    private static String SEARCH_REPOS = "search/repositories";
    private static String JAVA = "language:Java";

    public static URL buildJavaSearch(Integer page) {
        return buildUrl(SEARCH_REPOS, JAVA, page);
    }

    public static URL buildUrl(String searchPath, String language, Integer page) {
        Uri builtUri = Uri.parse(GITHUB_API_BASE_URL).buildUpon()
                .appendEncodedPath(searchPath)
                .appendQueryParameter("q", language)
                .appendQueryParameter("sort", "start")
                .appendQueryParameter("page", page.toString())
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
