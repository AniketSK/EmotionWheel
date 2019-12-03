package com.aniketkadam.emotionwheel.storage.di

import android.content.Context
import androidx.room.Room
import com.aniketkadam.emotionwheel.storage.EmotionDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    fun getDatabaseModule(context: Context): EmotionDatabase = Room.databaseBuilder(
        context,
        EmotionDatabase::class.java, "emotion_database"
    ).build()
}