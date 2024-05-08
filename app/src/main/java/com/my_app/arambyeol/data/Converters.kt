package com.my_app.arambyeol.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromDayPlanList(value: List<DayPlan>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<DayPlan>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDayPlanList(value: String?): List<DayPlan>? {
        val gson = Gson()
        val type = object : TypeToken<List<DayPlan>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCourseList(value: List<Course>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCourseList(value: String?): List<Course>? {
        val gson = Gson()
        val type = object : TypeToken<List<Course>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}