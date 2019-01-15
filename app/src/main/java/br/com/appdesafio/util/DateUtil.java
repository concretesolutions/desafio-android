package br.com.appdesafio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for dates.
 */
public class DateUtil {


    /**
     * Method responsible for formatting the date.
     * @param dateE
     * @return
     */
    public static String formateDate(final String dateE) {
        String date1 = null;
        try {
            SimpleDateFormat inputFormmatter = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = inputFormmatter.parse(dateE);
            date1 = outputFormatter.format(date);
        } catch (Exception e) {

        }
        return date1;


    }
}
