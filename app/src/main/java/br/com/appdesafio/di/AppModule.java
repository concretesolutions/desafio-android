package br.com.appdesafio.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;


import br.com.appdesafio.application.App;
import br.com.appdesafio.constants.Constants;
import br.com.appdesafio.service.IService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Model class that provides objects that are used throughout the application.
 */
@Module
public abstract class AppModule {

    private static final String CACHE_CONTROL = "Cache-Control";


    @Binds
    protected abstract Context bindContext(Application application);


    /**
     * Returns the request service object.
     *
     * @return
     */
    @Singleton
    @Provides
    public static IService provideService() {
        final OkHttpClient okHttpClient =  new OkHttpClient.Builder()

                .addInterceptor( interceptorCacheOffline() )
                .addNetworkInterceptor( interceptorCacheOnline() )
                .cache( configCache() )
                .build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl( Constants.getBaseUrl() )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        return retrofit.create(IService.class);



    }

    /**
     *
     * @return
     */
    private static Cache configCache ()
    {
        Cache cache = null;
        try
        {
            cache = new Cache( new File( App.getInstance().getCacheDir(), "http-cache" ),
                    10 * 1024 * 1024 );
        }
        catch (Exception e)
        {

        }
        return cache;
    }


    /**
     * Adds the online cache interceptor.
     * @return
     */

    public static Interceptor interceptorCacheOnline ()
    {
        return chain -> {
            Response response = chain.proceed( chain.request() );
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge( 2, TimeUnit.MINUTES )
                    .build();

            return response.newBuilder()
                    .header( CACHE_CONTROL, cacheControl.toString() )
                    .build();
        };
    }

    /**
     *Adds the Offline Cache Interceptor.
     * @return
     */
    public static Interceptor interceptorCacheOffline ()
    {
        return chain -> {
            Request request = chain.request();

            if ( !App.hasNetwork() )
            {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale( 5, TimeUnit.DAYS )
                        .build();

                request = request.newBuilder()
                        .cacheControl( cacheControl )
                        .build();
            }

            return chain.proceed( request );
        };
    }
}
