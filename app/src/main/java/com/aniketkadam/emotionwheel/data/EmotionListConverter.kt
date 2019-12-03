package com.aniketkadam.emotionwheel.data

import androidx.room.TypeConverter
import com.aniketkadam.emotionwheel.Emotion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class EmotionListConverter {

    private val gson = Gson()
    @TypeConverter
    fun emotionListToJson(emotionList: List<Emotion>): String {
        return gson.toJson(emotionList)
    }

    @TypeConverter
    fun jsonToEmotionList(emotionListString: String): List<Emotion> {

        val listType: Type =
            object : TypeToken<List<Emotion>?>() {}.type

        return gson.fromJson(emotionListString, listType)
    }
}