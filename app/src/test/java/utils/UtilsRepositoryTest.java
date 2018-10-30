package utils;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.InputStream;

/**
 * Created by felipe.recabarren on 29-10-18.
 */

public class UtilsRepositoryTest {

    UtilsRepository utilsRepository = new UtilsRepository();

    @Test
    public void convertStreamToString(){
        InputStream is = null;
        assertEquals(null,utilsRepository.convertStreamToString(is));
    }

    @Test
    public void convertStreamToStringTrue(){
        InputStream is = null;
        assertNotNull(utilsRepository.convertStreamToString(is));
    }

    @Test
    public void stringToJsonObject(){
        String json = "{'asdasd':213}";
        assertNotNull(utilsRepository.stringToJsonObject(json));
    }

    @Test
    public void stringToJsonObjectNull(){
        String json = null;
        //assertNull(utilsRepository.stringToJsonObject(json));
        assertTrue(utilsRepository.stringToJsonObject(json) == null);
    }

    @Test
    public void loadBitmapTrue(){
        String url = "https://avatars2.githubusercontent.com/u/42939261?v=4";
        assertNotNull(utilsRepository.loadBitmap(url));
    }

    @Test
    public void loadBitmapNull(){
    }


}
