package com.aniketkadam.emotionwheel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aniketkadam.emotionwheel.Emotion
import com.aniketkadam.emotionwheel.data.EmotionJourney
import com.aniketkadam.emotionwheel.data.EmotionListConverter


@Database(entities = [EmotionJourney::class, Emotion::class], version = 1, exportSchema = true)
@TypeConverters(EmotionListConverter::class)
abstract class EmotionDatabase : RoomDatabase() {

    abstract fun getEmotionDao(): EmotionDao
}