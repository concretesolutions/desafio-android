package com.github.api.morepopulargithubapp.model.web;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenter.PresenterApiCallBack;
import com.github.api.morepopulargithubapp.util.JsonUtil;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.api.morepopulargithubapp.util.ConstantsUitl.*;

@EBean(scope = EBean.Scope.Singleton)
public class RepositoryRequest {

    private static final String TAG = RepositoryRequest.class.getSimpleName();
    public static final String PAGE = "page";

    @RootContext
    Context context;

    @Background
    public void requestRepositorie(final PresenterApiCallBack presenterApiCallBack, int pageNumber) {
        final Map<Integer, String> responseMap = new HashMap<Integer, String>();
        Uri uri = buildRepositoriesUri(String.valueOf(pageNumber));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String items = response.getString("items");
                            List<Repository> repositories = JsonUtil.parseJsonRepositoryList(items);
                            presenterApiCallBack.notifySuccess(repositories);
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            responseMap.put(-1, "");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String errorMessage = "";
                        NetworkResponse response = error.networkResponse;

                        if (response != null) {
                            errorMessage = validateMessagStatusCodeError(error, errorMessage, response);
                        }

                        if (!TextUtils.isEmpty(errorMessage) && response.statusCode == 422) {
                            responseMap.put(response.statusCode, errorMessage);
                        } else {
                            responseMap.put(-1, "");
                        }
                        presenterApiCallBack.notifyError(responseMap);
                    }
                });

        VolleyRequest.getInstance(context).addToRequestQueue(request);
    }

    private String validateMessagStatusCodeError(VolleyError error, String errorMessage, NetworkResponse response) {

        switch (response.statusCode) {
            case NOT_FOUND_CODE: // não encontrado
            case SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE: // servidor não conseguiu porcessar as intruções de request
            case BAD_REQUEST: // bad request
            case UNAUTHORIZED_ERROR: // não autorizado

                try {
                    String string = new String(error.networkResponse.data);
                    JSONObject object = new JSONObject(string);
                    if (object.has("message")) {
                        errorMessage = object.get("message").toString();
                        Log.d(TAG, errorMessage);

                    } else if (object.has("error_description")) {
                        errorMessage = object.get("error_description").toString();
                        Log.d(TAG, errorMessage);
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "Não foi possível fazer o parser");
                }
                break;
            default:
                Log.d(TAG, "Código de status não reconhecido");
        }
        return errorMessage;
    }

    private Uri buildRepositoriesUri(String page) {
        // Construindo uma URI
        return Uri.parse(context.getString(R.string.search_repositories_url))
                .buildUpon()
                .appendQueryParameter(PAGE, page)
                .build();
    }

}
