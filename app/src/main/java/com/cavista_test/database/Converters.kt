
package com.cavista_test.database

import androidx.room.TypeConverter
import java.util.Calendar

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

        @TypeConverter
        @JvmStatic
        fun datestampToCalendar(value: Long): Calendar =
                Calendar.getInstance().apply { timeInMillis = value }
    }
}