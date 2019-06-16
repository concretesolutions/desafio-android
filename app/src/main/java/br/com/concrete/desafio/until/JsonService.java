package br.com.concrete.desafio.until;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonService<T> {
    private final Retrofit retrofit;
    private T data;
    private boolean dataReady;
    private ChangeListener listener;
    private final CompositeDisposable compositeDisposable;

    public JsonService(String url, Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(setupClient(context))
                .build();

        setDataReady(false);

        compositeDisposable = new CompositeDisposable();
    }

    /**
     * Cria um cliente OKHttp com um cache de 5 MB e validade de 60 segundos.
     * @param context Contexto da aplicação.
     * @return O cliente com cache.
     */
    private OkHttpClient setupClient(Context context) {
        long cacheSize = 5242880L; // 5 * 1024 * 1024
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .header("Cache-Control", "public, max-age=" + 60)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
    }

    /**
     * Usa o retrofit para ler os dados json.
     * @param service Interface com métodos que mapeiam os endpoints.
     * @param methodName Método da interface a ser invocado.
     * @param args Parâmetros a serem usados (query ou path).
     */
    @SuppressWarnings("unchecked")
    public void getJson(Class service, String methodName, String... args) {
        try {
            Object apiService = retrofit.create(service);
            Method method = null;

            for (Method m: apiService.getClass().getDeclaredMethods()) {
                if (m.getName().equals(methodName)) {
                    method = m;
                    break;
                }
            }

            if (method != null) {
                Single<T> data = (Single<T>) method.invoke(apiService, (Object[]) args);
                data.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<T>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(T data) {
                                setData(data);
                                setDataReady(true);
                            }

                            @Override
                            public void onError(Throwable e) {
                                setDataReady(true);
                                Log.d("ERROR JSON SERVICE (1)", e.getMessage());
                            }
                        });
            }
            else {
                throw new NoSuchMethodException(methodName);
            }
        }
        catch (Exception e) {
            Log.d("ERROR JSON SERVICE (2)", e.getMessage());
        }
    }

    public void clearDisposable() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public boolean isDataReady() {
        return dataReady;
    }

    private void setDataReady(boolean dataReady) {
        this.dataReady = dataReady;

        if(listener != null) {
            listener.onChange();
        }
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}