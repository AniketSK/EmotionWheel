package com.aniketkadam.emotionwheel.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.emotionwheel.EmotionListFactory
import com.aniketkadam.emotionwheel.EmotionListViewModel
import com.aniketkadam.emotionwheel.EmotionRepo
import dagger.Module
import dagger.Provides


@Module
object EmotionListFragmentModule {

    @JvmStatic
    @Provides
    fun provideVm(
        fragment: Fragment,
        emotionListFactory: EmotionListFactory
    ): EmotionListViewModel =
        ViewModelProviders.of(fragment, emotionListFactory).get(EmotionListViewModel::class.java)


    @JvmStatic
    @Provides
    fun provideVmFactory(emotionRepo: EmotionRepo): EmotionListFactory =
        EmotionListFactory(emotionRepo)

}