package com.felipemiranda.desafioconcrete.ui.main;

import com.felipemiranda.desafioconcrete.model.response.GenericErrorResponse;
import com.felipemiranda.desafioconcrete.network.RestCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by felipemiranda
 */

public abstract class BaseLoadingPresenter<T extends BaseLoadingView> {

    public T mView;
    private List<Call> mCalls;
    private Request mLastRequest;
    private Boolean showLoading = true;

    public abstract void bindView(T view);

    public void retryLastRequest() {
        if (mLastRequest != null)
            doRequest(mLastRequest.mCall.clone(), mLastRequest.mRestCallback, showLoading);
    }

    public <T> void addRequest(final Call call, final RestCallback<T> restCallback, final boolean showLoading) {
        doRequest(call, restCallback, showLoading);
    }

    private <T> void doRequest(final Call call, final RestCallback<T> restCallback, final boolean showLoading) {
        mLastRequest = new Request(call, restCallback);

        if (mView == null) return;

        if (showLoading)
            mView.showLoading();

        if (mCalls == null)
            mCalls = new ArrayList<>();

        mCalls.add(call);
        call.enqueue(new RestCallback<T>() {
            @Override
            public void onSuccess(T response) {
                mCalls.remove(call);
                if (mView != null) {
                    mView.hideLoading();
                    restCallback.onSuccess(response);
                }
            }

            @Override
            public void onError(GenericErrorResponse error) {
                mCalls.remove(call);
                if (mView != null) {
                    mView.hideLoading();
                    restCallback.onError(error);
                }
            }
        });
    }

    class Request {
        Call mCall;
        RestCallback mRestCallback;

        private Request(Call mCall, RestCallback mRestCallback) {
            this.mCall = mCall;
            this.mRestCallback = mRestCallback;
        }
    }
}
