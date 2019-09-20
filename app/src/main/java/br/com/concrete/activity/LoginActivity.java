package br.com.concrete.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.concrete.R;
import br.com.concrete.base.BaseActivity;
import br.com.concrete.model.Usuario;
import br.com.concrete.util.MaskEditUtil;
import br.com.concrete.util.Utils;

public class LoginActivity extends BaseActivity {

    // Views
    private TextInputLayout til_edtID;
    private EditText login_id;
    private Button btn_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_login);
        init();
        setOnClick();
        load();
    }

    private void init(){
        context = this;
        til_edtID = findViewById(R.id.til_edtID);
        login_id = findViewById(R.id.login_id);
        login_id.addTextChangedListener(MaskEditUtil.mask(login_id, MaskEditUtil.FORMAT_ID));
        btn_entrar = findViewById(R.id.btn_entrar);
    }

    private void setOnClick(){
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valido()) {
                    login();
                    Utils.closeKeyBoard(context, login_id);
                }
            }
        });
    }

    private void load() {
        usuario = getUsuario();
        if (usuario != null && usuario.getLogin() != null) {
            login_id.setText(usuario.getLogin());
            btn_entrar.performClick();
        } else {
            login_id.setText("");
        }
    }

    private boolean valido(){
        boolean retorno = true;
        if(login_id.getText().toString() == null || login_id.getText().toString().equals("")){
            til_edtID.setError("DIGITE ID");
            retorno = false;
        } else if(login_id.getText().toString().length() != 6){
            til_edtID.setError("ID INVÁLIDO");
            retorno = false;
        } else {
            til_edtID.setErrorEnabled(false);
        }
        return retorno;
    }

    private void login(){
        // Chamar serviço de login
        usuario = new Usuario(login_id.getText().toString().trim());
        setUsuario(usuario);
        goToMain();
    }

    private void goToMain(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}