package com.felipemiranda.desafioconcrete.network;


import com.felipemiranda.desafioconcrete.Application;
import com.felipemiranda.desafioconcrete.model.response.GenericErrorResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by felipemiranda
 */

public abstract class RestCallback<T extends Object> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(GenericErrorResponse error);

    @Override
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (response.isSuccessful())
            onSuccess(response.body());
        else {
            GenericErrorResponse error = GenericErrorResponse.getResponseError(response.errorBody());
            if (error == null || error.getMessage() == null)
                error = GenericErrorResponse.getExceptionError();

            onError(error);
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
        if (!Application.getInstance().isOnline())
            onError(GenericErrorResponse.getConnectionError());
        else
            onError(GenericErrorResponse.getExceptionError());
    }
}
