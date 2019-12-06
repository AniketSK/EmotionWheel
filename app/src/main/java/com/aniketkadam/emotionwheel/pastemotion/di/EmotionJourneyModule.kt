package com.aniketkadam.emotionwheel.pastemotion.di

import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyFragment
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyVm
import dagger.Module
import dagger.Provides

@Module
object EmotionJourneyModule {

    @JvmStatic
    @Provides
    fun provideEmotionJourneyVm(
        fragment: EmotionJourneyFragment,
        factory: EmotionJourneyViewModelFactory
    ): EmotionJourneyVm = ViewModelProviders.of(fragment, factory).get(EmotionJourneyVm::class.java)
}