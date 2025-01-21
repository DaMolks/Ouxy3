package com.damolks.ouxy3.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun toString(dateTime: LocalDateTime): String {
        return dateTime.format(formatter)
    }

    @TypeConverter
    fun toDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value, formatter)
    }
}