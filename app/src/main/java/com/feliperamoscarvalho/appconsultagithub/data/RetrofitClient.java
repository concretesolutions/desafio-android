package com.feliperamoscarvalho.appconsultagithub.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that configures the retrofit for use
 */
public class RetrofitClient {

    public static final String BASE_URL = "https://api.github.com";

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder().create();

    /**
     * Starts the retrofit object
     */
    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
