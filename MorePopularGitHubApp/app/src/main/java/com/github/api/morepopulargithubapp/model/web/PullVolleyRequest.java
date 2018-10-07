package com.github.api.morepopulargithubapp.model.web;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.api.morepopulargithubapp.R;
import com.github.api.morepopulargithubapp.model.vo.PullRequest;
import com.github.api.morepopulargithubapp.model.vo.Repository;
import com.github.api.morepopulargithubapp.presenter.PresenterApiCallBack;
import com.github.api.morepopulargithubapp.util.JsonUtil;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.api.morepopulargithubapp.util.ConstantsUitl.BAD_REQUEST;
import static com.github.api.morepopulargithubapp.util.ConstantsUitl.NOT_FOUND_CODE;
import static com.github.api.morepopulargithubapp.util.ConstantsUitl.SERVER_UNABLE_PROCESS_INSTRUCTIONS_CODE;
import static com.github.api.morepopulargithubapp.util.ConstantsUitl.UNAUTHORIZED_ERROR;

@EBean(scope = EBean.Scope.Singleton)
public class PullVolleyRequest {

    private static final String TAG = PullVolleyRequest.class.getSimpleName();
    public static final String PAGE = "page";

    @RootContext
    Context context;

    @Background
    public void requestPullRequest(final PresenterApiCallBack presenterApiCallBack, Repository repository) {
        final Map<Integer, String> responseMap = new HashMap<>();
        Uri uri = buildRepositoriesUri(repository);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, uri.toString(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<PullRequest> pullRequests = JsonUtil.parseJsonPullRequestsList(response.toString());
                            presenterApiCallBack.notifySuccess(pullRequests);
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            responseMap.put(-1, "");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String errorMessage = null;
                        NetworkResponse response = error.networkResponse;

                        if (response != null) {
                            errorMessage = validateMessagStatusCodeError(error, errorMessage, response);
                            responseMap.put(response.statusCode, errorMessage);
                        } else {
//                            Log.e(TAG,error.printStackTrace());
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

                    Log.d(TAG, error.getMessage());

                } catch (JSONException e) {
                    Log.e(TAG, "Não foi possível fazer o parser");
                }
                break;
            default:
                Log.d(TAG, "Código de status não reconhecido");
        }
        return errorMessage;
    }

    private Uri buildRepositoriesUri(Repository repository) {
        // Construindo uma URI
        return Uri.parse(context.getString(R.string.search_pull_request_url))
                .buildUpon()
                .appendPath(repository.getOwner().getLogin())
                .appendPath(repository.getName())
                .appendPath(context.getString(R.string.path_complement))
                .build();
    }


}
