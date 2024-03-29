package com.example.architectureexample.common.db.converters;

import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.Date;

public class DateConverter  implements Serializable {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}