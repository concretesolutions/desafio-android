package isaacborges.com.projetoconcrete.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DataUtil {

    public static String formataDataDiaMesAno(String dataEmString){
        try {
            Date dt = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            dt = sdf.parse(dataEmString);
            return new SimpleDateFormat("dd/MM/yyyy").format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

}
