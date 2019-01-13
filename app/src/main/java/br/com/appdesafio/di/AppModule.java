package br.com.appdesafio.di;

import android.app.Application;
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
import br.com.appdesafio.task.AppExecutors;
import br.com.appdesafio.task.ExecutorsBackground;
import br.com.appdesafio.task.ExecutorsMainThread;
import br.com.appdesafio.util.ConnectionUtil;
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
    private static final int THREAD_COUNT = 3;
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

                .addInterceptor( offlineCacheInterceptor() )
                .addNetworkInterceptor( provideCacheInterceptor() )
                .cache( configCache() )
                .build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl( Constants.getBaseUrl() )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        return retrofit.create(IService.class);



    }
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



    public static Interceptor provideCacheInterceptor ()
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

    public static Interceptor offlineCacheInterceptor ()
    {
        return chain -> {
            Request request = chain.request();

            if ( !App.hasNetwork() )
            {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale( 7, TimeUnit.DAYS )
                        .build();

                request = request.newBuilder()
                        .cacheControl( cacheControl )
                        .build();
            }

            return chain.proceed( request );
        };
    }



    /**
     * Returns the sharedpreferences object for dependency injection.
     * @param context
     * @return
     */
    @Provides
    public static SharedPreferences prividePreferences(final Context context) {
        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    /**
     * returns the database object for dependency injection.
     *
     * @param
     * @return
     */
/*    @Singleton
    @Provides
    public static AppDatabase provideDB(final Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "git_hub_db")
                .fallbackToDestructiveMigration().build();
    }*/

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new ExecutorsBackground(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new ExecutorsMainThread());
    }






}
