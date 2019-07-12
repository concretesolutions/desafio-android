package com.felipe.palma.desafioconcrete.network;

import com.felipe.palma.desafioconcrete.utils.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Felipe Palma on 03/07/2019.
 */
public class RetrofitInstance {

    private static Retrofit mRetrofit;

    /*
    CRIANDO INSTANCIA DO RETROFIT
     */
    public static Retrofit getInstance(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
