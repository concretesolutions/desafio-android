package contarini.com.desafio_tembici.data;


import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import contarini.com.desafio_tembici.BuildConfig;
import contarini.com.desafio_tembici.NetworkConstants;
import contarini.com.desafio_tembici.data.interceptors.RetrofitException;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private static final String ERROR_UNKNOWN_HOST = "Rede Indisponível";
    private static final String ERROR_PARSE_DEBUG = "JsonParseException";
    private static final String ERROR_PARSE_PRODUCTION = "Pode ser necessário atualizar seu aplicativo.";
    private static final String ERROR_TIMEOUT_ERROR = "Tempo Excedido.";

    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Single<?>> {
        private final Retrofit retrofit;
        private final CallAdapter<?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Single<?> adapt(Call<R> call) {
            return ((Single) wrapped.adapt(call)).onErrorResumeNext(new Function<Throwable, SingleSource>() {
                @Override
                public SingleSource apply(Throwable throwable) throws Exception {
                    return Single.error(asRetrofitException(throwable));
                }
            });
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            if (throwable instanceof HttpException) {
                // We had non-2XX http error
                switch (((HttpException) throwable).code()) {
                    case NetworkConstants.CODE_UNKNOWN:
                        if (!BuildConfig.DEBUG)
                           // Crashlytics.logException(new Throwable(((HttpException) throwable).response().raw().request().url().toString() + " - " + ((HttpException) throwable).message()));
                        break;
                }
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);

            } else if (throwable instanceof IOException) {
                RetrofitException returnableThrowable = RetrofitException.unexpectedError(throwable);
                // A network or conversion error happened
                if (throwable instanceof UnknownHostException) {
                    // Network error
                    throwable = new UnknownHostException(ERROR_UNKNOWN_HOST);
                    returnableThrowable = RetrofitException.networkError((IOException) throwable);
                } else if (throwable instanceof SocketTimeoutException) {
                    // Timeout error
                    throwable = new SocketTimeoutException(ERROR_TIMEOUT_ERROR);
                    returnableThrowable = RetrofitException.networkError((IOException) throwable);
                }
                return returnableThrowable;
            } else if (throwable instanceof JsonParseException) {
                //Parse error
                throwable = new IOException(BuildConfig.DEBUG ?
                        ERROR_PARSE_DEBUG + " " + throwable.getMessage() :
                        ERROR_PARSE_PRODUCTION);
                if (!BuildConfig.DEBUG) return RetrofitException.unexpectedError(throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable);
        }
    }
}
