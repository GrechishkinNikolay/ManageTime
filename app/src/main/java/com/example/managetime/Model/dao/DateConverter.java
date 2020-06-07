package com.example.managetime.Model.dao;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
        @TypeConverter
        public Long dateToTimestamp(Date date) {
            if (date == null) {
                return null;
            } else {
                return date.getTime();
            }
        }
}