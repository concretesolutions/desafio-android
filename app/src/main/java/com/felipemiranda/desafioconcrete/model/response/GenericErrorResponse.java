package com.felipemiranda.desafioconcrete.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import okhttp3.ResponseBody;

/**
 * Created by felipemiranda
 */

public class GenericErrorResponse implements Parcelable {

    private String message;

    public GenericErrorResponse() {}

    protected GenericErrorResponse(Parcel in) {
        message = in.readString();
    }

    public static final Creator<GenericErrorResponse> CREATOR = new Creator<GenericErrorResponse>() {
        @Override
        public GenericErrorResponse createFromParcel(Parcel in) {
            return new GenericErrorResponse(in);
        }

        @Override
        public GenericErrorResponse[] newArray(int size) {
            return new GenericErrorResponse[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static GenericErrorResponse getResponseError(final ResponseBody responseBody) {
        GenericErrorResponse error;
        try {
            error = new Gson().fromJson(responseBody.string(), GenericErrorResponse.class);
        } catch (Exception e) {
            error = getExceptionError();
        }
        return error;
    }

    public static GenericErrorResponse getExceptionError() {
        final GenericErrorResponse error = new GenericErrorResponse();
        error.setMessage("Desculpe-nos, não foi possível processar a sua solicitação no momento.");
        return error;
    }

    public static GenericErrorResponse getConnectionError() {
        final GenericErrorResponse error = new GenericErrorResponse();
        error.setMessage("Você não está conectado, por favor tente novamente mais tarde.");
        return error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }
}
