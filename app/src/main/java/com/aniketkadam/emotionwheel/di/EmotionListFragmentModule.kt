package com.aniketkadam.emotionwheel.di

import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.emotionwheel.EmotionListFactory
import com.aniketkadam.emotionwheel.EmotionListFragment
import com.aniketkadam.emotionwheel.EmotionListViewModel
import com.aniketkadam.emotionwheel.storage.EmotionDao
import com.aniketkadam.emotionwheel.storage.EmotionDatabase
import dagger.Module
import dagger.Provides


@Module
object EmotionListFragmentModule {

    @JvmStatic
    @Provides
    fun provideVm(
        fragment: EmotionListFragment,
        emotionListFactory: EmotionListFactory
    ): EmotionListViewModel =
        ViewModelProviders.of(fragment, emotionListFactory).get(EmotionListViewModel::class.java)

    @JvmStatic
    @Provides
    fun provideEmotionDao(database: EmotionDatabase): EmotionDao = database.getEmotionDao()
}