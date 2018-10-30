package asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by felipe.recabarren on 30-10-18.
 */

public class ProgressBarAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressBar progressBar;


        public ProgressBarAsyncTask(ProgressBar progressBar){
            this.progressBar = progressBar;
        }


        @Override protected void onPreExecute() {
            this.progressBar.setVisibility(View.VISIBLE);
        }

        @Override protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e("MainActivity", e.getMessage());
            }
            return null;
        }

        @Override protected void onPostExecute(Void param) {
            this.progressBar.setVisibility(View.INVISIBLE);
        }

}
