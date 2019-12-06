package com.aniketkadam.emotionwheel.di

import com.aniketkadam.emotionwheel.EmotionListFragment
import com.aniketkadam.emotionwheel.pastemotion.EmotionJourneyFragment
import com.aniketkadam.emotionwheel.pastemotion.di.EmotionJourneyModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [EmotionListFragmentModule::class])
    abstract fun provideEmotionFragment(): EmotionListFragment

    @ContributesAndroidInjector(modules = [EmotionJourneyModule::class])
    abstract fun provideEmotionJourneyFragment(): EmotionJourneyFragment
}