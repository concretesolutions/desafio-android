package br.com.concrete.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.gson.Gson;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import br.com.concrete.R;
import br.com.concrete.http.RestApi;
import br.com.concrete.http.RestClient;
import br.com.concrete.model.Usuario;
import br.com.concrete.util.Constantes;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.appbar) protected AppBarLayout mAppBarLayout;

    @Nullable
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    protected SharedPreferences sp;
    protected ProgressDialog mProgressDialog;
    protected RestApi restApi;
    protected Context context;
    protected Usuario usuario;

    protected void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        ButterKnife.bind(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        restApi = RestClient.getClient(Constantes.URL_BASE).create(RestApi.class);
        sp = getSharedPreferences(Constantes.SP, Context.MODE_PRIVATE);
        setupProgressDialog();
    }

    protected void setToolbarTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    private void setupProgressDialog(){
        mProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowProgressDialog(String msg) {
        mProgressDialog = ProgressDialog.show(this, null, msg, true, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected Usuario getUsuario(){
        usuario = new Gson().fromJson(sp.getString(Constantes.USUARIO,null), Usuario.class);
        return usuario;
    }

    protected void setUsuario(Usuario usuario){
        sp.edit().putString(Constantes.USUARIO, new Gson().toJson(usuario)).commit();
    }
}