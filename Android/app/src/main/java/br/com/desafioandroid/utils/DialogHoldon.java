package br.com.desafioandroid.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.desafioandroid.R;

public class DialogHoldon {
    Dialog dialogHold;
    TextView message;

    public DialogHoldon(Context context) {

        dialogHold = new Dialog(context);

        dialogHold.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogHold.setContentView(R.layout.dialog_hodon);

        message = (TextView) dialogHold.findViewById(R.id.message);

        dialogHold.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        ProgressBar progressBar = (ProgressBar) dialogHold.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

    }

    public void showDialog() {
        dialogHold.show();
    }

    public void hideDialog() {
        dialogHold.dismiss();
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
