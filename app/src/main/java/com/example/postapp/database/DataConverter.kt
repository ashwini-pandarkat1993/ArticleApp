package com.example.postapp.database

import androidx.room.TypeConverter
import com.example.postapp.model.Media
import com.example.postapp.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataConverter {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<Media?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Media?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<Media>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Media?>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }


    @TypeConverter
    fun fromUserList(countryLang: List<User?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<User?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toUserList(countryLangString: String?): List<User>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<User?>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}