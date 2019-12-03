package com.aniketkadam.emotionwheel.storage.di

import android.content.Context
import androidx.room.Room
import com.aniketkadam.emotionwheel.storage.EmotionDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @JvmStatic
    @Singleton
    @Provides
    fun getDatabaseModule(context: Context): EmotionDatabase = Room.databaseBuilder(
        context,
        EmotionDatabase::class.java, "emotion_database"
    ).build()
}