package br.com.concrete;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.concrete.model.Usuario;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("br.com.concrete", appContext.getPackageName());
    }

    @Test
    public void exampleUnitTest1() {
        Usuario usuario = new Usuario();
        assertEquals("Concrete", usuario.getNameDefault());
    }

    @Test
    public void exampleUnitTest2() {
        Usuario usuario = new Usuario();
        assertTrue(usuario.getNameDefault().equals("Concrete"));
    }

    @Test
    public void exampleUnitTest3() {
        Usuario usuario = new Usuario();
        assertFalse(usuario.getNameDefault().equals("concrete"));
    }
}