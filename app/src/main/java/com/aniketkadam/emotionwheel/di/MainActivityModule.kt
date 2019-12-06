package com.aniketkadam.emotionwheel.di

import com.aniketkadam.emotionwheel.storage.EmotionDao
import com.aniketkadam.emotionwheel.storage.EmotionDatabase
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @JvmStatic
    @Provides
    fun provideEmotionDao(database: EmotionDatabase): EmotionDao = database.getEmotionDao()

}