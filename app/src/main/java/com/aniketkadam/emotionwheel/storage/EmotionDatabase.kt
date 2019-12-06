package com.aniketkadam.emotionwheel.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aniketkadam.emotionwheel.data.EmotionJourney
import com.aniketkadam.emotionwheel.data.StringListConverter


@Database(entities = [EmotionJourney::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class EmotionDatabase : RoomDatabase() {

    abstract fun getEmotionDao(): EmotionDao
}