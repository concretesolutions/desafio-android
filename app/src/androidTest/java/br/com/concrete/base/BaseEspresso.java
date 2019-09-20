package br.com.concrete.base;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.intent.Intents;
import org.junit.Rule;
import org.junit.After;
import org.junit.Before;
import br.com.concrete.mock.MockServer;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.util.Log;

public abstract class BaseEspresso<T extends Activity> {

    @Rule
    public ActivityTestRule<T> activityTestRule = new ActivityTestRule<>(getClazz());

    protected MockServer mockServer;

    @Before
    public void setUp() {
        logar("before");
        if(mockServer == null){
            mockServer = new MockServer();
        }
    }

    @After
    public void cleanUp(){
        logar("after");
        mockServer.stop();
    }

    protected void initIntents(){
        Intents.init();
    }

    protected void closeIntents(){
        Intents.release();
    }

    protected void clicarBotao(String texto){
        onView(withText(texto)).perform(click());
    }

    protected void clicarBotao(int id){
        onView(withId(id)).perform(click());
    }

    protected void digitarTexto(int id, String texto){
        onView(withId(id)).perform(typeText(texto), closeSoftKeyboard());
    }

    protected void logar(String log){
        Log.i("*** ",log + " ***");
    }

    public abstract Class<T> getClazz();
}