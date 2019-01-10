package br.com.appdesafio.model.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;



public class SharedPreference {

    private SharedPreferences sharedPref;


    @Inject
    public SharedPreference(final SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    /**
     * save token in shared preferences.
     * @param context
     * @param token
     * @return
     */
/*    public boolean isSaveTokenUser(final Context context, final String token) {
        final SharedPreferences.Editor editor = sharedPref.edit().putString(context.getString(R.string.token),
                token);
        return  editor.commit();


    }*/

    /**
     * retrieve token in shared preferences.
     * @param context
     * @return
     */
/*    public String getIsToken(final Context context) {
        return sharedPref.getString(context
                .getString(R.string.token), "");


    }*/
}
