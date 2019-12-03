package com.aniketkadam.emotionwheel.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StringListConverter {

    private val gson = Gson()
    @TypeConverter
    fun emotionListToJson(emotionList: List<String>): String {
        return gson.toJson(emotionList)
    }

    @TypeConverter
    fun jsonToEmotionList(emotionListString: String): List<String> {

        val listType: Type =
            object : TypeToken<List<String>?>() {}.type

        return gson.fromJson(emotionListString, listType)
    }
}