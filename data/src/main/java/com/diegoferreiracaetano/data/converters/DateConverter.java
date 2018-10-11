package com.diegoferreiracaetano.data.converters;


import java.util.Date;

import androidx.room.TypeConverter;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long timesTamp) {

        return timesTamp == null ? null : new Date(timesTamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {

        return date == null ? null : date.getTime();
    }

}
