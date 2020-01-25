package br.com.victoramaral.githubdive.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.com.victoramaral.githubdive.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText textInputNome;
    private TextInputEditText textInputEmail;
    private Button buttonEntrar;
    private Button buttonAnonimo;

    public static final String NOME_KEY = "nome";
    public static final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = String.valueOf(textInputNome.getText());
                String email = String.valueOf(textInputEmail.getText());
                if (!email.isEmpty() && !nome.isEmpty()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class)
                            .putExtra(NOME_KEY, nome).putExtra(EMAIL_KEY, email));
                    finish();
                } else if (nome.isEmpty()) {
                    textInputNome.setError("Insira seu nome ou entre como anônimo");
                } else if (email.isEmpty()) {
                    textInputEmail.setError("Insira seu email ou entre como anônimo");
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Desculpe, não foi possível concluir a solicitação",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = "Anônimo";
                String email = "Anônimo";
                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                        .putExtra(NOME_KEY, nome).putExtra(EMAIL_KEY, email));
                finish();
            }
        });
    }

    public void initViews() {
        textInputNome = findViewById(R.id.editTextNome);
        textInputEmail = findViewById(R.id.editTextEmail);
        buttonEntrar = findViewById(R.id.buttonLogin);
        buttonAnonimo = findViewById(R.id.buttonWithoutLogin);
    }
}
