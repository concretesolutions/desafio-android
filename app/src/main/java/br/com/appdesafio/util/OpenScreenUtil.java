package br.com.appdesafio.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class OpenScreenUtil {

    /**
     * method responsible for opening an activity.
     * @param context, activity context.
     * @param action, the action to be taken, in case which activity should be opened.
     * @param bundle, bundle with information that may or may not be passed to the next screen.
     * @param clearTask, flag to inform other activity must be cleared from the stack.
     */
    public static void openScreen(final Context context, final String action, final Bundle bundle, final boolean clearTask) {
        final Intent intent = new Intent(action);
        if (clearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
