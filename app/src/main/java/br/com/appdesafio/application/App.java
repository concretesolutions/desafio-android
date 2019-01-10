package br.com.appdesafio.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.net.Uri;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import javax.inject.Inject;

import br.com.appdesafio.di.DaggerAppComponent;
import br.com.appdesafio.task.AppExecutors;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    public AppExecutors appExecutors;

   /* @Inject
    public AppDatabase appDatabase;
*/
    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(getPicassoCahe());
        DaggerAppComponent.builder().application(this)
                .build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }


    /**
     * Ã© uma Ãºnica instancia da biblioteca picasso e configura o cache de imagens.
     * @return
     */
    private Picasso getPicassoCahe(){
        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());

        /**
         * sets a percentage of application memory for the image cache
         */
        builder.memoryCache(new LruCache(getBytesMemoryCache(50)));
        Picasso.RequestTransformer requestTransformer =  new Picasso.RequestTransformer() {
            @Override
            public Request transformRequest(Request request) {

                return request;
            }
        };
        builder.requestTransformer(requestTransformer);

        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri,
                                          Exception exception) {

            }
        });

        return builder.build();
    }

    private int getBytesMemoryCache(int percent){
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)
                getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);
        double availableMemory= memoryInfo.availMem;
        return (int)(percent*availableMemory/100);
    }
}
