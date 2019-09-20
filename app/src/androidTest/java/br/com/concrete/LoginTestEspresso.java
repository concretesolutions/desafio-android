package br.com.concrete;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.concrete.activity.LoginActivity;
import br.com.concrete.activity.MainActivity;
import br.com.concrete.base.BaseEspresso;
import br.com.concrete.mock.response.LoginResponse;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
public class LoginTestEspresso extends BaseEspresso {

    @Override
    public Class getClazz() {
        return LoginActivity.class;
    }

    @Test
    public void logar(){
        logar("Teste de login...");
        initIntents();
        mockServer.changeResponse(LoginResponse.getLoginResnponseSucess());
        digitarTexto(R.id.login_id, "12345");
        clicarBotao("ENTRAR");
        intended(hasComponent(MainActivity.class.getName()));
        closeIntents();
    }
}