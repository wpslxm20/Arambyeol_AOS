package com.my_app.arambyeol.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

enum class MealTimeEnum(val startTime: LocalTime, val endTime: LocalTime) {
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKDAY_MORNING(LocalTime.of(7, 30), LocalTime.of(9, 0)),
    @RequiresApi(Build.VERSION_CODES.O)
    TAKE_OUT(LocalTime.of(8, 30), LocalTime.of(9, 30)),
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKDAY_LUNCH(LocalTime.of(11, 30), LocalTime.of(13, 30)),
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKDAY_DINNER(LocalTime.of(17, 30), LocalTime.of(19, 0)),
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKEND_MORNING(LocalTime.of(8, 0), LocalTime.of(9, 0)),
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKEND_LUNCH(LocalTime.of(12, 0), LocalTime.of(13, 30)),
    @RequiresApi(Build.VERSION_CODES.O)
    WEEKEND_DINNER(LocalTime.of(17, 30), LocalTime.of(18, 40))
}